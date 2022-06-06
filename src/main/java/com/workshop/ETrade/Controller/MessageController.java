package com.workshop.ETrade.Controller;

import com.workshop.ETrade.Domain.Notifications.Notification;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController implements ApplicationContextAware {

     @Autowired
     static ApplicationContext myContext;
        @Autowired
        private SimpMessagingTemplate smt;


     public static ApplicationContext getAppContext() {
         return myContext;
     }
     @Override
     public void setApplicationContext(ApplicationContext context) throws BeansException {
         myContext = context;
     }
     public void sendNotification(Notification notification){
         smt.convertAndSend("/topic/"+notification.getSentTo(),notification);
     }
 }
