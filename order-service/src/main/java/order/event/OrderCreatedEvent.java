package order.event;

import java.math.BigDecimal;

public record OrderCreatedEvent(Long orderId, BigDecimal totalPrice) {
}
