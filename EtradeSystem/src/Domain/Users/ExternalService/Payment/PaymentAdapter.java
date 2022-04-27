package Domain.Users.ExternalService.Payment;


import Domain.Users.ExternalService.Payment.IPay;
import Domain.Users.ExternalService.Payment.PaymentAdaptee;

import java.time.LocalTime;

public class PaymentAdapter implements IPay {
    private PaymentAdaptee paymentAdaptee;

    public PaymentAdapter(PaymentAdaptee extPaySys) {
        this.paymentAdaptee = extPaySys;
    }

    @Override
    public boolean pay(int cardNumber, LocalTime expDate, int cvv, double price,int cardTo) {
        if(paymentAdaptee == null)
            return false;
        else
            return paymentAdaptee.payment(cardNumber,expDate,cvv,price,cardTo);
    }

    @Override
    public boolean canPay(int cardFrom, LocalTime expDate, int cvv, double price) {
        if(paymentAdaptee == null){
            if (expDate.isBefore(LocalTime.now()))
                return false;
            return true;
        }
        return paymentAdaptee.canPay(cardFrom,expDate,cvv,price);
    }
}
