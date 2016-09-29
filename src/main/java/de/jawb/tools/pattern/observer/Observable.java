/**
 * 
 */
package de.jawb.tools.pattern.observer;

/**
 * Ein Objekt das beobachtet werden soll.
 * 
 * @author dit (29.07.2011)
 */
public interface Observable {
    
    /**
     * Fuegt neuen Beobachter hinzu.
     * 
     * @param observer
     *            Beobachter
     */
    void checkIn(Observer observer);
    
    /**
     * Entfernt (meldet ab) einen Beobachter.
     * 
     * @param observer
     *            Beobachter.
     */
    void checkOut(Observer observer);
}
