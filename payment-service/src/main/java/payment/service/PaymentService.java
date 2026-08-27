package payment.service;

import order.event.OrderCreatedEvent;
import org.springframework.stereotype.Service;
import payment.dto.PaymentDetailsResponseDTO;
import payment.dto.ProcessPaymentRequestDTO;
import payment.gateway.PaymentGateway;
import payment.mapper.PaymentMapper;
import payment.model.Payment;
import payment.repository.PaymentRepository;

@Service
public class PaymentService {

    private PaymentGateway paymentGateway;
    private PaymentMapper paymentMapper;
    private PaymentRepository paymentRepository;

    public PaymentService(PaymentGateway paymentGateway,
                          PaymentMapper paymentMapper,
                          PaymentRepository paymentRepository) {

        this.paymentGateway = paymentGateway;
        this.paymentMapper = paymentMapper;
        this.paymentRepository = paymentRepository;
    }

    public void createPendingPayment(OrderCreatedEvent event){
        Payment payment = paymentMapper.createPendingPayment(event);
        paymentRepository.save(payment);
    }

    public PaymentDetailsResponseDTO processPayment(ProcessPaymentRequestDTO request){
        Payment payment = new Payment();


    }
}
