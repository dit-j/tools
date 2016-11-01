package de.jawb.tools.mail.conf;

import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import de.jawb.tools.mail.interfaces.ConfigurationProperty;

/**
 * @author dit (14.06.2012)
 */
public class MailServiceConfiguration {

    private Properties _properties;

    public MailServiceConfiguration() {
        _properties = System.getProperties();
    }

    public Properties getProperties() {
        return _properties;
    }

    public MailServiceConfiguration addProperty(ConfigurationProperty prop, String value) {
        _properties.put(prop.key(), value);
        return this;
    }

    public String getValue(ConfigurationProperty prop) {
        return _properties.getProperty(prop.key());
    }

    @Override
    public String toString() {
        Set<Object> set = _properties.keySet();
        Iterator<Object> it = set.iterator();
        StringBuilder sb = new StringBuilder();
        Object next;
        while (it.hasNext()) {
            next = it.next();
            if (next.toString().startsWith("mail.")) {
                sb.append(next);
                int a = 30 - next.toString().length();
                for (int i = 0; i < a; i++) {
                    sb.append(" ");
                }
                sb.append(": ");
                sb.append(_properties.get(next));
                sb.append("\n");
            }
        }

        return "MailServiceConfiguration [\n" + sb + "]";
    }

}
