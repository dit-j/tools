package de.jawb.tools.util.service.mail.conf.props;

import de.jawb.tools.util.service.mail.interfaces.ConfigurationProperty;

/**
 * @author dit (15.06.2012)
 */
public enum Main_Prop implements ConfigurationProperty {
    /**
     * <b>Type: Boolean</b><br>
     * <br>
     * Include protocol authentication commands (including
     * usernames and passwords) in the debug output. Default
     * is false.
     */
    MAIL_DEBUG_AUTH(
            "mail.debug.auth"),
    
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * The initial debug mode. Default is false.
     */
    MAIL_DEBUG(
            "mail.debug"),
    /**
     * <b>Type: String</b><br>
     * <br>
     * The return email address of the current user, used by
     * the <code>InternetAddress</code> method
     * <code>getLocalAddress</code>.
     */
    MAIL_FROM(
            "mail.from"),
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * The MimeMessage class uses the
     * <code>InternetAddress</code> method
     * <code>parseHeader</code> to parse headers in
     * messages. This property controls the strict flag
     * passed to the <code>parseHeader</code> method. The
     * default is true.
     */
    MAIL_MIME_ADDRESS_STRICT(
            "mail.mime.address.strict"),
    /**
     * <b>Type: String</b><br>
     * <br>
     * The default host name of the mail server for both
     * Stores and Transports. Used if the
     * <code>mail.<i>protocol</i>.host</code> property isn't
     * set.
     */
    MAIL_HOST(
            "mail.host"),
    /**
     * <b>Type: String</b><br>
     * <br>
     * Specifies the default message access protocol. The
     * <code>Session</code> method <code>getStore()</code>
     * returns a Store object that implements this protocol.
     * By default the first Store provider in the
     * configuration files is returned.
     */
    MAIL_STORE_PROTOCOL(
            "mail.store.protocol"),
    /**
     * <b>Type: String</b><br>
     * <br>
     * Specifies the default message transport protocol. The
     * <code>Session</code> method
     * <code>getTransport()</code> returns a Transport
     * object that implements this protocol. By default the
     * first Transport provider in the configuration files
     * is returned.
     */
    MAIL_TRANSPORT_PROTOCOL(
            "mail.transport.protocol"),
    /**
     * <b>Type: String</b><br>
     * <br>
     * The default user name to use when connecting to the
     * mail server. Used if the
     * <code>mail.<i>protocol</i>.user</code> property isn't
     * set.
     */
    MAIL_USER(
            "mail.user");
    
    private String _key;
    
    private Main_Prop(String key) {
        _key = key;
    }
    
    @Override
    public String key() {
        return _key;
    }
}
