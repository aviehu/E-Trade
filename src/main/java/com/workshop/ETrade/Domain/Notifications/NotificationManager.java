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
        //awaitingNotifications = Collections.synchronizedList(new ArrayList<Notification>());
        context = MessageController.getAppContext();
        messageController = (MessageController) context.getBean("messageController");
    }
    public boolean sendNotification(Member user, String message, String sentFrom) {
        Notification notification = new Notification(LocalDate.now(), sentFrom, message, user.getUserName());
        if(user.isConnected()) {
            //storesController.SentNotification(notification);
            messageController.sendNotification(notification);
//            user.sendNotification(notification);
            return true;
        }
        user.addToAwaitingNotification(notification);
        //awaitingNotifications.add(notification);
        return false;
    }

//    public List<Notification> getAwaitingNotifications(String userName) {
//        List<Notification> result = Collections.synchronizedList(new ArrayList<Notification>());
//        for(Notification notification : awaitingNotifications) {
//            if(notification.getSentTo().equals(userName)) {
//                result.add(notification);
//            }
//        }
//        return result;
//    }

}
