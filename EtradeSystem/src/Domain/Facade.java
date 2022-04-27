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

    public String enterSystem() {
        return "Welcome To E-Trade\n" + storesFacade.displayAllStores();
    }

    public boolean addExternalPaymentService() {
        return true;
    }

    public boolean exitSystem() {
        return true;
    }




}
