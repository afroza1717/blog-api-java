package org.example.manager.userManager;

import lombok.RequiredArgsConstructor;
import org.example.manager.paymentManager.StripePaymentService;
import org.example.model.db.UserType;
import org.example.model.dto.AuthenticationResponse;
import org.example.model.dto.AuthticationRequest;
import org.example.model.db.User;
import org.example.model.dto.StripePaymentRequest;
import org.example.model.dto.StripePaymentResponse;
import org.example.securityManager.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final StripePaymentService stripePaymentService;
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    public AuthenticationResponse register(User user) {
        User registeredUser = User.builder()
                .first_name(user.getFirst_name())
                .last_name(user.getLast_name())
                .email(user.getEmail())
                .username(user.getUsername())
                .password(passwordEncoder.encode(user.getPassword()))
                .userType(user.getUserType())
                .build();
        userRepository.save(registeredUser);

        String sessionUrl = createUserPaymentProcess(user.getUserType());

        var jwtToken = jwtService.generateToken(registeredUser);

        return AuthenticationResponse.builder()
               .token(jwtToken)
                .stripeUrl(sessionUrl)
                .build();
    }

    private String createUserPaymentProcess(UserType userType) {
        logger.info("UserType: {}", userType);
        StripePaymentRequest stripePaymentRequest = StripePaymentRequest.builder()
                        .name(userType.getName())
                        .amount(userType.getValue())
                        .quantity(1L)
                        .currency("USD")
                        .build();
        StripePaymentResponse response = stripePaymentService.checkoutBlogPayment(stripePaymentRequest);
        logger.info("Session URL {}", response.getSessionUrl());

        return response.getSessionUrl();
    }

    public AuthenticationResponse authenticate(AuthticationRequest authticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authticationRequest.getUsername(),
                        authticationRequest.getPassword())
        );
        var userFromDb = userRepository.findByUsername(authticationRequest.getUsername())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(userFromDb);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }
}
