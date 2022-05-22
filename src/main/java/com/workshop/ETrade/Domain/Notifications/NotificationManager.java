package com.workshop.ETrade.Domain.Notifications;

import com.workshop.ETrade.Domain.Users.Users.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NotificationManager {
    List<Notification> awaitingNotifications;

    public NotificationManager(){
        awaitingNotifications = Collections.synchronizedList(new ArrayList<Notification>());
    }

    public boolean sendNotification(User user, String message, String sentFrom) {
        Notification notification = new Notification(LocalDate.now(), sentFrom, message, user.getUserName());
        if(user.isConnected()) {
//            user.sendNotification(notification);
            return true;
        }
        awaitingNotifications.add(notification);
        return false;
    }

    public List<Notification> getAwaitingNotifications(String userName) {
        List<Notification> result = Collections.synchronizedList(new ArrayList<Notification>());
        for(Notification notification : awaitingNotifications) {
            if(notification.getSentTo().equals(userName)) {
                result.add(notification);
            }
        }
        return result;
    }

}
