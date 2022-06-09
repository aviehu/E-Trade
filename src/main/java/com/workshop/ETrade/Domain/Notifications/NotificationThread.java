package com.workshop.ETrade.Domain.Notifications;

import com.workshop.ETrade.Domain.Users.Member;

public class NotificationThread extends Thread{

    private Member user;
    private String sendFrom;

    private String message;

    public NotificationThread(Member user, String message, String sendFrom) {
        this.user = user;
        this.sendFrom = sendFrom;
        this.message = message;
    }

    public void run() {
        if(!user.getUserName().equals(sendFrom)) {
            user.update(message, sendFrom);
        }
    }

}
