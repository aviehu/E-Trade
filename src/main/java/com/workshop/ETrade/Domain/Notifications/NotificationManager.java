package com.workshop.ETrade.Domain.Notifications;


import com.workshop.ETrade.Controller.MessageController;
import com.workshop.ETrade.Domain.Users.Users.Member;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;


public class NotificationManager {
   // List<Notification> awaitingNotifications;
   protected ApplicationContext context;
    protected MessageController messageController;




    public NotificationManager(){
        context = MessageController.getAppContext();
        messageController = (MessageController) context.getBean("messageController");
    }
    public boolean sendNotification(Member user, String message, String sentFrom) {
        Notification notification = new Notification(LocalDate.now(), sentFrom, message, user.getUserName());
        if(user.isConnected()) {
            messageController.sendNotification(notification);
            return true;
        }
        user.addToAwaitingNotification(notification);
        return false;
    }
//    public void sendAllNotification(HashMap<Member,Notification> notifications) {
//        for (Member user : notifications.keySet()) {
//            if (user.isConnected()) {
//                messageController.sendNotification(notifications.get(user));
//            }
//            user.addToAwaitingNotification(notifications.get(user));
//        }
//    }


}
