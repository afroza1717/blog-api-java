package org.example.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StripePaymentRequest {
    private Long amount;
    private Long quantity;
    private String name;
    private String currency;


}