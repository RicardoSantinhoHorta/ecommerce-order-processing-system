package payment.service;

import org.springframework.stereotype.Service;
import payment.dto.PaymentDetailsResponseDTO;
import payment.dto.PerformPaymentRequestDTO;
import payment.gateway.PaymentGateway;

@Service
public class PaymentService {

    private PaymentGateway paymentGateway;

    public PaymentService(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public PaymentDetailsResponseDTO processPayment(PerformPaymentRequestDTO request){

    }
}
