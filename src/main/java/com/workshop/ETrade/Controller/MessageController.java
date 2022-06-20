package com.workshop.ETrade.Controller;

import com.workshop.ETrade.Domain.Notifications.Notification;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;

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
     public int sendNotification(Notification notification){
         try {
             smt.convertAndSend("/topic/" + notification.getSentTo(), notification);
             return 0;
         }catch (MessagingException me){
             return -1;
         }
     }
     public void updateStats(){
         smt.convertAndSend("/topic/stats",new Notification(LocalDate.now(),"","",""));
     }
 }
