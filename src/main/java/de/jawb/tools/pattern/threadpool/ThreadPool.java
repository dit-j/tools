/**
 * 
 */
package de.jawb.tools.pattern.threadpool;

import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 5 Threads stehen zur Verfuegung.<br>
 * Wenn noetig, wird ein oder mehrere neue Threads erstellt.
 * 
 * @author dit (03.08.2011)
 */
public class ThreadPool {
    
    /**
     * Anzahl der Threads in dem Pool.
     */
    private static int                POOL_SIZE = 5;
    private static ThreadPoolExecutor _executorService;
    
    static {
        _executorService = new ThreadPoolExecutor(POOL_SIZE, //Anzahl der Threads
                Integer.MAX_VALUE, // Maximale Anzahl der Thread bei auslastung
                10L, TimeUnit.SECONDS, // nach 60 Sekunden alle inaktive Threads beenden
                new SynchronousQueue<Runnable>() //Warteschlange.
                );
        
    }
    
    public static final void execute(Runnable job) {
//        System.out.println("|~| " + ThreadPool.class.getSimpleName() + ": Neue Aufgabe - " + job);
        if (!_executorService.isShutdown()) {
//            Log.info(getInfo());
            _executorService.execute(job);
        } else {
//            System.out.println("|~| " + ThreadPool.class.getSimpleName()
//                    + " wurde beendet. Aufgabe '" + job + "' wird nicht bearbeitet...");
        }
    }
    
    public static final void execute(Job job) {
//        System.out.println("|~| " + ThreadPool.class.getSimpleName() + ": Neue Aufgabe - " + job);
        if (!_executorService.isShutdown()) {
//            Log.info(getInfo());
            _executorService.execute(job);
        } else {
//            System.out.println("|~| " + ThreadPool.class.getSimpleName()
//                    + " wurde beendet. Aufgabe '" + job + "' wird nicht bearbeitet...");
        }
    }
    
    public static final <V> Future<V> executCallable(Callable<V> task) {
        return _executorService.submit(task);
    }
    
    public static final void shutdownNow() {
        _executorService.shutdownNow();
        try {
            _executorService.awaitTermination(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
//            e.printStackTrace();
        }
    }
    
    public static final String getInfo() {
        StringBuffer sb = new StringBuffer();
        sb.append("|~| " + ThreadPool.class.getSimpleName());
        sb.append(": InitPoolGröße - ");
        sb.append(_executorService.getCorePoolSize() + ", Aktuelle Poolgröße - ");
        sb.append(_executorService.getPoolSize() + ", Aktuell aktiv - ");
        sb.append(_executorService.getActiveCount() + ", Erledigte Aufgaben: ");
        sb.append(_executorService.getCompletedTaskCount() + ", KeepAliveTime: ");
        sb.append(_executorService.getKeepAliveTime(TimeUnit.SECONDS) + " sekunden.");
        
        return sb.toString();
    }
}
