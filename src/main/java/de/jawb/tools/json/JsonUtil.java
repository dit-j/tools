package de.jawb.tools.json;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class JsonUtil {

    private static ObjectMapper _init() {
        ObjectMapper m = new ObjectMapper();
        m.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        m.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        m.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        m.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
//        m.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
        return m;
    }

    public static JsonNode toJsonNode(String json) throws IOException {
        return _init().readTree(json);
    }

    public static <K, V> Map<K, V> toMap(String jsonString) {

        ObjectMapper m = _init();
        TypeReference<HashMap<K, V>> typeRef = new TypeReference<HashMap<K, V>>() {
        };
        Map<K, V> map = null;
        try {
            map = m.readValue(jsonString, typeRef);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    public static <K, V> List<Map<K, V>> toListOfMaps(JsonNode node) {

        ObjectMapper m = _init();
        TypeReference<List<Map<K, V>>> typeRef = new TypeReference<List<Map<K, V>>>() {
        };
        List<Map<K, V>> list = null;
        try {
            list = m.readValue(node.traverse(), typeRef);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public static <K, V> List<Map<K, V>> toListOfMaps(String json) {

        ObjectMapper m = _init();
        TypeReference<List<Map<K, V>>> typeRef = new TypeReference<List<Map<K, V>>>() {
        };
        List<Map<K, V>> list = null;
        try {
            list = m.readValue(json, typeRef);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public static <K, V> Map<K, V> toMapOfType(String jsonString, Class<V> clazz) {

        ObjectMapper m = _init();
        Map<K, V> map = null;
        try {
            map = m.readValue(jsonString, TypeFactory.defaultInstance().constructMapLikeType(HashMap.class, String.class, clazz));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    public static <K, V> Map<K, V> toMapOfType(String jsonString, Class<K> keyClass, Class<V> valueClass) {

        ObjectMapper m = _init();
        Map<K, V> map = null;
        try {
            map = m.readValue(jsonString, TypeFactory.defaultInstance().constructMapLikeType(LinkedHashMap.class, keyClass, valueClass));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    public static <T> List<T> toList(String jsonString, Class<T> clazz) {
        ObjectMapper m = _init();
        List<T> list = null;
        try {
            list = m.readValue(jsonString, TypeFactory.defaultInstance().constructCollectionType(List.class, clazz));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public static byte[] toJSONBytes(Object o) {
        try {
            return _init().writeValueAsBytes(o);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String toJSON(Object o) {
        return toJSON(o, false);
    }

    public static String toJSON(Object o, boolean prettyPrint) {
        ObjectMapper mapper = _init();
        if (prettyPrint) {
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
        }
        try {
            return mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T parse(String json, Class<T> clazz) {
        try {
            return _init().readValue(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T parse(JsonNode data, Class<T> clazz) {
        try {
            return _init().readValue(data.traverse(), clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T parse(File src, Class<T> clazz) {
        try {
            return _init().readValue(src, clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
