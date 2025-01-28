package org.example.manager.paymentManager;

import org.example.model.dto.StripePaymentRequest;
import org.example.model.dto.StripePaymentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class StripePaymentApi {

    @Autowired
    private StripePaymentService stripePaymentService;

    @PostMapping("/blogs/payment")
    ResponseEntity<StripePaymentResponse> checkoutProducts(@RequestBody StripePaymentRequest stripePaymentRequest) {
        StripePaymentResponse stripePaymentResponse = stripePaymentService.checkoutBlogPayment(stripePaymentRequest);

        return ResponseEntity.status(HttpStatus.OK)
                .body(stripePaymentResponse);
    }


}
