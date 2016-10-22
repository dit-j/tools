package de.jawb.tools.mail.conf.props;

import javax.mail.PasswordAuthentication;

import de.jawb.tools.mail.interfaces.ConfigurationProperty;


/**
 * @author dit (02.09.2012)
 */
public enum AUTH_Prop implements ConfigurationProperty {
    /**
     * <b>Type: String</b><br>
     * <br>
     * User Name for {@link PasswordAuthentication}
     */
    USER(
            "auth.user"),
    /**
     * <b>Type: String</b><br>
     * <br>
     * Password for {@link PasswordAuthentication}
     */
    PASS(
            "auth.pass");
    
    private String _key;
    
    private AUTH_Prop(String key) {
        _key = key;
    }
    
    @Override
    public String key() {
        return _key;
    }
    
}
