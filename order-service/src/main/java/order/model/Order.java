package order.model;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import order.enums.OrderState;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "orders")
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>(); //para começar [] e não null

    private BigDecimal orderPrice;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderState  orderState = OrderState.PENDING_PAYMENT;
    //Quando a order é criada, começa sempre com pending payment. se falhar, não é criada.

    @CreationTimestamp //Assim não precisamos de meter setter no service
    private LocalDateTime createdAt;


    /**
     * synchronize order items and order. Order knows items and vice versa
     * @param orderItem
     */
    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem); //Order conhece o Item
        orderItem.setOrder(this);  /* Cada item conhece a order que está associado.
        Isto será importante o OrderItem é que tem o order_id*/
    }

    /**
     * Calculates the total order price
     * @param order
     * @return Total order price
     */
    public BigDecimal calculateOrderPrice(Order order) {
        BigDecimal totalPrice = BigDecimal.ZERO;

        for (OrderItem orderItem : order.getOrderItems()) {
            totalPrice = totalPrice
                    .add(orderItem.getPrice()
                    .multiply(BigDecimal.valueOf(orderItem.getQuantity())));
        }
        return totalPrice;
    }

}
