package de.jawb.tools.util.pattern.threadpool;

/**
 * @author dit (19.10.2012)
 */
public abstract class Job implements Runnable {
    
    private String _name;
    
    /**
     * @param name
     *            Name oder ID der Aufgabe
     */
    public Job(String name) {
        _name = name;
    }
    
    /**
     * Wird zuerst ausgefuehrt.
     */
    protected void doOnStart() {
        //
    }
    
    /**
     * Wird ausgefuehrt nachdem die Aufgabe erledigt wurde.
     */
    protected void doFinally() {
        //
    }
    
    /**
     * Methode zum Anhalten des Threads
     */
    public void stop() {
        //
    }
    
    /**
     * Eigentliche Aufgabe
     */
    protected abstract void job();
    
    @Override
    public void run() {
        doOnStart();
        job();
        doFinally();
    }
    
    @Override
    public String toString() {
        return _name;
    }
}
