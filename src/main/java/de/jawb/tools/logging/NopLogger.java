package de.jawb.tools.logging;

public class NopLogger implements ISimpleLogger {

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public void debug(String msg) {
    }

    @Override
    public void warn(String msg) {
    }

    @Override
    public void error(String msg) {
    }

    @Override
    public void error(Throwable e) {
    }

    @Override
    public void disable() {
    }

    @Override
    public void enable() {
    }

}
