package payment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProcessPaymentRequestDTO(
        @NotNull(message = "ID is required")
        @NotBlank(message = "ID cannot be blank")
        Long id,

        @NotNull(message = "Amount is required")
        @Positive(message = "Amount must be greater than 0")
        BigDecimal amount){
}
