package de.jawb.tools.io.http;

import java.util.HashMap;
import java.util.Map;

/**
 * Ermoeglich das Cachen von {@link HttpResponse}-Objekten um so die unnoetigen
 * Serveranfragen zu vermeiden.
 *
 * @author dit
 */
public class HttpResponseCache {

    private final Map<String, CacheEntry> cache = new HashMap<>();

    public void put(String uid, HttpResponse response, long maxAgeInMs) {
        if (get(uid) == null) {
            cache.put(uid, new CacheEntry(response, maxAgeInMs));
        }
    }

    public HttpResponse get(String uid) {
        CacheEntry entry = cache.get(uid);

        if (entry == null) return null;

        if (entry.isOutdated()) {
            cache.remove(uid);
            return null;
        }

        return entry.response;
    }

    public void clear() {
        cache.clear();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("HttpResponseCache [cache=");
        builder.append(cache);
        builder.append("]");
        return builder.toString();
    }

    private static class CacheEntry {

        HttpResponse response;
        long         timestamp;
        long         maxAgeInMs;

        public CacheEntry(HttpResponse response, long maxAgeInMs) {
            super();
            this.response = response;
            this.timestamp = System.currentTimeMillis();
            this.maxAgeInMs = maxAgeInMs;
        }

        boolean isOutdated() {
            long age = System.currentTimeMillis() - timestamp;
            return age > maxAgeInMs;
        }
    }
}
