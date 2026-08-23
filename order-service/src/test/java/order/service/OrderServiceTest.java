package order.service;

import order.mapper.OrderMapper;
import order.messaging.OrderEventProducer;
import order.model.Order;
import order.model.OrderItem;
import order.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    private OrderRepository orderRepository;
    private OrderMapper orderMapper;
    private OrderEventProducer orderEventProducer;
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        orderRepository = mock(OrderRepository.class);
        orderMapper = mock(OrderMapper.class);
        orderEventProducer = mock(OrderEventProducer.class);

        orderService = new OrderService(orderRepository, orderMapper, orderEventProducer);

    }

    @Test
    void calculateOrderPrice1() {
        Order order = new Order();

        OrderItem orderItem1 = new OrderItem();
        orderItem1.setPrice(BigDecimal.valueOf(10));
        orderItem1.setQuantity(1);

        OrderItem orderItem2 = new OrderItem();
        orderItem2.setPrice(BigDecimal.valueOf(30));
        orderItem2.setQuantity(2);

        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(orderItem1);
        orderItems.add(orderItem2);

        order.setOrderItems(orderItems);

        BigDecimal result = order.calculateOrderPrice(order);

        assertEquals(70, result.doubleValue());

    }

    @Test
    void calculateOrderPrice2() {
        Order order = new Order();

        OrderItem orderItem1 = new OrderItem();
        orderItem1.setPrice(BigDecimal.valueOf(5));
        orderItem1.setQuantity(3);

        OrderItem orderItem2 = new OrderItem();
        orderItem2.setPrice(BigDecimal.valueOf(100));
        orderItem2.setQuantity(1);

        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(orderItem1);
        orderItems.add(orderItem2);

        order.setOrderItems(orderItems);

        BigDecimal result = order.calculateOrderPrice(order);

        assertEquals(115, result.doubleValue());

    }

    @Test
    void calculateOrderPrice3() {
        Order order = new Order();

        OrderItem orderItem1 = new OrderItem();
        orderItem1.setPrice(BigDecimal.valueOf(5));
        orderItem1.setQuantity(0);

        OrderItem orderItem2 = new OrderItem();
        orderItem2.setPrice(BigDecimal.valueOf(100));
        orderItem2.setQuantity(0);

        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(orderItem1);
        orderItems.add(orderItem2);

        order.setOrderItems(orderItems);

        BigDecimal result = order.calculateOrderPrice(order);

        assertEquals(0, result.doubleValue());

    }

    @Test
    void calculateOrderPrice4() {
        Order order = new Order();

        OrderItem orderItem1 = new OrderItem();
        orderItem1.setPrice(BigDecimal.valueOf(0));
        orderItem1.setQuantity(1000);

        OrderItem orderItem2 = new OrderItem();
        orderItem2.setPrice(BigDecimal.valueOf(1));
        orderItem2.setQuantity(50);

        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(orderItem1);
        orderItems.add(orderItem2);

        order.setOrderItems(orderItems);

        BigDecimal result = order.calculateOrderPrice(order);

        assertEquals(50, result.doubleValue());

    }
}