package de.jawb.tools.time;

import de.jawb.tools.date.DateUtil;

public class StopWatch {
    
    private long start, end;
    
    public StopWatch() {
        start();
    }
    
    public StopWatch(boolean start) {
        if (start) start();
    }
    
    public void start() {
        start = System.currentTimeMillis();
        end   = 0;
    }
    
    public long stop() {
        if (end == 0) {
            end = System.currentTimeMillis();
        }
        return end - start;
    }
    
    public String duration() {
        return DateUtil.getDurationFromMillis(stop());
    }
    
}
