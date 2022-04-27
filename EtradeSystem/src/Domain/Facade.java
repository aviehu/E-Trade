package Domain;

import Domain.Stores.Store;
import Domain.Stores.StoresFacade;
import Domain.Users.Users.UserController;

public class Facade {
    private StoresFacade storesFacade;
    private UserController userController;

    public Facade() {
        storesFacade = new StoresFacade();
        userController = new UserController();
    }

    public boolean addStore(String storeName, String founderName,int card) {
        return storesFacade.addStore(storeName, founderName, card);
    }

    public String displayAllStores() {
        return storesFacade.displayAllStores();
    }
}
