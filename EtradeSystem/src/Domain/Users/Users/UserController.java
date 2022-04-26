package Domain.Users.Users;

import java.util.ArrayList;
import java.util.List;

public class UserController {
    private List<Member> members;
    public static int memberDiscount;

    public UserController() {
        memberDiscount = 0;
        this.members = new ArrayList<>();
    }

   //unique userName
    public boolean signIn(String userName,String password,int age,String mail,String city,String street,int streetNum,int apartementNum) {
        for(Member m : members){
            if (m.getUserName() == userName)
                return false;
        }
        new Member(userName, password, age, mail,city,street,streetNum,apartementNum);
        return true;
   }

}
