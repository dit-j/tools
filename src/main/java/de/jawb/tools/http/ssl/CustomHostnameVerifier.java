package de.jawb.tools.http.ssl;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/**
 * @author dit
 */
public class CustomHostnameVerifier implements HostnameVerifier {

    private final String hostName;

    public CustomHostnameVerifier(String hostName) {
        super();
        this.hostName = hostName;
    }

    @Override
    public boolean verify(String hostname, SSLSession session) {
        return hostName.equals(hostname);
    }

}
