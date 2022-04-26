package Domain.Users.ExternalService.Payment;


import java.time.LocalTime;

public class PaymentAdaptee {
    public boolean payment(int cardNum, LocalTime expDate, int cvv, int price,int cardTo){
        return false;
    }
    public boolean canPay(int cardFrom, LocalTime expDate, int cvv,int price){
        return false;
    }
}
