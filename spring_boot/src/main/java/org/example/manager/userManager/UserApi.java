package org.example.manager.userManager;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.model.dto.AuthenticationResponse;
import org.example.model.dto.AuthticationRequest;
import org.example.model.db.User;
import org.example.util.HelperUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class UserApi {
    @Autowired
    private UserRepository userRepository;
    private final AuthenticationService authenticationService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    public static String RAISED_EXCEPTION_MESSAGE = "";
    private static final Logger logger = LoggerFactory.getLogger(UserApi.class);

    @GetMapping("/users/all")
    @ResponseBody
    public List<User> getAll() {
        Timestamp startTime = HelperUtils.nowTimestamp();
        Timestamp endTime = HelperUtils.nowTimestamp();
        logger.info("Getting all records takes: " + Long.valueOf(endTime.getTime() - startTime.getTime()).toString()
                + " milli seconds. ");
        return userRepository.findAll();
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody User user) {

      return ResponseEntity.ok(authenticationService.register(user));
    }

    @PostMapping("/login")
    public Map<String, Object> register(@RequestBody AuthticationRequest authticationRequest) {

        AuthenticationResponse authenticationResponse = authenticationService.authenticate(authticationRequest);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put(HelperUtils.SUCCESS_MESSAGE, authticationRequest);
        responseMap.put(HelperUtils.DATA_TOKEN, authenticationResponse.getToken());

        return responseMap;
    }

    @PostMapping("/logout/")
    public String performLogout(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
        SecurityContextHolder.getContext().setAuthentication(null);
        return "redirect:/login";
    }
}
