package Domain.Stores;

import java.time.LocalDate;
import java.util.Hashtable;
import java.util.Random;

public class Raffle {

    private Product product;
    private int raffleId;
    private double price;
    private LocalDate raffleEnd;
    private Hashtable<String, Double> usersShares;
    Random rand = new Random();

    public Raffle(Product product, double price, LocalDate raffleEnd, int raffleId) {
        this.raffleId = raffleId;
        this.price = price;
        this.raffleEnd = raffleEnd;
        this.usersShares = new Hashtable<>();
    }

    public boolean raffleEnded() {
        return LocalDate.now().isAfter(raffleEnd);
    }

    private double calculateTotalShares() {
        double total = 0;
        for(String userName : usersShares.keySet()) {
            total = total + usersShares.get(userName);
        }
        return total;
    }

    public boolean addShares(String userName, double amount) {
        if(calculateTotalShares() + amount > price) {
            return false;
        }
        if(usersShares.containsKey(userName)) {
            usersShares.computeIfPresent(userName, (K,V) -> V = V + amount);
        } else {
            usersShares.put(userName, amount);
        }
        return true;
    }

    public String selectWinner() {
        if(!raffleEnded()) {
            return null;
        }
        int counter = 0;
        int numbersToPick = (int) (price * 100);
        int selectedNumber = rand.nextInt(numbersToPick);
        for(String userName : usersShares.keySet()) {
            int currUserPart = (int) (usersShares.get(userName) * 100);
            if(counter + currUserPart >= selectedNumber) {
                return userName;
            }
            counter = counter + currUserPart;
        }
        return null;
    }

    private String sharesToString() {
        StringBuilder result = new StringBuilder();
        for(String userName : usersShares.keySet()) {
            double sharePercent = price / usersShares.get(userName);
            result.append(userName).append(": ").append(sharePercent).append("%\n");
        }
        return result.toString();
    }

    public String toString() {
        String result = "Raffle " + raffleId + ":\n";
        result +=       "Product: " + product.getName() + "\n";
        result +=       "Price: " + price + "\n";
        result +=       "Shares: \n" + sharesToString();
        result +=       "Raffle Ends At: " + raffleEnd.toString() + "\n\n";
        return result;
    }

}
