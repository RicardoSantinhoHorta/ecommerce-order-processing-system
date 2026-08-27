package payment.dto;


import order.enums.OrderState;
import payment.enums.PaymentMethod;

public record PaymentDetailsResponseDTO(OrderState orderState, PaymentMethod paymentMethod) {
}
