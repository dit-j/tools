package de.jawb.tools.io.net;

import static de.jawb.tools.string.StringUtil.isEmpty;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.jawb.tools.collections.CollectionsUtil;

public class NetworkUtil {

    /**
     * @return
     */
    public static String getLocalhostIP() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            return null;
        }
    }

    public static String getHostNameByIP(String ip) {
        try {
            InetAddress inetAddress =InetAddress.getByName(ip);
            return inetAddress.getHostName();
        } catch (UnknownHostException e) {
            return null;
        }
    }

    /**
     * @param connection
     * @return
     */
    public static Map<String, String> getResponseHeaders(URLConnection connection) {
        Map<String, String> headerMap = new HashMap<>();
        Map<String, List<String>> map = connection.getHeaderFields();
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            String headerName = entry.getKey();
            String headerValue = CollectionsUtil.join(entry.getValue(), ",");
            if (headerValue != null) {
                headerMap.put(headerName, headerValue);
            }
        }
        return headerMap;
    }

    /**
     * @param io
     * @return
     * @throws IOException
     */
    public static String read(InputStream io) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = io.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        String rawData = result.toString("UTF-8").trim();
        return isEmpty(rawData) ? null : rawData;
    }

    public static void main(String[] args) {
        System.out.println(getHostNameByIP("172.217.21.46"));
    }

}
