package order.model;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
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
    private List<OrderItem> orderItems;

    private BigDecimal orderPrice;

    /**
     * Sincroniza ambas as partes. Order e OrderItem.
     * @param orderItem um item a adicionar
     */
    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem); //Order conhece o Item
        orderItem.setOrder(this);  /* Cada item conhece a order que está associado.
        Isto será importante o OrderItem é que tem o order_id*/
    }

}
