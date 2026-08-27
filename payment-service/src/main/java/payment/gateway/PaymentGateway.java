package payment.gateway;

import payment.enums.PaymentResult;

import java.math.BigDecimal;

public interface PaymentGateway {
    PaymentResult processPayment(String paymentToken);
}
