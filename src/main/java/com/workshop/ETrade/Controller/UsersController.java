package com.workshop.ETrade.Controller;

import com.workshop.ETrade.Domain.Notifications.Notification;
import com.workshop.ETrade.Domain.Stores.Store;
import com.workshop.ETrade.Service.ResultPackge.ResultBool;
import com.workshop.ETrade.Service.ResultPackge.ResultMsg;
import com.workshop.ETrade.Service.ServiceInterface;
import com.workshop.ETrade.Service.SystemService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@RestController
@RequestMapping("/users")
@CrossOrigin
public class UsersController {

    @Autowired
    private ServiceInterface systemService;
    @Autowired
    private SimpMessagingTemplate smt;

//    @GetMapping("/onlinemembers")
//    public ResultMsg getOnlineMembers(@RequestHeader("Authorization") String userName) {
//        return systemService.getOnlineMembers(systemService.getOnline());
//    }

//    @GetMapping("/offlinemembers")
//    public ResultMsg getOfflineMembers(@RequestHeader("Authorization") String userName) {
//        return systemService.getOfflineMembers(userName);
//    }

//    public ResultBool hasAdmin(){
//        return systemService.hasAdmin();
//    }

    @GetMapping("/removemember/{member}")
    public ResultBool removeMember(@RequestHeader("Authorization") String userName, @PathVariable("member") String memberToRemove) {
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
    public ResultBool signUp(@RequestHeader("Authorization") String username, @RequestBody SignUpForm form) {
        return systemService.signUp(username, form.email, form.password, form.firstName, form.lastName);
    }

    @PostMapping("/login")
    public ResultBool login(@RequestHeader("Authorization") String userName, @RequestBody LoginForm form) {
        return systemService.login(userName, form.email, form.password);
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
