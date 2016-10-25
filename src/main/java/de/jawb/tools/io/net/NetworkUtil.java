package de.jawb.tools.io.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

    public static String readFromStream(InputStream inputStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
        String inputLine;
        Appendable response = new StringBuilder(); // new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine.trim());
        }
        in.close();

        return response.toString();
    }

}
