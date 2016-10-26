package de.jawb.tools.io.http;

import java.util.Map;
import java.util.Map.Entry;

import de.jawb.tools.reflection.ReflectionUtil;
import de.jawb.tools.string.StringUtil;

public class HttpRequestFactory {

    public static HttpRequest createGETFromBean(String baseUrl, Object requestParameters) {

        HttpRequest request = new HttpRequest(baseUrl);

        Map<String, Object> params = ReflectionUtil.beanToMap(requestParameters);
        for (Entry<String, Object> entry : params.entrySet()) {
            Object value = entry.getValue();
            if (value != null) {
                String valueAsStr = value.toString();
                if (StringUtil.notEmpty(valueAsStr)) {
                    request.addParameter(entry.getKey(), valueAsStr);
                }
            }
        }

        return request;
    }

}
