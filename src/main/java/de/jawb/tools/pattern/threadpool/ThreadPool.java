/**
 *
 */
package de.jawb.tools.pattern.threadpool;

import java.util.concurrent.*;

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
    private static final int                POOLSIZE = 5;
    private static final ThreadPoolExecutor executorService;

    static {
        executorService = new ThreadPoolExecutor(POOLSIZE, // Anzahl der Threads
                Integer.MAX_VALUE, // Maximale Anzahl der Thread bei auslastung
                10L, TimeUnit.SECONDS, // nach 60 Sekunden alle inaktive Threads beenden
                new SynchronousQueue<Runnable>() // Warteschlange.
                );

    }

    public static void execute(Runnable job) {
        if (!executorService.isShutdown()) {
            executorService.execute(job);
        } else {

        }
    }

    public static void execute(Job job) {
        if (!executorService.isShutdown()) {
            executorService.execute(job);
        }
    }

    public static <V> Future<V> executCallable(Callable<V> task) {
        return executorService.submit(task);
    }

    public static void shutdownNow() {
        executorService.shutdownNow();
        try {
            executorService.awaitTermination(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String getInfo() {
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
