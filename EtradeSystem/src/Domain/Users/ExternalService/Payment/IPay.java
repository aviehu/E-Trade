package Domain.Users.ExternalService.Payment;

import java.time.LocalTime;

public interface IPay {
    public boolean pay(int cardFrom, LocalTime expDate, int cvv,double price, int cardTo);
    public boolean canPay(int cardFrom, LocalTime expDate, int cvv,double price);
}
