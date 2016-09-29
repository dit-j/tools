package de.jawb.tools.util.service.mail.conf.props;

import de.jawb.tools.util.service.mail.interfaces.ConfigurationProperty;

/**
 * @author dit (14.06.2012)
 */
public enum POP3_Prop implements ConfigurationProperty {
    
    /**
     * <b>Type: String</b><br>
     * <br>
     * Default user name for POP3.
     */
    MAIL_POP3_USER(
            "mail.pop3.user"),
    /**
     * <b>Type: String</b><br>
     * <br>
     * The POP3 server to connect to.
     */
    MAIL_POP3_HOST(
            "mail.pop3.host"),
    /**
     * <b>Type: int</b><br>
     * <br>
     * The POP3 server port to connect to, if the connect()
     * method doesn't explicitly specify one. Defaults to
     * 110.
     */
    MAIL_POP3_PORT(
            "mail.pop3.port"),
    /**
     * <b>Type: int</b><br>
     * <br>
     * Socket connection timeout value in milliseconds.
     * Default is infinite timeout.
     */
    MAIL_POP3_CONNECTIONTIMEOUT(
            "mail.pop3.connectiontimeout"),
    /**
     * <b>Type: int</b><br>
     * <br>
     * Socket I/O timeout value in milliseconds. Default is
     * infinite timeout.
     */
    MAIL_POP3_TIMEOUT(
            "mail.pop3.timeout"),
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * Send a POP3 RSET command when closing the folder,
     * before sending the QUIT command. Useful with POP3
     * servers that implicitly mark all messages that are
     * read as "deleted"; this will prevent such messages
     * from being deleted and expunged unless the client
     * requests so. Default is false.
     */
    MAIL_POP3_RSETBEFOREQUIT(
            "mail.pop3.rsetbeforequit"),
    /**
     * <b>Type: String</b><br>
     * <br>
     * Class name of a subclass of
     * <code>com.sun.mail.pop3.POP3Message</code>. The
     * subclass can be used to handle (for example)
     * non-standard Content-Type headers. The subclass must
     * have a public constructor of the form
     * <code>MyPOP3Message(Folder f, int msgno)
     * throws MessagingException</code>.
     */
    MAIL_POP3_MESSAGE_CLASS(
            "mail.pop3.message.class"),
    /**
     * <b>Type: String</b><br>
     * <br>
     * Local address (host name) to bind to when creating
     * the POP3 socket. Defaults to the address picked by
     * the Socket class. Should not normally need to be set,
     * but useful with multi-homed hosts where it's
     * important to pick a particular local address to bind
     * to.
     */
    MAIL_POP3_LOCALADDRESS(
            "mail.pop3.localaddress"),
    /**
     * <b>Type: int</b><br>
     * <br>
     * Local port number to bind to when creating the POP3
     * socket. Defaults to the port number picked by the
     * Socket class.
     */
    MAIL_POP3_LOCALPORT(
            "mail.pop3.localport"),
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * If set to true, use APOP instead of USER/PASS to
     * login to the POP3 server, if the POP3 server supports
     * APOP. APOP sends a digest of the password rather than
     * the clear text password. Defaults to false.
     */
    MAIL_POP3_APOP_ENABLE(
            "mail.pop3.apop.enable"),
    /**
     * <b>Type: SocketFactory</b><br>
     * <br>
     * If set to a class that implements the
     * <code>javax.net.SocketFactory</code> interface, this
     * class will be used to create POP3 sockets. Note that
     * this is an instance of a class, not a name, and must
     * be set using the <code>put</code> method, not the
     * <code>setProperty</code> method.
     */
    MAIL_POP3_SOCKETFACTORY(
            "mail.pop3.socketFactory"),
    /**
     * <b>Type: String</b><br>
     * <br>
     * If set, specifies the name of a class that implements
     * the <code>javax.net.SocketFactory</code> interface.
     * This class will be used to create POP3 sockets.
     */
    MAIL_POP3_SOCKETFACTORY_CLASS(
            "mail.pop3.socketFactory.class"),
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * If set to true, failure to create a socket using the
     * specified socket factory class will cause the socket
     * to be created using the <code>java.net.Socket</code>
     * class. Defaults to true.
     */
    MAIL_POP3_SOCKETFACTORY_FALLBACK(
            "mail.pop3.socketFactory.fallback"),
    /**
     * <b>Type: int</b><br>
     * <br>
     * Specifies the port to connect to when using the
     * specified socket factory. If not set, the default
     * port will be used.
     */
    MAIL_POP3_SOCKETFACTORY_PORT(
            "mail.pop3.socketFactory.port"),
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * If set to true, use SSL to connect and use the SSL
     * port by default. Defaults to false for the "pop3"
     * protocol and true for the "pop3s" protocol.
     */
    MAIL_POP3_SSL_ENABLE(
            "mail.pop3.ssl.enable"),
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
    MAIL_POP3_SSL_CHECKSERVERIDENTITY(
            "mail.pop3.ssl.checkserveridentity"),
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
    MAIL_POP3_SSL_TRUST(
            "mail.pop3.ssl.trust"),
    /**
     * <b>Type: SSLSocketFactory</b><br>
     * <br>
     * If set to a class that extends the
     * <code>javax.net.ssl.SSLSocketFactory</code> class,
     * this class will be used to create POP3 SSL sockets.
     * Note that this is an instance of a class, not a name,
     * and must be set using the <code>put</code> method,
     * not the <code>setProperty</code> method.
     */
    MAIL_POP3_SSL_SOCKETFACTORY(
            "mail.pop3.ssl.socketFactory"),
    /**
     * <b>Type: String</b><br>
     * <br>
     * If set, specifies the name of a class that extends
     * the <code>javax.net.ssl.SSLSocketFactory</code>
     * class. This class will be used to create POP3 SSL
     * sockets.
     */
    MAIL_POP3_SSL_SOCKETFACTORY_CLASS(
            "mail.pop3.ssl.socketFactory.class"),
    /**
     * <b>Type: int</b><br>
     * <br>
     * Specifies the port to connect to when using the
     * specified socket factory. If not set, the default
     * port will be used.
     */
    MAIL_POP3_SSL_SOCKETFACTORY_PORT(
            "mail.pop3.ssl.socketFactory.port"),
    /**
     * <b>Type: string</b><br>
     * <br>
     * Specifies the SSL protocols that will be enabled for
     * SSL connections. The property value is a whitespace
     * separated list of tokens acceptable to the
     * <code>javax.net.ssl.SSLSocket.setEnabledProtocols</code>
     * method.
     */
    MAIL_POP3_SSL_PROTOCOLS(
            "mail.pop3.ssl.protocols"),
    /**
     * <b>Type: string</b><br>
     * <br>
     * Specifies the SSL cipher suites that will be enabled
     * for SSL connections. The property value is a
     * whitespace separated list of tokens acceptable to the
     * <code>javax.net.ssl.SSLSocket.setEnabledCipherSuites</code>
     * method.
     */
    MAIL_POP3_SSL_CIPHERSUITES(
            "mail.pop3.ssl.ciphersuites"),
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * If true, enables the use of the <code>STLS</code>
     * command (if supported by the server) to switch the
     * connection to a TLS-protected connection before
     * issuing any login commands. Note that an appropriate
     * trust store must configured so that the client will
     * trust the server's certificate. Defaults to false.
     */
    MAIL_POP3_STARTTLS_ENABLE(
            "mail.pop3.starttls.enable"),
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * If true, requires the use of the <code>STLS</code>
     * command. If the server doesn't support the STLS
     * command, or the command fails, the connect method
     * will fail. Defaults to false.
     */
    MAIL_POP3_STARTTLS_REQUIRED(
            "mail.pop3.starttls.required"),
    /**
     * <b>Type: string</b><br>
     * <br>
     * Specifies the host name of a SOCKS5 proxy server that
     * will be used for connections to the mail server.
     * (Note that this only works on JDK 1.5 or newer.)
     */
    MAIL_POP3_SOCKS_HOST(
            "mail.pop3.socks.host"),
    /**
     * <b>Type: string</b><br>
     * <br>
     * Specifies the port number for the SOCKS5 proxy
     * server. This should only need to be used if the proxy
     * server is not using the standard port number of 1080.
     */
    MAIL_POP3_SOCKS_PORT(
            "mail.pop3.socks.port"),
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * If set to true, the POP3 TOP command will not be used
     * to fetch message headers. This is useful for POP3
     * servers that don't properly implement the TOP
     * command, or that provide incorrect information in the
     * TOP command results. Defaults to false.
     */
    MAIL_POP3_DISABLETOP(
            "mail.pop3.disabletop"),
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * If set to true, the POP3 CAPA command will not be
     * used to fetch server capabilities. This is useful for
     * POP3 servers that don't properly implement the CAPA
     * command, or that provide incorrect information in the
     * CAPA command results. Defaults to false.
     */
    MAIL_POP3_DISABLECAPA(
            "mail.pop3.disablecapa"),
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * If set to true, the headers that might have been
     * retrieved using the POP3 TOP command will be
     * forgotten and replaced by headers retrieved as part
     * of the POP3 RETR command. Some servers, such as some
     * versions of Microsft Exchange and IBM Lotus Notes,
     * will return slightly different headers each time the
     * TOP or RETR command is used. To allow the POP3
     * provider to properly parse the message content
     * returned from the RETR command, the headers also
     * returned by the RETR command must be used. Setting
     * this property to true will cause these headers to be
     * used, even if they differ from the headers returned
     * previously as a result of using the TOP command.
     * Defaults to false.
     */
    MAIL_POP3_FORGETTOPHEADERS(
            "mail.pop3.forgettopheaders"),
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * If set to true, the POP3 provider will cache message
     * data in a temporary file rather than in memory.
     * Messages are only added to the cache when accessing
     * the message content. Message headers are always
     * cached in memory (on demand). The file cache is
     * removed when the folder is closed or the JVM
     * terminates. Defaults to false.
     */
    MAIL_POP3_FILECACHE_ENABLE(
            "mail.pop3.filecache.enable"),
    /**
     * <b>Type: String</b><br>
     * <br>
     * If the file cache is enabled, this property can be
     * used to override the default directory used by the
     * JDK for temporary files.
     */
    MAIL_POP3_FILECACHE_DIR(
            "mail.pop3.filecache.dir"),
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * Controls the behavior of the <A HREF=
     * "../../../../com/sun/mail/pop3/POP3Message.html#writeTo(java.io.OutputStream, java.lang.String[])"
     * ><CODE>writeTo</CODE></A> method on a POP3 message
     * object. If set to true, and the message content
     * hasn't yet been cached, and ignoreList is null, the
     * message is cached before being written. Otherwise,
     * the message is streamed directly to the output stream
     * without being cached. Defaults to false.
     */
    MAIL_POP3_CACHEWRITETO(
            "mail.pop3.cachewriteto"),
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * The content of a message is cached when it is first
     * fetched. Normally this cache uses a <A HREF=
     * "http://java.sun.com/j2se/1.4.2/docs/api/java/lang/ref/SoftReference.html?is-external=true"
     * title="class or interface in java.lang.ref">
     * <CODE>SoftReference</CODE></A> to refer to the cached
     * content. This allows the cached content to be purged
     * if memory is low, in which case the content will be
     * fetched again if it's needed. If this property is set
     * to true, a hard reference to the cached content will
     * be kept, preventing the memory from being reused
     * until the folder is closed or the cached content is
     * explicitly invalidated (using the <A HREF=
     * "../../../../com/sun/mail/pop3/POP3Message.html#invalidate(boolean)"
     * ><CODE>invalidate</CODE></A> method). (This was the
     * behavior in previous versions of JavaMail.) Defaults
     * to false.
     */
    MAIL_POP3_KEEPMESSAGECONTENT(
            "mail.pop3.keepmessagecontent");
    
    private String _key;
    
    private POP3_Prop(String key) {
        _key = key;
    }
    
    @Override
    public String key() {
        return _key;
    }
}
