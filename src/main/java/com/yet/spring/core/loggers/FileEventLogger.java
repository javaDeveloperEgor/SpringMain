package com.yet.spring.core.loggers;


import com.yet.spring.core.beans.Events;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileEventLogger implements EventLogger {
    private String filename;
    private File file;

    public FileEventLogger(String filename) {
        this.filename = filename;
    }

    public void init(){
        file = new File(filename);
        if (file.exists() && !file.canWrite()){
            throw new IllegalArgumentException("Can't write file " + filename);
        }else if (!file.exists()){
            try {
                file.createNewFile();
            }catch (Exception e){
                throw new IllegalArgumentException("Can't create file", e);
            }
        }
    }

    public void logEvent(Events event) {
        try {
            FileUtils.writeStringToFile(file, event.toString(), true);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
