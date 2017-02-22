package de.jawb.tools.logging;

public interface ISimpleLogger {

    boolean isEnabled();

    void debug(String msg);
    void warn(String msg);
    void error(String msg);
    void error(Throwable e);

    void disable();
    void enable();

}