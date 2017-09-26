package com.yet.spring.core.loggers;

import com.yet.spring.core.beans.Events;
import com.yet.spring.core.loggers.EventLogger;

public class ConsoleEventLogger implements EventLogger {
    public void logEvent(Events event){
        System.out.println(event.toString());
    }
}
