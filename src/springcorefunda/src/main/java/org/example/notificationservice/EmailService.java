package org.example.notificationservice;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class EmailService implements NotificationService {
    @Override
    public void sendMsg(String msg) {
        System.out.println("E-MAIL SERVICES"+msg);
    }
}
