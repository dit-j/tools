package de.jawb.tools.http.ssl;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import java.util.List;

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
