package Domain.Users.ExternalService.Security;

public class SecurityAdapter implements ISecurity {
    private SecurityAdaptee securityAdaptee;

    public SecurityAdapter(SecurityAdaptee securityAdaptee) {
        this.securityAdaptee = securityAdaptee;
    }

    @Override
    public String encode(String password) {
        if(securityAdaptee == null)
            return password;
        else
            return securityAdaptee.encode(password);
    }

    @Override
    public String decode(String password) {
        if(securityAdaptee == null)
            return password;
        else
            return securityAdaptee.decode(password);
    }
    public boolean isExist(){
        return securityAdaptee != null;
    }
}
