package order.mapper;

import order.dto.CreateOrderItemRequestDTO;
import order.dto.OrderDetailsResponseDTO;
import order.event.OrderCreatedEvent;
import order.model.Order;
import order.model.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public OrderItem toOrderItem(CreateOrderItemRequestDTO dto){
        OrderItem orderItem = new OrderItem();

        orderItem.setProductId(dto.productId());
        orderItem.setName(dto.name());
        orderItem.setPrice(dto.price());
        orderItem.setQuantity(dto.quantity());

        return orderItem;
    }

    public OrderDetailsResponseDTO toResponseDTO(Order order){
        return new OrderDetailsResponseDTO(order.getOrderPrice());
    }

    public OrderCreatedEvent toOrderCreatedEvent(Order order){
        return new OrderCreatedEvent(order.getId(),  order.getOrderPrice());
    }
}
