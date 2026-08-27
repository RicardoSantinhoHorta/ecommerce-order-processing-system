package payment.gateway;

import org.springframework.stereotype.Component;
import payment.enums.PaymentResult;

import java.math.BigDecimal;


//Não temos um real processador de pagamento(obv). O pagamento será validado de acordo com o payment token.
@Component
public class FakePaymentGateway implements PaymentGateway {

    @Override
    public PaymentResult processPayment(String paymentToken, BigDecimal amount) {
        if(paymentToken.equals("CARD_ACCEPTED")){
            return PaymentResult.ACCEPTED;
        }
        if(paymentToken.equals("CARD_DECLINED")){
            return PaymentResult.DECLINED;
        }
        return PaymentResult.INSUFFICIENT_FUNDS;
    }
}
