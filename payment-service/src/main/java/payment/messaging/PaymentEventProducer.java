package payment.messaging;

import order.event.OrderCreatedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class PaymentEventProducer {

    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

    public PaymentEventProducer(KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishPaymentApproved(OrderCreatedEvent event){
        kafkaTemplate.send("order-created", event);
    }

    public void publishPaymentRejected(OrderCreatedEvent event){
        kafkaTemplate.send("order-rejected", event);
    }
}
