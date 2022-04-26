package Domain.Stores;

import java.time.LocalDate;
import java.util.Hashtable;

public class Raffle {

    private int price;
    private LocalDate raffleEnd;
    private Hashtable<String, Integer> usersShares;

    public Raffle(int price, LocalDate raffleEnd, Hashtable<String, Integer> usersShares) {
        this.price = price;
        this.raffleEnd = raffleEnd;
        this.usersShares = usersShares;
    }

}
