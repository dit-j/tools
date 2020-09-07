package de.jawb.tools.time;

import de.jawb.tools.date.DateUtil;
import de.jawb.tools.date.DurationStyle;
import de.jawb.tools.date.Style;

public class StopWatch {

    private long start, end;

    public StopWatch() {
    }

    public StopWatch(boolean startNow) {
        if (startNow) start();
    }

    public StopWatch start() {

        start = System.currentTimeMillis();
        end   = 0;

        return this;
    }

    public StopWatch stop() {
        if (end == 0) {
            end = System.currentTimeMillis();
        }
        return this;
    }

    public long duration(){
        if(end == 0){
            return System.currentTimeMillis() - start;
        }
        return end - start;
    }

    public String readableDuration(){
        return DateUtil.toReadableString(DurationStyle.Full, duration());
    }

    @Override
    public String toString() {

        if(start == 0){
            return "StopWatch [not started]";
        }

        String s = "StopWatch [" + DateUtil.dateToString(start, Style.DateTime) + " - ";

        if(end > 0){
            s += DateUtil.dateToString(end, Style.DateTime);
        }else {
            s += " ...";
        }
        s+= "] - " + readableDuration();
        return s;
    }

}
