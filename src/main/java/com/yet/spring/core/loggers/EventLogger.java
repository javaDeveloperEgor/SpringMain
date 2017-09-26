package com.yet.spring.core.loggers;

import com.yet.spring.core.beans.Events;

public interface EventLogger {
    void logEvent(Events event);
}
