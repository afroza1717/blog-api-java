package org.example.manager.paymentManager;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.example.model.dto.StripePaymentRequest;
import org.example.model.dto.StripePaymentResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class StripePaymentService {

    @Value("${stripe.secretKey}")
    private String secretKey;

    public StripePaymentResponse checkoutBlogPayment(
            StripePaymentRequest stripePaymentRequest) {
        Stripe.apiKey = secretKey;

        SessionCreateParams.LineItem.PriceData.ProductData productData =
                SessionCreateParams.LineItem.PriceData.ProductData.builder()
                .setName(stripePaymentRequest.getName())
                        .build();

        SessionCreateParams.LineItem.PriceData priceData =
                SessionCreateParams.LineItem.PriceData.builder()
                .setCurrency("USD")
                .setUnitAmount(stripePaymentRequest.getAmount())
                .setProductData(productData)
                        .build();

        SessionCreateParams.LineItem lineItem =  SessionCreateParams.LineItem.builder()
                .setQuantity(stripePaymentRequest.getQuantity())
                .setPriceData(priceData)
                    .build();

        SessionCreateParams param = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("http://localhost:5173/signin")
                .setCancelUrl("http://localhost:5173/cancel")
                .addLineItem(lineItem)
                .build();

            Session session = null;

        try {
            session = Session.create(param);
        } catch (StripeException e) {
            throw new RuntimeException(e);
        }

       return StripePaymentResponse.builder()
                .status("SUCCESS")
                .message("Payment Completed")
                .sessionId(session.getId())
                .sessionUrl(session.getUrl())
                .build();
    }
}
