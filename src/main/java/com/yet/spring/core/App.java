package com.yet.spring.core;


import com.yet.spring.core.beans.Client;
import com.yet.spring.core.beans.EventType;
import com.yet.spring.core.loggers.ConsoleEventLogger;
import com.yet.spring.core.beans.Events;
import com.yet.spring.core.loggers.EventLogger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class App {
   private Client client;
   private EventLogger defaultLogger;

   private Map<EventType, EventLogger> mapLoggers;

    public App(Client client, EventLogger eventLogger, Map<EventType, EventLogger> mapLoggers) {
        this.client = client;
        this.defaultLogger = eventLogger;
        this.mapLoggers = mapLoggers;
    }

    public static void main(String[] args) {
        @SuppressWarnings("resource")
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App)ctx.getBean("app");

        Events event = ctx.getBean(Events.class);
        app.logEvent(EventType.ERROR, event,"Some event 1");

        event = ctx.getBean(Events.class);
        app.logEvent(EventType.INFO, event,"Some event 2");

        ctx.close();
    }

    private void logEvent(EventType eventType, Events event, String msg){
            String message = msg.replaceAll(client.getId(), client.getFullName());
            event.setMsg(msg);

            EventLogger eventLogger = mapLoggers.get(eventType);

            if (eventLogger == null){
                eventLogger = defaultLogger;
            }

            eventLogger.logEvent(event);

        }
}
