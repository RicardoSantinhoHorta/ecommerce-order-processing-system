package payment.controller;

import order.dto.CreateOrderRequestDTO;
import order.dto.OrderDetailsResponseDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import order.service.OrderService;
import payment.dto.PaymentDetailsResponseDTO;
import payment.dto.ProcessPaymentRequestDTO;
import payment.service.PaymentService;

@RestController
public class PaymentController {

    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/orders_payment")
    public PaymentDetailsResponseDTO processPayment(@Valid @RequestBody ProcessPaymentRequestDTO request) {
        return paymentService.processPayment(request);
    }
}
