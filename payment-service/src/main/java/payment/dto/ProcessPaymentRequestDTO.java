package payment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;import payment.enums.PaymentMethod;

import java.math.BigDecimal;

public record ProcessPaymentRequestDTO(
        @NotNull(message = "ID is required")
        @NotBlank(message = "ID cannot be blank")
        Long orderId,

        PaymentMethod paymentMethod,
        String token){
}
