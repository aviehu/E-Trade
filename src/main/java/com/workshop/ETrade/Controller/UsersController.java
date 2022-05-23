package com.workshop.ETrade.Controller;

import com.workshop.ETrade.Domain.Stores.Store;
import com.workshop.ETrade.Service.ResultPackge.ResultBool;
import com.workshop.ETrade.Service.ResultPackge.ResultMsg;
import com.workshop.ETrade.Service.ServiceInterface;
import com.workshop.ETrade.Service.SystemService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private ServiceInterface systemService;


    @GetMapping("/onlinemembers")
    public ResultMsg getOnlineMembers(String userName) {
        return systemService.getOnlineMembers(systemService.getOnline());
    }

    @GetMapping("/offlinemembers")
    public ResultMsg getOfflineMembers(String userName) {
        return systemService.getOfflineMembers(userName);
    }

//    public ResultBool hasAdmin(){
//        return systemService.hasAdmin();
//    }

    @GetMapping("/removemember/{member}")
    public ResultBool removeMember(String userName, @PathVariable("member") String memberToRemove) {
        return systemService.removeMember(userName, memberToRemove);
    }

    @GetMapping("/entersystem")
    public ResultMsg enterSystem() {
        return systemService.enterSystem();
    }

    @GetMapping("/addsysmanager/{member}")
    public ResultBool addSystemManager(String userName, @PathVariable("member") String managerToAdd) {
        return systemService.addSystemManager(userName, managerToAdd);
    }

    @GetMapping("/removesysmanager/{member}")
    public ResultBool removeSystemManager(String userName, @PathVariable("member") String managerToRemove) {
        return systemService.removeSystemManager(userName, managerToRemove);
    }

    @GetMapping("/exitsystem")
    public ResultBool exitSystem(String userName) {
        return systemService.exitSystem(userName);
    }

    @PostMapping("/signup")
    public ResultBool signUp(SignUpForm form) {
        return systemService.signUp(systemService.getOnline(), form.newUserName, form.password, form.name, form.lastName);
    }

    @PostMapping("/login")
    public ResultBool login(LoginForm form) {
        return systemService.login(systemService.getOnline(), form.memberUserName, form.password);
    }

    @GetMapping("/logout")
    public ResultMsg logOut() {
        return systemService.logOut(systemService.getOnline());
    }

//    @GetMapping("/terminate/{user}")
//    public ResultBool adminTerminateUser(String adminName, @PathVariable("user") String userToTerminate) {
//        return systemService.adminTerminateUser(adminName, userToTerminate);
//    }

}
