package Domain.Notifications;

import java.time.LocalDate;

public class Notification {
    private LocalDate sentAt;
    private String sentFrom;
    private String message;
    private String sentTo;

    public Notification(LocalDate sentAt, String sentFrom, String message, String sentTo) {
        this.sentAt = sentAt;
        this.sentFrom = sentFrom;
        this.message = message;
        this.sentTo = sentTo;
    }

    public LocalDate getSentAt() {
        return sentAt;
    }

    public String getSentFrom() {
        return sentFrom;
    }

    public String getMessage() {
        return message;
    }

    public String getSentTo() {
        return sentTo;
    }

    public String toString(){
        return message;
    }
}
