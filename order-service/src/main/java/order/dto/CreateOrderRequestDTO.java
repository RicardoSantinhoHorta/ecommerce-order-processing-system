package order.dto;

import order.model.OrderItem;

import java.util.List;

//TODO Meter estado da order no DTO
public record CreateOrderRequestDTO(List<OrderItem> orderItems) {
}
