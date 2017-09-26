package com.yet.spring.core.loggers;


import com.yet.spring.core.beans.Events;

import java.util.ArrayList;
import java.util.List;

public class CacheFileEventLogger extends FileEventLogger {
    private int cacheSize;
    private List<Events> cache;

    public CacheFileEventLogger(String filename, int cacheSize) {
        super(filename);
        this.cacheSize = cacheSize;
        this.cache = new ArrayList<Events>(cacheSize);
    }

    @Override
    public void logEvent(Events event) {
       cache.add(event);
          if (cache.size() == cacheSize){
            writeEventsFromCache();
            cache.clear();
       }
    }

    public void destroy(){
        if (!cache.isEmpty())
            writeEventsFromCache();
    }

    private void writeEventsFromCache(){
      cache.stream().forEach(super::logEvent);
    }
}
