package payment.service;

import order.event.OrderCreatedEvent;
import org.springframework.stereotype.Service;
import payment.dto.PaymentDetailsResponseDTO;
import payment.dto.ProcessPaymentRequestDTO;
import payment.enums.PaymentResult;
import payment.exception.PaymentNotFoundException;
import payment.gateway.PaymentGateway;
import payment.mapper.PaymentMapper;
import payment.messaging.PaymentEventProducer;
import payment.model.Payment;
import payment.repository.PaymentRepository;

import java.util.Optional;

@Service
public class PaymentService {

    private PaymentGateway paymentGateway;
    private PaymentMapper paymentMapper;
    private PaymentRepository paymentRepository;
    private PaymentEventProducer paymentEventProducer;

    public PaymentService(PaymentGateway paymentGateway,
                          PaymentMapper paymentMapper,
                          PaymentRepository paymentRepository,
                          PaymentEventProducer paymentEventProducer) {

        this.paymentGateway = paymentGateway;
        this.paymentMapper = paymentMapper;
        this.paymentRepository = paymentRepository;
        this.paymentEventProducer = paymentEventProducer;
    }

    public void createPendingPayment(OrderCreatedEvent event){
        Payment payment = paymentMapper.createPendingPayment(event);
        paymentRepository.save(payment);
    }

    public PaymentDetailsResponseDTO processPayment(ProcessPaymentRequestDTO request){
        Payment payment = paymentRepository
                .findByOrderId(request.orderId())
                .orElseThrow(() -> new PaymentNotFoundException(
                        "Payment not found for order " + request.orderId()));

        payment.setPaymentMethod(request.paymentMethod());
        payment.setPaymentResult(paymentGateway.processPayment(request.token()));

        paymentRepository.save(payment);

        return paymentMapper.toResponseDTO(payment);
    }

    private void publishPaymentResult(Payment payment){
        if(payment.getPaymentResult() == PaymentResult.ACCEPTED){
            //paymentEventProducer.publishPaymentApproved();
        }else {
            //paymentEventProducer.publishPaymentApproved();
        }
    }
}
