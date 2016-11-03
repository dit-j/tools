package de.jawb.tools.io.http;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

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
