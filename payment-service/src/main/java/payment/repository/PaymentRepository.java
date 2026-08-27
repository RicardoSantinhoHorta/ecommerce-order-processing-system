package payment.repository;

import order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import payment.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
}
