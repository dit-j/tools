package de.jawb.tools.io.http;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Arrays;

import javax.net.ssl.HttpsURLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.jawb.tools.io.http.ssl.SSLConfiguration;
import de.jawb.tools.io.net.NetworkUtil;

class HttpClientSupport {

    private final Logger _logger = LoggerFactory.getLogger(getClass());

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
        _logger.warn("no SSLConfiguration specified. Using default.");
    }

    protected void logErrorSSLSocketFactory(Exception e) {
        _logger.error("error while setting SSLSocketFactory", e);
    }

    protected void logInitBodyData() {
        if (_logger.isDebugEnabled()) {
            _logger.debug("init request body");
        }
    }

    protected void logSSLConfiguration(SSLConfiguration configs) {
        if (_logger.isDebugEnabled()) {
            _logger.debug("using SSLConfiguration: {}", configs);
        }
    }

    protected void logSetTimeout(int timeout) {
        if (_logger.isDebugEnabled()) {
            _logger.debug("set connection and read timeout: {}", timeout);
        }
    }

    protected void logConnectionOpened(HttpURLConnection connection) {
        if (_logger.isDebugEnabled()) {
            boolean ssl = connection instanceof HttpsURLConnection;
            _logger.debug("connection opened. ssl={}", ssl);
        }
    }

    protected void logMethodCall(HttpRequest request, int... expectedResponseCodes) {
        if (_logger.isDebugEnabled()) {
            _logger.debug("sendRequest: {}, {}", request, Arrays.toString(expectedResponseCodes));
        }
    }

    private void logGotResponseCode(int responseCode, String message) {
        if (_logger.isDebugEnabled()) {
            _logger.debug("got response {} {}", responseCode, message);
        }
    }

}
