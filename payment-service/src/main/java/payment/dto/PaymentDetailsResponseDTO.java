package payment.dto;


import order.enums.OrderState;
import payment.enums.PaymentMethod;
import payment.enums.PaymentResult;

import java.math.BigDecimal;

public record PaymentDetailsResponseDTO(BigDecimal amount,
                                        PaymentMethod paymentMethod,
                                        PaymentResult paymentResult) {
}
