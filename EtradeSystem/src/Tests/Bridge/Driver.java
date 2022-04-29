package Tests.Bridge;

import Service.ServiceInterface;
import Service.SystemService;

public class Driver {

    public ServiceInterface getService(){
        SystemServiceProxy proxy = new SystemServiceProxy();
        //proxy.setReal(new SystemService());
        return proxy;
    }
}
