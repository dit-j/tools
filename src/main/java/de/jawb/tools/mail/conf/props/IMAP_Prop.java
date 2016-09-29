package de.jawb.tools.mail.conf.props;

import de.jawb.tools.mail.interfaces.ConfigurationProperty;

/**
 * @author dit (14.06.2012)
 */
public enum IMAP_Prop implements ConfigurationProperty {
    /**
     * <b>Type: String</b><br>
     * <br>
     * Default user name for IMAP.
     */
    MAIL_IMAP_USER(
            "mail.imap.user"),
    /**
     * <b>Type: String</b><br>
     * <br>
     * The IMAP server to connect to.
     */
    MAIL_IMAP_HOST(
            "mail.imap.host"),
    /**
     * <b>Type: int</b><br>
     * <br>
     * The IMAP server port to connect to, if the connect()
     * method doesn't explicitly specify one. Defaults to
     * 143.
     */
    MAIL_IMAP_PORT(
            "mail.imap.port"),
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * Controls whether the IMAP partial-fetch capability
     * should be used. Defaults to true.
     */
    MAIL_IMAP_PARTIALFETCH(
            "mail.imap.partialfetch"),
    /**
     * <b>Type: int</b><br>
     * <br>
     * Partial fetch size in bytes. Defaults to 16K.
     */
    MAIL_IMAP_FETCHSIZE(
            "mail.imap.fetchsize"),
    /**
     * <b>Type: int</b><br>
     * <br>
     * Socket connection timeout value in milliseconds.
     * Default is infinite timeout.
     */
    MAIL_IMAP_CONNECTIONTIMEOUT(
            "mail.imap.connectiontimeout"),
    /**
     * <b>Type: int</b><br>
     * <br>
     * Socket I/O timeout value in milliseconds. Default is
     * infinite timeout.
     */
    MAIL_IMAP_TIMEOUT(
            "mail.imap.timeout"),
    /**
     * <b>Type: int</b><br>
     * <br>
     * Timeout value in milliseconds for cache of STATUS
     * command response. Default is 1000 (1 second). Zero
     * disables cache.
     */
    MAIL_IMAP_STATUSCACHETIMEOUT(
            "mail.imap.statuscachetimeout"),
    /**
     * <b>Type: int</b><br>
     * <br>
     * Maximum size of a message to buffer in memory when
     * appending to an IMAP folder. If not set, or set to
     * -1, there is no maximum and all messages are
     * buffered. If set to 0, no messages are buffered. If
     * set to (e.g.) 8192, messages of 8K bytes or less are
     * buffered, larger messages are not buffered. Buffering
     * saves cpu time at the expense of short term memory
     * usage. If you commonly append very large messages to
     * IMAP mailboxes you might want to set this to a
     * moderate value (1M or less).
     */
    MAIL_IMAP_APPENDBUFFERSIZE(
            "mail.imap.appendbuffersize"),
    /**
     * <b>Type: int</b><br>
     * <br>
     * Maximum number of available connections in the
     * connection pool. Default is 1.
     */
    MAIL_IMAP_CONNECTIONPOOLSIZE(
            "mail.imap.connectionpoolsize"),
    /**
     * <b>Type: int</b><br>
     * <br>
     * Timeout value in milliseconds for connection pool
     * connections. Default is 45000 (45 seconds).
     */
    MAIL_IMAP_CONNECTIONPOOLTIMEOUT(
            "mail.imap.connectionpooltimeout"),
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * Flag to indicate whether to use a dedicated store
     * connection for store commands. Default is false.
     */
    MAIL_IMAP_SEPARATESTORECONNECTION(
            "mail.imap.separatestoreconnection"),
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * If false, attempts to open a folder read/write will
     * fail if the SELECT command succeeds but indicates
     * that the folder is READ-ONLY. This sometimes
     * indicates that the folder contents can'tbe changed,
     * but the flags are per-user and can be changed, such
     * as might be the case for public shared folders. If
     * true, such open attempts will succeed, allowing the
     * flags to be changed. The <code>getMode</code> method
     * on the <code>Folder</code> object will return
     * <code>Folder.READ_ONLY</code> in this case even
     * though the <code>open</code> method specified
     * <code>Folder.READ_WRITE</code>. Default is false.
     */
    MAIL_IMAP_ALLOWREADONLYSELECT(
            "mail.imap.allowreadonlyselect"),
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * If true, prevents use of the non-standard
     * <code>AUTHENTICATE LOGIN</code> command, instead
     * using the plain <code>LOGIN</code> command. Default
     * is false.
     */
    MAIL_IMAP_AUTH_LOGIN_DISABLE(
            "mail.imap.auth.login.disable"),
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * If true, prevents use of the
     * <code>AUTHENTICATE PLAIN</code> command. Default is
     * false.
     */
    MAIL_IMAP_AUTH_PLAIN_DISABLE(
            "mail.imap.auth.plain.disable"),
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * If true, prevents use of the
     * <code>AUTHENTICATE NTLM</code> command. Default is
     * false.
     */
    MAIL_IMAP_AUTH_NTLM_DISABLE(
            "mail.imap.auth.ntlm.disable"),
    /**
     * <b>Type: String</b><br>
     * <br>
     * If the server supports the PROXYAUTH extension, this
     * property specifies the name of the user to act as.
     * Authenticate to the server using the administrator's
     * credentials. After authentication, the IMAP provider
     * will issue the <code>PROXYAUTH</code> command with
     * the user name specified in this property.
     */
    MAIL_IMAP_PROXYAUTH_USER(
            "mail.imap.proxyauth.user"),
    /**
     * <b>Type: String</b><br>
     * <br>
     * Local address (host name) to bind to when creating
     * the IMAP socket. Defaults to the address picked by
     * the Socket class. Should not normally need to be set,
     * but useful with multi-homed hosts where it's
     * important to pick a particular local address to bind
     * to.
     */
    MAIL_IMAP_LOCALADDRESS(
            "mail.imap.localaddress"),
    /**
     * <b>Type: int</b><br>
     * <br>
     * Local port number to bind to when creating the IMAP
     * socket. Defaults to the port number picked by the
     * Socket class.
     */
    MAIL_IMAP_LOCALPORT(
            "mail.imap.localport"),
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * If set to true, attempt to use the
     * javax.security.sasl package to choose an
     * authentication mechanism for login. Defaults to
     * false.
     */
    MAIL_IMAP_SASL_ENABLE(
            "mail.imap.sasl.enable"),
    /**
     * <b>Type: String</b><br>
     * <br>
     * A space or comma separated list of SASL mechanism
     * names to try to use.
     */
    MAIL_IMAP_SASL_MECHANISMS(
            "mail.imap.sasl.mechanisms"),
    /**
     * <b>Type: String</b><br>
     * <br>
     * The authorization ID to use in the SASL
     * authentication. If not set, the authentication ID
     * (user name) is used.
     */
    MAIL_IMAP_SASL_AUTHORIZATIONID(
            "mail.imap.sasl.authorizationid"),
    /**
     * <b>Type: String</b><br>
     * <br>
     * The realm to use with SASL authentication mechanisms
     * that require a realm, such as DIGEST-MD5.
     */
    MAIL_IMAP_SASL_REALM(
            "mail.imap.sasl.realm"),
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * If set to true, enables a workaround for a bug in the
     * Novell Groupwise XGWTRUSTEDAPP SASL mechanism, when
     * that mechanism is being used. Defaults to true.
     */
    MAIL_IMAP_SASL_XGWTRUSTEDAPPHACK_ENABLE(
            "mail.imap.sasl.xgwtrustedapphack.enable"),
    /**
     * <b>Type: String</b><br>
     * <br>
     * The NTLM authentication domain.
     */
    MAIL_IMAP_AUTH_NTLM_DOMAIN(
            "mail.imap.auth.ntlm.domain"),
    /**
     * <b>Type: int</b><br>
     * <br>
     * NTLM protocol-specific flags. See <A HREF=
     * "http://curl.haxx.se/rfc/ntlm.html#theNtlmFlags"
     * TARGET="_top">
     * http://curl.haxx.se/rfc/ntlm.html#theNtlmFlags</A>
     * for details.
     */
    MAIL_IMAP_AUTH_NTLM_FLAGS(
            "mail.imap.auth.ntlm.flags"),
    /**
     * <b>Type: SocketFactory</b><br>
     * <br>
     * If set to a class that implements the
     * <code>javax.net.SocketFactory</code> interface, this
     * class will be used to create IMAP sockets. Note that
     * this is an instance of a class, not a name, and must
     * be set using the <code>put</code> method, not the
     * <code>setProperty</code> method.
     */
    MAIL_IMAP_SOCKETFACTORY(
            "mail.imap.socketFactory"),
    /**
     * <b>Type: String</b><br>
     * <br>
     * If set, specifies the name of a class that implements
     * the <code>javax.net.SocketFactory</code> interface.
     * This class will be used to create IMAP sockets.
     */
    MAIL_IMAP_SOCKETFACTORY_CLASS(
            "mail.imap.socketFactory.class"),
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * If set to true, failure to create a socket using the
     * specified socket factory class will cause the socket
     * to be created using the <code>java.net.Socket</code>
     * class. Defaults to true.
     */
    MAIL_IMAP_SOCKETFACTORY_FALLBACK(
            "mail.imap.socketFactory.fallback"),
    /**
     * <b>Type: int</b><br>
     * <br>
     * Specifies the port to connect to when using the
     * specified socket factory. If not set, the default
     * port will be used.
     */
    MAIL_IMAP_SOCKETFACTORY_PORT(
            "mail.imap.socketFactory.port"),
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * If set to true, use SSL to connect and use the SSL
     * port by default. Defaults to false for the "imap"
     * protocol and true for the "imaps" protocol.
     */
    MAIL_IMAP_SSL_ENABLE(
            "mail.imap.ssl.enable"),
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * If set to true, check the server identity as
     * specified by <A
     * HREF="http://www.ietf.org/rfc/rfc2595.txt"
     * TARGET="_top">RFC 2595</A>. These additional checks
     * based on the content of the server's certificate are
     * intended to prevent man-in-the-middle attacks.
     * Defaults to false.
     */
    MAIL_IMAP_SSL_CHECKSERVERIDENTITY(
            "mail.imap.ssl.checkserveridentity"),
    /**
     * <b>Type: String</b><br>
     * <br>
     * If set, and a socket factory hasn't been specified,
     * enables use of a <A HREF=
     * "../../../../com/sun/mail/util/MailSSLSocketFactory.html"
     * title="class in com.sun.mail.util">
     * <CODE>MailSSLSocketFactory</CODE></A>. If set to "*",
     * all hosts are trusted. If set to a whitespace
     * separated list of hosts, those hosts are trusted.
     * Otherwise, trust depends on the certificate the
     * server presents.
     */
    MAIL_IMAP_SSL_TRUST(
            "mail.imap.ssl.trust"),
    /**
     * <b>Type: SSLSocketFactory</b><br>
     * <br>
     * If set to a class that extends the
     * <code>javax.net.ssl.SSLSocketFactory</code> class,
     * this class will be used to create IMAP SSL sockets.
     * Note that this is an instance of a class, not a name,
     * and must be set using the <code>put</code> method,
     * not the <code>setProperty</code> method.
     */
    MAIL_IMAP_SSL_SOCKETFACTORY(
            "mail.imap.ssl.socketFactory"),
    /**
     * <b>Type: String</b><br>
     * <br>
     * If set, specifies the name of a class that extends
     * the <code>javax.net.ssl.SSLSocketFactory</code>
     * class. This class will be used to create IMAP SSL
     * sockets.
     */
    MAIL_IMAP_SSL_SOCKETFACTORY_CLASS(
            "mail.imap.ssl.socketFactory.class"),
    /**
     * <b>Type: int</b><br>
     * <br>
     * Specifies the port to connect to when using the
     * specified socket factory. If not set, the default
     * port will be used.
     */
    MAIL_IMAP_SSL_SOCKETFACTORY_PORT(
            "mail.imap.ssl.socketFactory.port"),
    /**
     * <b>Type: string</b><br>
     * <br>
     * Specifies the SSL protocols that will be enabled for
     * SSL connections. The property value is a whitespace
     * separated list of tokens acceptable to the
     * <code>javax.net.ssl.SSLSocket.setEnabledProtocols</code>
     * method.
     */
    MAIL_IMAP_SSL_PROTOCOLS(
            "mail.imap.ssl.protocols"),
    /**
     * <b>Type: string</b><br>
     * <br>
     * Specifies the SSL cipher suites that will be enabled
     * for SSL connections. The property value is a
     * whitespace separated list of tokens acceptable to the
     * <code>javax.net.ssl.SSLSocket.setEnabledCipherSuites</code>
     * method.
     */
    MAIL_IMAP_SSL_CIPHERSUITES(
            "mail.imap.ssl.ciphersuites"),
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * If true, enables the use of the <code>STARTTLS</code>
     * command (if supported by the server) to switch the
     * connection to a TLS-protected connection before
     * issuing any login commands. Note that an appropriate
     * trust store must configured so that the client will
     * trust the server's certificate. This feature only
     * works on J2SE 1.4 and newer systems. Default is
     * false.
     */
    MAIL_IMAP_STARTTLS_ENABLE(
            "mail.imap.starttls.enable"),
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * If true, requires the use of the
     * <code>STARTTLS</code> command. If the server doesn't
     * support the STARTTLS command, or the command fails,
     * the connect method will fail. Defaults to false.
     */
    MAIL_IMAP_STARTTLS_REQUIRED(
            "mail.imap.starttls.required"),
    /**
     * <b>Type: string</b><br>
     * <br>
     * Specifies the host name of a SOCKS5 proxy server that
     * will be used for connections to the mail server.
     * (Note that this only works on JDK 1.5 or newer.)
     */
    MAIL_IMAP_SOCKS_HOST(
            "mail.imap.socks.host"),
    /**
     * <b>Type: string</b><br>
     * <br>
     * Specifies the port number for the SOCKS5 proxy
     * server. This should only need to be used if the proxy
     * server is not using the standard port number of 1080.
     */
    MAIL_IMAP_SOCKS_PORT(
            "mail.imap.socks.port"),
    /**
     * <b>Type: int</b><br>
     * <br>
     * Applications typically call the idle method in a
     * loop. If another thread termiantes the IDLE command,
     * it needs a chance to do its work before another IDLE
     * command is issued. The idle method enforces a delay
     * to prevent thrashing between the IDLE command and
     * regular commands. This property sets the delay in
     * milliseconds. If not set, the default is 10
     * milliseconds.
     */
    MAIL_IMAP_MINIDLETIME(
            "mail.imap.minidletime"),
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * Enable special IMAP-specific events to be delivered
     * to the Store's <code>ConnectionListener</code>. If
     * true, unsolicited responses received during the
     * Store's <code>idle</code> method will be sent as
     * <code>ConnectionEvent</code>s with a type of
     * <code>IMAPStore.RESPONSE</code>. The event's message
     * will be the raw IMAP response string. By default,
     * these events are not sent. NOTE: This capability is
     * highly experimental and likely will change in future
     * releases.
     */
    MAIL_IMAP_ENABLEIMAPEVENTS(
            "mail.imap.enableimapevents"),
    /**
     * <b>Type: String</b><br>
     * <br>
     * Class name of a subclass of
     * <code>com.sun.mail.imap.IMAPFolder</code>. The
     * subclass can be used to provide support for
     * additional IMAP commands. The subclass must have
     * public constructors of the form
     * <code>public MyIMAPFolder(String fullName, char separator, IMAPStore store,
     * Boolean isNamespace)</code> and
     * <code>public MyIMAPFolder(ListInfo li, IMAPStore store)</code>
     */
    MAIL_IMAP_FOLDER_CLASS(
            "mail.imap.folder.class");
    
    private final String _key;
    
    private IMAP_Prop(String key) {
        _key = key;
    }
    
    @Override
    public String key() {
        return _key;
    }
}
