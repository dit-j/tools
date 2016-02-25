/**
 * 
 */
package de.jawb.tools.util.pattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Eine StandardImplementierung der {@link Observable}-Schnittstelle.
 * 
 * @author dit (29.07.2011)
 */
public abstract class DefaultObserverable implements Observable {
    
    protected List<Observer> _observers;
    
    /**
     * StandardKonstruktor.
     */
    public DefaultObserverable() {
        _observers = new ArrayList<Observer>(4);
    }
    
    @Override
    public void checkIn(Observer observer) {
        _observers.add(observer);
    }
    
    @Override
    public void checkOut(Observer observer) {
        _observers.remove(observer);
    }
    
    /**
     * Aktualisiert alle Beobachter.
     * 
     * @param object
     *            Ein Objekt das die neuen Informationen darstellt.
     */
    protected void updateAll(Object object) {
        for (Observer observer : _observers) {
            observer.update(object);
        }
    }
    
}
