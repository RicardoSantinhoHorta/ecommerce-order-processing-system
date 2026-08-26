package order.controller;

import order.dto.CreateOrderRequestDTO;
import order.dto.OrderDetailsResponseDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import order.service.OrderService;

@RestController
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/orders")
    public OrderDetailsResponseDTO createOrder(@Valid @RequestBody CreateOrderRequestDTO request) {
        return orderService.createOrder(request);
    }
}
