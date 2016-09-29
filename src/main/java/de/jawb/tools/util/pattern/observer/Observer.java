package de.jawb.tools.util.pattern.observer;

/**
 * Beobachter.
 * 
 * @author dit (29.07.2011)
 */
public interface Observer {
    
    /**
     * Aktualisiert den Observer.
     * 
     * @param object
     *            Neue Daten.
     */
    void update(Object object);
}
