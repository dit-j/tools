package de.jawb.tools.util.service.mail.conf.props;

import de.jawb.tools.util.service.mail.interfaces.ConfigurationProperty;

/**
 * @author dit (14.06.2012)
 */
public enum SMTP_Prop implements ConfigurationProperty {
    
    /**
     * <b>Type: String</b><br>
     * <br>
     * Default user name for SMTP.
     */
    MAIL_SMTP_USER(
            "mail.smtp.user"),
    /**
     * <b>Type: String</b><br>
     * <br>
     * The SMTP server to connect to.
     */
    MAIL_SMTP_HOST(
            "mail.smtp.host"),
    /**
     * <b>Type: int</b><br>
     * <br>
     * The SMTP server port to connect to, if the connect()
     * method doesn't explicitly specify one. Defaults to
     * 25.
     */
    MAIL_SMTP_PORT(
            "mail.smtp.port"),
    /**
     * <b>Type: int</b><br>
     * <br>
     * Socket connection timeout value in milliseconds.
     * Default is infinite timeout.
     */
    MAIL_SMTP_CONNECTIONTIMEOUT(
            "mail.smtp.connectiontimeout"),
    /**
     * <b>Type: int</b><br>
     * <br>
     * Socket I/O timeout value in milliseconds. Default is
     * infinite timeout.
     */
    MAIL_SMTP_TIMEOUT(
            "mail.smtp.timeout"),
    /**
     * <b>Type: String</b><br>
     * <br>
     * Email address to use for SMTP MAIL command. This sets
     * the envelope return address. Defaults to
     * msg.getFrom() or InternetAddress.getLocalAddress().
     * NOTE: mail.smtp.user was previously used for this.
     */
    MAIL_SMTP_FROM(
            "mail.smtp.from"),
    /**
     * <b>Type: String</b><br>
     * <br>
     * Local host name used in the SMTP HELO or EHLO
     * command. Defaults to
     * <code>InetAddress.getLocalHost().getHostName()</code>
     * . Should not normally need to be set if your JDK and
     * your name service are configured properly.
     */
    MAIL_SMTP_LOCALHOST(
            "mail.smtp.localhost"),
    /**
     * <b>Type: String</b><br>
     * <br>
     * Local address (host name) to bind to when creating
     * the SMTP socket. Defaults to the address picked by
     * the Socket class. Should not normally need to be set,
     * but useful with multi-homed hosts where it's
     * important to pick a particular local address to bind
     * to.
     */
    MAIL_SMTP_LOCALADDRESS(
            "mail.smtp.localaddress"),
    /**
     * <b>Type: int</b><br>
     * <br>
     * Local port number to bind to when creating the SMTP
     * socket. Defaults to the port number picked by the
     * Socket class.
     */
    MAIL_SMTP_LOCALPORT(
            "mail.smtp.localport"),
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * If false, do not attempt to sign on with the EHLO
     * command. Defaults to true. Normally failure of the
     * EHLO command will fallback to the HELO command; this
     * property exists only for servers that don't fail EHLO
     * properly or don't implement EHLO properly.
     */
    MAIL_SMTP_EHLO(
            "mail.smtp.ehlo"),
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * If true, attempt to authenticate the user using the
     * AUTH command. Defaults to false.
     */
    MAIL_SMTP_AUTH(
            "mail.smtp.auth"),
    /**
     * <b>Type: String</b><br>
     * <br>
     * If set, lists the authentication mechanisms to
     * consider, and the order in which to consider them.
     * Only mechanisms supported by the server and supported
     * by the current implementation will be used. The
     * default is <code>"LOGIN PLAIN DIGEST-MD5 NTLM"</code>
     * , which includes all the authentication mechanisms
     * supported by the current implementation.
     */
    MAIL_SMTP_AUTH_MECHANISMS(
            "mail.smtp.auth.mechanisms"),
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * If true, prevents use of the <code>AUTH LOGIN</code>
     * command. Default is false.
     */
    MAIL_SMTP_AUTH_LOGIN_DISABLE(
            "mail.smtp.auth.login.disable"),
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * If true, prevents use of the <code>AUTH PLAIN</code>
     * command. Default is false.
     */
    MAIL_SMTP_AUTH_PLAIN_DISABLE(
            "mail.smtp.auth.plain.disable"),
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * If true, prevents use of the
     * <code>AUTH DIGEST-MD5</code> command. Default is
     * false.
     */
    MAIL_SMTP_AUTH_DIGEST_MD5_DISABLE(
            "mail.smtp.auth.digest-md5.disable"),
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * If true, prevents use of the <code>AUTH NTLM</code>
     * command. Default is false.
     */
    MAIL_SMTP_AUTH_NTLM_DISABLE(
            "mail.smtp.auth.ntlm.disable"),
    /**
     * <b>Type: String</b><br>
     * <br>
     * The NTLM authentication domain.
     */
    MAIL_SMTP_AUTH_NTLM_DOMAIN(
            "mail.smtp.auth.ntlm.domain"),
    /**
     * <b>Type: int</b><br>
     * <br>
     * NTLM protocol-specific flags. See <A HREF=
     * "http://curl.haxx.se/rfc/ntlm.html#theNtlmFlags"
     * TARGET="_top">
     * http://curl.haxx.se/rfc/ntlm.html#theNtlmFlags</A>
     * for details.
     */
    MAIL_SMTP_AUTH_NTLM_FLAGS(
            "mail.smtp.auth.ntlm.flags"),
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * Set this to "true" if the username or password may
     * use Unicode UTF-8 encoded characters. Default is
     * "true". Currently has no effect.
     */
    MAIL_SMTP_AUTH_NTLM_UNICODE(
            "mail.smtp.auth.ntlm.unicode"),
    /**
     * <b>Type: int</b><br>
     * <br>
     * Sets the LM compatibility level, as described here:
     * <A HREF=
     * "http://curl.haxx.se/rfc/ntlm.html#ntlmVersion2"
     * TARGET="_top">
     * http://curl.haxx.se/rfc/ntlm.html#ntlmVersion2</A>
     * Defaults to "3". Currently not used.
     */
    MAIL_SMTP_AUTH_NTLM_LMCOMPAT(
            "mail.smtp.auth.ntlm.lmcompat"),
    /**
     * <b>Type: String</b><br>
     * <br>
     * The submitter to use in the AUTH tag in the MAIL FROM
     * command. Typically used by a mail relay to pass along
     * information about the original submitter of the
     * message. See also the <A HREF=
     * "../../../../com/sun/mail/smtp/SMTPMessage.html#setSubmitter(java.lang.String)"
     * ><CODE>setSubmitter</CODE></A> method of <A HREF=
     * "../../../../com/sun/mail/smtp/SMTPMessage.html"
     * title="class in com.sun.mail.smtp">
     * <CODE>SMTPMessage</CODE></A>. Mail clients typically
     * do not use this.
     */
    MAIL_SMTP_SUBMITTER(
            "mail.smtp.submitter"),
    /**
     * <b>Type: String</b><br>
     * <br>
     * The NOTIFY option to the RCPT command. Either NEVER,
     * or some combination of SUCCESS, FAILURE, and DELAY
     * (separated by commas).
     */
    MAIL_SMTP_DSN_NOTIFY(
            "mail.smtp.dsn.notify"),
    /**
     * <b>Type: String</b><br>
     * <br>
     * The RET option to the MAIL command. Either FULL or
     * HDRS.
     */
    MAIL_SMTP_DSN_RET(
            "mail.smtp.dsn.ret"),
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * If set to true, and the server supports the 8BITMIME
     * extension, text parts of messages that use the
     * "quoted-printable" or "base64" encodings are
     * converted to use "8bit" encoding if they follow the
     * RFC2045 rules for 8bit text.
     */
    MAIL_SMTP_ALLOW8BITMIME(
            "mail.smtp.allow8bitmime"),
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * If set to true, and a message has some valid and some
     * invalid addresses, send the message anyway, reporting
     * the partial failure with a SendFailedException. If
     * set to false (the default), the message is not sent
     * to any of the recipients if there is an invalid
     * recipient address.
     */
    MAIL_SMTP_SENDPARTIAL(
            "mail.smtp.sendpartial"),
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * If set to true, attempt to use the
     * javax.security.sasl package to choose an
     * authentication mechanism for login. Defaults to
     * false.
     */
    MAIL_SMTP_SASL_ENABLE(
            "mail.smtp.sasl.enable"),
    /**
     * <b>Type: String</b><br>
     * <br>
     * A space or comma separated list of SASL mechanism
     * names to try to use.
     */
    MAIL_SMTP_SASL_MECHANISMS(
            "mail.smtp.sasl.mechanisms"),
    /**
     * <b>Type: String</b><br>
     * <br>
     * The authorization ID to use in the SASL
     * authentication. If not set, the authentication ID
     * (user name) is used.
     */
    MAIL_SMTP_SASL_AUTHORIZATIONID(
            "mail.smtp.sasl.authorizationid"),
    /**
     * <b>Type: String</b><br>
     * <br>
     * The realm to use with DIGEST-MD5 authentication.
     */
    MAIL_SMTP_SASL_REALM(
            "mail.smtp.sasl.realm"),
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * If set to false, the QUIT command is sent and the
     * connection is immediately closed. If set to true (the
     * default), causes the transport to wait for the
     * response to the QUIT command.
     */
    MAIL_SMTP_QUITWAIT(
            "mail.smtp.quitwait"),
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * If set to true, causes the transport to include an <A
     * HREF=
     * "../../../../com/sun/mail/smtp/SMTPAddressSucceededException.html"
     * title="class in com.sun.mail.smtp">
     * <CODE>SMTPAddressSucceededException</CODE></A> for
     * each address that is successful. Note also that this
     * will cause a <A HREF=
     * "../../../../javax/mail/SendFailedException.html"
     * title="class in javax.mail">
     * <CODE>SendFailedException</CODE></A> to be thrown
     * from the <A HREF=
     * "../../../../com/sun/mail/smtp/SMTPTransport.html#sendMessage(javax.mail.Message, javax.mail.Address[])"
     * ><CODE>sendMessage</CODE></A> method of <A HREF=
     * "../../../../com/sun/mail/smtp/SMTPTransport.html"
     * title="class in com.sun.mail.smtp">
     * <CODE>SMTPTransport</CODE></A> even if all addresses
     * were correct and the message was sent successfully.
     */
    MAIL_SMTP_REPORTSUCCESS(
            "mail.smtp.reportsuccess"),
    /**
     * <b>Type: SocketFactory</b><br>
     * <br>
     * If set to a class that implements the
     * <code>javax.net.SocketFactory</code> interface, this
     * class will be used to create SMTP sockets. Note that
     * this is an instance of a class, not a name, and must
     * be set using the <code>put</code> method, not the
     * <code>setProperty</code> method.
     */
    MAIL_SMTP_SOCKETFACTORY(
            "mail.smtp.socketFactory"),
    /**
     * <b>Type: String</b><br>
     * <br>
     * If set, specifies the name of a class that implements
     * the <code>javax.net.SocketFactory</code> interface.
     * This class will be used to create SMTP sockets.
     */
    MAIL_SMTP_SOCKETFACTORY_CLASS(
            "mail.smtp.socketFactory.class"),
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * If set to true, failure to create a socket using the
     * specified socket factory class will cause the socket
     * to be created using the <code>java.net.Socket</code>
     * class. Defaults to true.
     */
    MAIL_SMTP_SOCKETFACTORY_FALLBACK(
            "mail.smtp.socketFactory.fallback"),
    /**
     * <b>Type: int</b><br>
     * <br>
     * Specifies the port to connect to when using the
     * specified socket factory. If not set, the default
     * port will be used.
     */
    MAIL_SMTP_SOCKETFACTORY_PORT(
            "mail.smtp.socketFactory.port"),
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * If set to true, use SSL to connect and use the SSL
     * port by default. Defaults to false for the "smtp"
     * protocol and true for the "smtps" protocol.
     */
    MAIL_SMTP_SSL_ENABLE(
            "mail.smtp.ssl.enable"),
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
    MAIL_SMTP_SSL_CHECKSERVERIDENTITY(
            "mail.smtp.ssl.checkserveridentity"),
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
    MAIL_SMTP_SSL_TRUST(
            "mail.smtp.ssl.trust"),
    /**
     * <b>Type: SSLSocketFactory</b><br>
     * <br>
     * If set to a class that extends the
     * <code>javax.net.ssl.SSLSocketFactory</code> class,
     * this class will be used to create SMTP SSL sockets.
     * Note that this is an instance of a class, not a name,
     * and must be set using the <code>put</code> method,
     * not the <code>setProperty</code> method.
     */
    MAIL_SMTP_SSL_SOCKETFACTORY(
            "mail.smtp.ssl.socketFactory"),
    /**
     * <b>Type: String</b><br>
     * <br>
     * If set, specifies the name of a class that extends
     * the <code>javax.net.ssl.SSLSocketFactory</code>
     * class. This class will be used to create SMTP SSL
     * sockets.
     */
    MAIL_SMTP_SSL_SOCKETFACTORY_CLASS(
            "mail.smtp.ssl.socketFactory.class"),
    /**
     * <b>Type: int</b><br>
     * <br>
     * Specifies the port to connect to when using the
     * specified socket factory. If not set, the default
     * port will be used.
     */
    MAIL_SMTP_SSL_SOCKETFACTORY_PORT(
            "mail.smtp.ssl.socketFactory.port"),
    /**
     * <b>Type: string</b><br>
     * <br>
     * Specifies the SSL protocols that will be enabled for
     * SSL connections. The property value is a whitespace
     * separated list of tokens acceptable to the
     * <code>javax.net.ssl.SSLSocket.setEnabledProtocols</code>
     * method.
     */
    MAIL_SMTP_SSL_PROTOCOLS(
            "mail.smtp.ssl.protocols"),
    /**
     * <b>Type: string</b><br>
     * <br>
     * Specifies the SSL cipher suites that will be enabled
     * for SSL connections. The property value is a
     * whitespace separated list of tokens acceptable to the
     * <code>javax.net.ssl.SSLSocket.setEnabledCipherSuites</code>
     * method.
     */
    MAIL_SMTP_SSL_CIPHERSUITES(
            "mail.smtp.ssl.ciphersuites"),
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * If true, enables the use of the <code>STARTTLS</code>
     * command (if supported by the server) to switch the
     * connection to a TLS-protected connection before
     * issuing any login commands. Note that an appropriate
     * trust store must configured so that the client will
     * trust the server's certificate. Defaults to false.
     */
    MAIL_SMTP_STARTTLS_ENABLE(
            "mail.smtp.starttls.enable"),
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * If true, requires the use of the
     * <code>STARTTLS</code> command. If the server doesn't
     * support the STARTTLS command, or the command fails,
     * the connect method will fail. Defaults to false.
     */
    MAIL_SMTP_STARTTLS_REQUIRED(
            "mail.smtp.starttls.required"),
    /**
     * <b>Type: string</b><br>
     * <br>
     * Specifies the host name of a SOCKS5 proxy server that
     * will be used for connections to the mail server.
     * (Note that this only works on JDK 1.5 or newer.)
     */
    MAIL_SMTP_SOCKS_HOST(
            "mail.smtp.socks.host"),
    /**
     * <b>Type: string</b><br>
     * <br>
     * Specifies the port number for the SOCKS5 proxy
     * server. This should only need to be used if the proxy
     * server is not using the standard port number of 1080.
     */
    MAIL_SMTP_SOCKS_PORT(
            "mail.smtp.socks.port"),
    /**
     * <b>Type: String</b><br>
     * <br>
     * Extension string to append to the MAIL command. The
     * extension string can be used to specify standard SMTP
     * service extensions as well as vendor-specific
     * extensions. Typically the application should use the
     * <A HREF=
     * "../../../../com/sun/mail/smtp/SMTPTransport.html"
     * title="class in com.sun.mail.smtp">
     * <CODE>SMTPTransport</CODE></A> method <A HREF=
     * "../../../../com/sun/mail/smtp/SMTPTransport.html#supportsExtension(java.lang.String)"
     * ><CODE>supportsExtension</CODE></A> to verify that
     * the server supports the desired service extension.
     * See <A HREF="http://www.ietf.org/rfc/rfc1869.txt"
     * TARGET="_top">RFC 1869</A> and other RFCs that define
     * specific extensions.
     */
    MAIL_SMTP_MAILEXTENSION(
            "mail.smtp.mailextension"),
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * If set to true, use the RSET command instead of the
     * NOOP command in the <A HREF=
     * "../../../../javax/mail/Service.html#isConnected()">
     * <CODE>isConnected</CODE></A> method. In some cases
     * sendmail will respond slowly after many NOOP
     * commands; use of RSET avoids this sendmail issue.
     * Defaults to false.
     */
    MAIL_SMTP_USERSET(
            "mail.smtp.userset"),
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * If set to true (the default), insist on a 250
     * response code from the NOOP command to indicate
     * success. The NOOP command is used by the <A HREF=
     * "../../../../javax/mail/Service.html#isConnected()">
     * <CODE>isConnected</CODE></A> method to determine if
     * the connection is still alive. Some older servers
     * return the wrong response code on success, some
     * servers don't implement the NOOP command at all and
     * so always return a failure code. Set this property to
     * false to handle servers that are broken in this way.
     * Normally, when a server times out a connection, it
     * will send a 421 response code, which the client will
     * see as the response to the next command it issues.
     * Some servers send the wrong failure response code
     * when timing out a connection. Do not set this
     * property to false when dealing with servers that are
     * broken in this way.
     */
    MAIL_SMTP_NOOP_STRICT(
            "mail.smtp.noop.strict");
    
    private final String _key;
    
    private SMTP_Prop(String key) {
        _key = key;
    }
    
    @Override
    public String key() {
        return _key;
    }
    
//    public static void main(String[] args) throws IOException {
//        List<String> line = FileAccess.getContent(new File("c:\\smtp.txt"));
//        
//        class Temp {
//            
//            String c;
//            String n;
//            String v;
//        }
//        
////        <TR>
////        <TD>mail.smtp.user</TD>
////        <TD>String</TD>
////        <TD>Default user name for SMTP.</TD>
////        </TR>
//        boolean tr = false;
//        boolean td = false;
//        int col = 0;
//        String template = "/**\n * %s\n */\n%s(\"%s\"),";
//        Temp t = new Temp();
//        for (String string : line) {
//            String l = string.trim();
//            if (l.startsWith("<TR>")) {
//                tr = true;
//                col = -1;
//            } else if (l.startsWith("</TR>")) {
//                tr = false;
//                System.out.println(String.format(template, t.c, t.n, t.v));
//            }
//            
//            String cleaned = l.replaceAll("(<TD>)|(</TD>)", "");
////            cleaned = cleaned.replaceAll("<A HREF(.*?)>", "");
////            cleaned = cleaned.replaceAll("</A>", "");
//            if (tr) {
//                if (l.startsWith("<TD>")) {
//                    td = true;
//                    col++;
//                } else if (l.startsWith("</TD>")) {
//                    td = false;
//                }
//                
//                if (td) {
//                    if (col == 0) {
//                        String enumName = cleaned.toUpperCase().replaceAll("[.|-]", "_");
//                        t.n = enumName.replaceAll(" ", "");
//                        t.v = cleaned.replaceAll(" ", "");
//                    } else if (col == 1) {
//                        t.c = "<b>Type: " + cleaned + "</b><br><br>";
//                    } else if (col == 2) {
//                        t.c = (t.c + "\n * " + cleaned);
//                    }
//                }
//            }
//            
//        }
//    }
    
}
