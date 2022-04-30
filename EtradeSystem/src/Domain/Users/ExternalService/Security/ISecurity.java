package Domain.Users.ExternalService.Security;

public interface ISecurity {
    public String encode(String password);
    public String decode(String password);
    public boolean isExist();
}
