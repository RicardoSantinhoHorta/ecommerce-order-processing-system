package payment.mapper;

import order.dto.CreateOrderItemRequestDTO;
import order.dto.OrderDetailsResponseDTO;
import order.event.OrderCreatedEvent;
import order.model.Order;
import order.model.OrderItem;
import org.springframework.stereotype.Component;
import payment.dto.PaymentDetailsResponseDTO;
import payment.model.Payment;

@Component
public class PaymentMapper {

    public Payment createPendingPayment(OrderCreatedEvent event) {
        Payment payment = new Payment();

        payment.setOrderId(event.orderId());
        payment.setAmount(event.totalPrice());

        return payment;
    }

    public PaymentDetailsResponseDTO toResponseDTO(Payment payment){
        return new PaymentDetailsResponseDTO(payment.getAmount(),
                payment.getPaymentMethod(),
                payment.getPaymentResult());
    }
}
