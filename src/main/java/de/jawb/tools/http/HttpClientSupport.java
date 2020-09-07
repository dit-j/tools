package de.jawb.tools.http;

import de.jawb.tools.http.ssl.SSLConfiguration;
import de.jawb.tools.io.net.NetworkUtil;
import de.jawb.tools.logging.ISimpleLogger;
import de.jawb.tools.logging.NopLogger;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.HttpURLConnection;

class HttpClientSupport {

    private ISimpleLogger logger = new NopLogger();

    protected final void setSimpleLogger(ISimpleLogger logger){
        this.logger = logger;
    }

    protected HttpResponse createResponse(HttpURLConnection connection) throws IOException {

        int responseCode = connection.getResponseCode();
        String message   = connection.getResponseMessage();

        logGotResponseCode(responseCode, message);

        String data      = getData(responseCode, connection);

        return new HttpResponse(responseCode, message, data, NetworkUtil.getResponseHeaders(connection));
    }

    private String getData(int rCode, HttpURLConnection connection) throws IOException {

        if (rCode >= 300) {
            if (connection.getErrorStream() != null) {
                return NetworkUtil.read(connection.getErrorStream());
            }
        } else {
            if (connection.getInputStream() != null) {
                return NetworkUtil.read(connection.getInputStream());
            }
        }

        return null;
    }

    protected void logWarnNoSslConfiguration() {
        if(logger.isEnabled()){
            logger.warn("no SSLConfiguration specified. Using default.");
        }
    }

    protected void logErrorSSLSocketFactory(Exception e) {
        if(logger.isEnabled()){
            logger.error(e);
        }
    }

    protected void logInitBodyData() {
        if (logger.isEnabled()) {
            logger.debug("init request body");
        }
    }

    protected void logSSLConfiguration(SSLConfiguration configs) {
        if (logger.isEnabled()) {
            logger.debug("using SSLConfiguration: " + configs);
        }
    }

    protected void logSetTimeout(int timeout) {
        if (logger.isEnabled()) {
            logger.debug("set connection and read timeout: " +  timeout);
        }
    }

    protected void logConnectionOpened(HttpURLConnection connection) {
        if (logger.isEnabled()) {
            boolean ssl = connection instanceof HttpsURLConnection;
            logger.debug("connection opened. ssl=" + ssl);
        }
    }

    protected void logMethodCall(HttpRequest request) {
        if (logger.isEnabled()) {
            logger.debug("sendRequest: " + request);
        }
    }

    private void logGotResponseCode(int responseCode, String message) {
        if (logger.isEnabled()) {
            logger.debug("got response " + responseCode + " " + message);
        }
    }

}
