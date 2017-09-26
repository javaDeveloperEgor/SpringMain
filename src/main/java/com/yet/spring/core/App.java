package com.yet.spring.core;


import com.yet.spring.core.beans.Client;
import com.yet.spring.core.loggers.ConsoleEventLogger;
import com.yet.spring.core.beans.Events;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
   private Client client;
   private ConsoleEventLogger eventLogger;

    public App(Client client, ConsoleEventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    public static void main(String[] args) {
        @SuppressWarnings("resource")
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App)ctx.getBean("app");

        Events event = ctx.getBean(Events.class);
        app.logEvent(event,"Some event 1");

        event = ctx.getBean(Events.class);
        app.logEvent(event,"Some event 2");

        ctx.close();
    }

    private void logEvent(Events event, String msg){
            String message = msg.replaceAll(client.getId(), client.getFullName());
            event.setMsg(msg);
            eventLogger.logEvent(event);
        }
}
