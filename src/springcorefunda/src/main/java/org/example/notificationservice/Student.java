
package org.example.notificationservice;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.context.annotation.Scope;
@Scope("prototype")

@Component
public class Student {

    private final NotificationService notificationService;

    @Autowired
    public Student(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void notifyStudent() {
        notificationService.sendMsg(
                "Your ASSESSMENT->101 IS SCHEDULED."
        );
    }

    @PostConstruct
    public void init() {
        System.out.println("OBJECT CREATED");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("OBJECT DESTROYED");
    }
}
