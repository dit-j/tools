package de.jawb.tools.http.ssl;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dit
 */
public class SSLConfiguration {

    private HostnameVerifier hostNameVerifier;
    private SSLSocketFactory sslSocketFactory;


    private SSLConfiguration(HostnameVerifier hostNameVerifier, SSLSocketFactory sslSocketFactory) {
        super();
        this.hostNameVerifier = hostNameVerifier;
        this.sslSocketFactory = sslSocketFactory;
    }

    public HostnameVerifier getHostNameVerifier() {
        return hostNameVerifier;
    }

    public SSLSocketFactory getSslSocketFactory() {
        return sslSocketFactory;
    }

    /**
     * @author dit
     */
    public static class Builder {

        private List<HostnameVerifier> hostNameVerifiers = new ArrayList<>();
        private List<X509TrustManager> trustManagers     = new ArrayList<>();

        public Builder addHostNameVerifier(HostnameVerifier verifier) {
            hostNameVerifiers.add(verifier);
            return this;
        }

        public Builder addTrustManager(X509TrustManager trustManager) {
            trustManagers.add(trustManager);
            return this;
        }

        public SSLConfiguration build() {

            SSLSocketFactory socketFactory = null;
            HostnameVerifier verifier = null;

            if (hostNameVerifiers.size() > 0) {
                if (hostNameVerifiers.size() == 1) {
                    verifier = hostNameVerifiers.get(0);
                } else {
                    verifier = new MultiHostnameVerifier(hostNameVerifiers);
                }
            }

            try {

                final int trustedManagersSize = trustManagers.size();
                if (trustedManagersSize > 0) {
                    SSLContext sc = SSLContext.getInstance("SSL");
                    X509TrustManager[] managers = trustManagers.toArray(new X509TrustManager[trustedManagersSize]);
                    sc.init(null, managers, new SecureRandom());

                    socketFactory = sc.getSocketFactory();
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            return new SSLConfiguration(verifier, socketFactory);
        }
    }

}
