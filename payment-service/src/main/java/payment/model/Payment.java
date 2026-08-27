package payment.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import payment.enums.PaymentMethod;
import payment.enums.PaymentResult;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "order_payments")
@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMethod paymentMethod = PaymentMethod.NONE;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentResult paymentResult = PaymentResult.PENDING_PAYMENT;

}
