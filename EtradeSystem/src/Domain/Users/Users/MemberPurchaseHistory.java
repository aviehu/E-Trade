package Domain.Users.Users;

import java.util.ArrayList;
import java.util.List;

public class MemberPurchaseHistory {
    private List<StoreBasket>   basketsPurchased;

    public MemberPurchaseHistory() {
        this.basketsPurchased = new ArrayList<>();
    }

    public String displayHistory(){
        String display = "";
        for(StoreBasket b : basketsPurchased){
            display += b.displayBasket();
        }
        return display;
    }
    public void addToHistory(StoreBasket b){
        basketsPurchased.add(b);
    }
}
