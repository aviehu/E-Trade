package com.workshop.ETrade.Domain.Notifications;


import com.workshop.ETrade.Controller.MessageController;
import com.workshop.ETrade.Domain.Users.User;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDate;


public class NotificationManager {
   // List<Notification> awaitingNotifications;
    @DBRef(lazy = true)
   protected ApplicationContext context;
    @DBRef(lazy = true)
    protected MessageController messageController;




    public NotificationManager(){
        context = MessageController.getAppContext();
        messageController = (MessageController) context.getBean("messageController");
    }
    public boolean sendNotification(User user, String message, String sentFrom) {
        Notification notification = new Notification(LocalDate.now(), sentFrom, message, user.getUserName());
        if(user.isConnected()) {
           int i =  messageController.sendNotification(notification);
           if(i == -1)
               user.addToAwaitingNotification(notification);
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
