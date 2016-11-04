package de.jawb.tools.io.http.ssl;

import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

public class MultiHostnameVerifier implements HostnameVerifier {

    private List<HostnameVerifier> verifiers;

    public MultiHostnameVerifier(List<HostnameVerifier> verifiers) {
        super();
        this.verifiers = verifiers;
    }

    @Override
    public boolean verify(String hostname, SSLSession session) {
        for (HostnameVerifier v : verifiers) {
            if (!v.verify(hostname, session)) {
                return false;
            }
        }
        return true;
    }

}
