/**
 * 
 */
package de.jawb.tools.util.io.network;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author dit (01.02.2015)
 */
public class NetworkUtil {
    
    public static String getLocalhostIP() {
        try {
            InetAddress localHost = Inet4Address.getLocalHost();
            return localHost.getHostAddress();
        } catch (UnknownHostException e) {
        }
        return "UNKNOWN";
    }
    
    
    public static void main(String[] args) {
        System.out.println(getLocalhostIP());
    }
}
