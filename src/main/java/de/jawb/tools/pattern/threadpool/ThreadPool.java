/**
 *
 */
package de.jawb.tools.pattern.threadpool;

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
    private static int                POOLSIZE = 5;
    private static ThreadPoolExecutor executorService;

    static {
        executorService = new ThreadPoolExecutor(POOLSIZE, // Anzahl der Threads
                Integer.MAX_VALUE, // Maximale Anzahl der Thread bei auslastung
                10L, TimeUnit.SECONDS, // nach 60 Sekunden alle inaktive Threads beenden
                new SynchronousQueue<Runnable>() // Warteschlange.
                );

    }

    public static final void execute(Runnable job) {
        if (!executorService.isShutdown()) {
            executorService.execute(job);
        } else {

        }
    }

    public static final void execute(Job job) {
        if (!executorService.isShutdown()) {
            executorService.execute(job);
        } else {

        }
    }

    public static final <V> Future<V> executCallable(Callable<V> task) {
        return executorService.submit(task);
    }

    public static final void shutdownNow() {
        executorService.shutdownNow();
        try {
            executorService.awaitTermination(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static final String getInfo() {
        StringBuffer sb = new StringBuffer();
        sb.append("|~| " + ThreadPool.class.getSimpleName());
        sb.append(": Initial pool size - ");
        sb.append(executorService.getCorePoolSize() + ", Current  pool size- ");
        sb.append(executorService.getPoolSize() + ", Aktuell aktiv - ");
        sb.append(executorService.getActiveCount() + ", Erledigte Aufgaben: ");
        sb.append(executorService.getCompletedTaskCount() + ", KeepAliveTime: ");
        sb.append(executorService.getKeepAliveTime(TimeUnit.SECONDS) + " sekunden.");

        return sb.toString();
    }
}
