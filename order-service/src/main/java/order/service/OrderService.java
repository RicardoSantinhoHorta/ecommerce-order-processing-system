package order.service;

import order.dto.CreateOrderItemRequestDTO;
import order.dto.CreateOrderRequestDTO;
import order.dto.OrderDetailsResponseDTO;
import order.enums.OrderState;
import order.event.OrderCreatedEvent;
import order.mapper.OrderMapper;
import order.messaging.OrderEventProducer;
import order.model.Order;
import order.model.OrderItem;
import order.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;import java.util.List;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private OrderMapper orderMapper;
    private OrderEventProducer orderEventProducer;

    public OrderService(OrderRepository orderRepository,
                        OrderMapper orderMapper,
                        OrderEventProducer orderEventProducer) {

        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.orderEventProducer = orderEventProducer;
    }

    //Por agora aceita qualquer order. Não importa se há stock ou se o payment foi validado
    @Transactional
    public OrderDetailsResponseDTO createOrder(CreateOrderRequestDTO request) {
        Order order = new Order();

        addOrderItems(order, request.orderItems());
        order.setOrderPrice(order.calculateOrderPrice(order));
        orderRepository.save(order);

        //Mete em event e manda para o kafka
        OrderCreatedEvent event = orderMapper.toOrderCreatedEvent(order);
        orderEventProducer.publishOrderCreated(event);

        return orderMapper.toResponseDTO(order);
    }

    private void addOrderItems(Order order, List<CreateOrderItemRequestDTO> orderItems) {
        for(CreateOrderItemRequestDTO itemRequest : orderItems){
            OrderItem orderItem = orderMapper.toOrderItem(itemRequest);
            order.addOrderItem(orderItem);
        }
    }

}
