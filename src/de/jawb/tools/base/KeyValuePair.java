package de.jawb.tools.base;

/**
 * @author dit
 */
public class KeyValuePair {
    
    private String key, value;
    
    public KeyValuePair() {
    }
    
    public KeyValuePair(String key, String value) {
        super();
        this.key = key;
        this.value = value;
    }
    
    public String getKey() {
        return key;
    }
    
    public void setKey(String key) {
        this.key = key;
    }
    
    public String getValue() {
        return value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    
    @Override
    public String toString() {
        return "KeyValuePair [key=" + key + ", value=" + value + "]";
    }
}
