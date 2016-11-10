package de.jawb.tools.io.net;

import static de.jawb.tools.string.StringUtil.isEmpty;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.jawb.tools.collections.CollectionsUtil;

public class NetworkUtil {

    public static String getLocalhostIP() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            return null;
        }
    }

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

    @Deprecated
    public static String readFromStream(InputStream inputStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
        String inputLine;
        Appendable response = new StringBuilder(); // new StringBuilder();
        boolean data = false;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine.trim());
            data = true;
        }
        in.close();

        return data ? response.toString() : null;
    }

}
