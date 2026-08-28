package payment.repository;

import order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import payment.model.Payment;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment,Long> {

    Optional<Payment> findByOrderId(Long orderId);
}
