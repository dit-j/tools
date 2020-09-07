package de.jawb.tools.http;

/**
 * @author dit
 */
public enum HttpRequestMethod {

    GET(
            true),
    DELETE(
            false),
    HEAD(
            true),
    POST(
            false),
    PUT(
            false);

    final boolean canBeCached;

//    private HttpRequestMethod(boolean mayHaveBody, boolean canBeCached) {
//        this.mayHaveBody = mayHaveBody;
//        this.canBeCached = canBeCached;
//    }

    private HttpRequestMethod(boolean canBeCached) {
        this.canBeCached = canBeCached;
    }
}
