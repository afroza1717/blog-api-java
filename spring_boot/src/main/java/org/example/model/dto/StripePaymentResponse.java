package org.example.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StripePaymentResponse {
    private String status;
    private String message;
    private String sessionId;
    private String sessionUrl;

}
