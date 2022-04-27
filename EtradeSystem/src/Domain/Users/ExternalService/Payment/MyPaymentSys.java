package Domain.Users.ExternalService.Payment;

import java.time.LocalTime;

public class MyPaymentSys {
    IPay realPaymentSys;

    public MyPaymentSys() {
        this.realPaymentSys = new PaymentAdapter(null);
    }

    public boolean pay(int cardNumber, LocalTime expDate, int cvv, double price,int cardTo) {
        return realPaymentSys.pay(cardNumber, expDate, cvv,price,cardTo);
    }
    public boolean canPay(int cardFrom, LocalTime expDate, int cvv,double price){
        return realPaymentSys.canPay(cardFrom, expDate, cvv, price);
    }
}
