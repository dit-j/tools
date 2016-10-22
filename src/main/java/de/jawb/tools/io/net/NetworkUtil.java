package de.jawb.tools.io.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class NetworkUtil {

    public static String getLocalhostIP() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            return null;
        }
    }
}
