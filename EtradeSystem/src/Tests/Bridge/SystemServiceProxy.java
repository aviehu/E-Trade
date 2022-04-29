package Tests.Bridge;

import Service.ServiceInterface;

public class SystemServiceProxy implements ServiceInterface {

    private ServiceInterface real;

    public void setReal(ServiceInterface real){
        this.real = real;
        //real.init();
    }
}
