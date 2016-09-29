package de.jawb.tools.util.service.mail.conf.props;

import de.jawb.tools.util.service.mail.interfaces.ConfigurationProperty;

/**
 * @author dit (15.06.2012)
 */
public enum MIME_Prop implements ConfigurationProperty {
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * The <code>mail.mime.address.strict</code> session
     * property controls the parsing of address headers. By
     * default, strict parsing of address headers is done.
     * If this property is set to <code>"false"</code>,
     * strict parsing is not done and many illegal addresses
     * that sometimes occur in real messages are allowed.
     * See the <code>InternetAddress</code> class for
     * details.
     */
    MAIL_MIME_ADDRESS_STRICT(
            "mail.mime.address.strict"),
    /**
     * <b>Type: String</b><br>
     * <br>
     * The <code>mail.mime.charset</code> System property
     * can be used to specify the default MIME charset to
     * use for encoded words and text parts that don't
     * otherwise specify a charset. Normally, the default
     * MIME charset is derived from the default Java
     * charset, as specified in the
     * <code>file.encoding</code> System property. Most
     * applications will have no need to explicitly set the
     * default MIME charset. In cases where the default MIME
     * charset to be used for mail messages is different
     * than the charset used for files stored on the system,
     * this property should be set.
     */
    MAIL_MIME_CHARSET(
            "mail.mime.charset"),
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * The <code>mail.mime.decodetext.strict</code> property
     * controls decoding of MIME encoded words. The MIME
     * spec requires that encoded words start at the
     * beginning of a whitespace separated word. Some
     * mailers incorrectly include encoded words in the
     * middle of a word. If the
     * <code>mail.mime.decodetext.strict</code> System
     * property is set to <code>"false"</code>, an attempt
     * will be made to decode these illegal encoded words.
     * The default is true.
     */
    MAIL_MIME_DECODETEXT_STRICT(
            "mail.mime.decodetext.strict"),
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * The <code>mail.mime.encodeeol.strict</code> property
     * controls the choice of Content-Transfer-Encoding for
     * MIME parts that are not of type "text". Often such
     * parts will contain textual data for which an encoding
     * that allows normal end of line conventions is
     * appropriate. In rare cases, such a part will appear
     * to contain entirely textual data, but will require an
     * encoding that preserves CR and LF characters without
     * change. If the
     * <code>mail.mime.encodeeol.strict</code> System
     * property is set to <code>"true"</code>, such an
     * encoding will be used when necessary. The default is
     * false.
     */
    MAIL_MIME_ENCODEEOL_STRICT(
            "mail.mime.encodeeol.strict"),
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * If set to <code>"true"</code>, the
     * <code>getFileName</code> method uses the
     * <code>MimeUtility</code> method
     * <code>decodeText</code> to decode any non-ASCII
     * characters in the filename. Note that this decoding
     * violates the MIME specification, but is useful for
     * interoperating with some mail clients that use this
     * convention. The default is false.
     */
    MAIL_MIME_DECODEFILENAME(
            "mail.mime.decodefilename"),
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * If set to <code>"true"</code>, the
     * <code>setFileName</code> method uses the
     * <code>MimeUtility</code> method
     * <code>encodeText</code> to encode any non-ASCII
     * characters in the filename. Note that this encoding
     * violates the MIME specification, but is useful for
     * interoperating with some mail clients that use this
     * convention. The default is false.
     */
    MAIL_MIME_ENCODEFILENAME(
            "mail.mime.encodefilename"),
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * If set to <code>"true"</code>, non-ASCII parameters
     * in a <code>ParameterList</code>, e.g., in a
     * Content-Type header, will be decoded as specified by
     * <A HREF="http://www.ietf.org/rfc/rfc2231.txt"
     * TARGET="_top">RFC 2231</A>. The default is false.
     */
    MAIL_MIME_DECODEPARAMETERS(
            "mail.mime.decodeparameters"),
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * If set to <code>"true"</code>, non-ASCII parameters
     * in a <code>ParameterList</code>, e.g., in a
     * Content-Type header, will be encoded as specified by
     * <A HREF="http://www.ietf.org/rfc/rfc2231.txt"
     * TARGET="_top">RFC 2231</A>. The default is false.
     */
    MAIL_MIME_ENCODEPARAMETERS(
            "mail.mime.encodeparameters"),
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * Normally, when parsing a multipart MIME message, a
     * message that is missing the final end boundary line
     * is not considered an error. The data simply ends at
     * the end of the input. Note that messages of this form
     * violate the MIME specification. If the property
     * <code>mail.mime.multipart.ignoremissingendboundary</code>
     * is set to <code>false</code>, such messages are
     * considered an error and a
     * <code>MesagingException</code> will be thrown when
     * parsing such a message.
     */
    MAIL_MIME_MULTIPART_IGNOREMISSINGENDBOUNDARY(
            "mail.mime.multipart.ignoremissingendboundary"),
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * If the Content-Type header for a multipart content
     * does not have a <code>boundary</code> parameter, the
     * multipart parsing code will look for the first line
     * in the content that looks like a boundary line and
     * extract the boundary parameter from the line. If this
     * property is set to <code>"false"</code>, a
     * <code>MessagingException</code> will be thrown if the
     * Content-Type header doesn't specify a boundary
     * parameter. The default is true.
     */
    MAIL_MIME_MULTIPART_IGNOREMISSINGBOUNDARYPARAMETER(
            "mail.mime.multipart.ignoremissingboundaryparameter"),
    
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * If set to <code>"true"</code>, the BASE64 decoder
     * will ignore errors in the encoded data, returning
     * EOF. This may be useful when dealing with improperly
     * encoded messages that contain extraneous data at the
     * end of the encoded stream. Note however that errors
     * anywhere in the stream will cause the decoder to stop
     * decoding so this should be used with extreme caution.
     * The default is false.
     */
    MAIL_MIME_BASE64_IGNOREERRORS(
            "mail.mime.base64.ignoreerrors"),
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * If set to <code>"true"</code>, header fields
     * containing just text such as the <code>Subject</code>
     * and <code>Content-Description</code> header fields,
     * and long parameter values in structured headers such
     * as <code>Content-Type</code> will be folded (broken
     * into 76 character lines) when set and unfolded when
     * read. The default is true.
     */
    MAIL_MIME_FOLDTEXT(
            "mail.mime.foldtext"),
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * If set to <code>"true"</code>, the
     * <code>setFileName</code> method will also set the
     * <code>name</code> parameter on the
     * <code>Content-Type</code> header to the specified
     * filename. This supports interoperability with some
     * old mail clients. The default is true.
     */
    MAIL_MIME_SETCONTENTTYPEFILENAME(
            "mail.mime.setcontenttypefilename"),
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * When updating the headers of a message, a body part
     * with a <code>text</code> content type but no
     * <code>charset</code> parameter will have a
     * <code>charset</code> parameter added to it if this
     * property is set to <code>"true"</code>. The default
     * is true.
     */
    MAIL_MIME_SETDEFAULTTEXTCHARSET(
            "mail.mime.setdefaulttextcharset"),
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * If set to false, when reading a message, parameter
     * values in header fields such as
     * <code>Content-Type</code> and
     * <code>Content-Disposition</code> are allowed to
     * contain whitespace and other special characters
     * without being quoted; the parameter value ends at the
     * next semicolon. If set to true (the default),
     * parameter values are required to conform to the MIME
     * specification and must be quoted if they contain
     * whitespace or special characters.
     */
    MAIL_MIME_PARAMETERS_STRICT(
            "mail.mime.parameters.strict"),
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * Apple Mail incorrectly encodes filenames that contain
     * spaces, forgetting to quote the parameter value. If
     * this property is set to <code>"true"</code>, JavaMail
     * will try to detect this situation when parsing
     * parameters and work around it. The default is false.
     * Note that this property handles a subset of the cases
     * handled by setting the mail.mime.parameters.strict
     * property to false. This property will likely be
     * removed in a future release.
     */
    MAIL_MIME_APPLEFILENAMES(
            "mail.mime.applefilenames"),
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * Internet Explorer 6 incorrectly includes a complete
     * pathname in the filename parameter of the
     * Content-Disposition header for uploaded files, and
     * fails to properly escape the backslashes in the
     * pathname. If this property is set to
     * <code>"true"</code>, JavaMail will preserve all
     * backslashes in the "filename" and "name" parameters
     * of any MIME header. The default is false. Note that
     * this is a violation of the MIME specification but may
     * be useful when using JavaMail to parse HTTP messages
     * for uploaded files sent by IE6.
     */
    MAIL_MIME_WINDOWSFILENAMES(
            "mail.mime.windowsfilenames"),
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * If set to <code>"true"</code>, an unknown value in
     * the <code>Content-Transfer-Encoding</code> header
     * will be ignored when reading a message and an
     * encoding of "8bit" will be assumed. If set to
     * <code>"false"</code>, an exception is thrown for an
     * unknown encoding value. The default is false.
     */
    MAIL_MIME_IGNOREUNKNOWNENCODING(
            "mail.mime.ignoreunknownencoding"),
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * If set to <code>"true"</code>, errors in the encoded
     * format of a uuencoded document will be ignored when
     * reading a message part. If set to
     * <code>"false"</code>, an exception is thrown for an
     * incorrectly encoded message part. The default is
     * false.
     */
    MAIL_MIME_UUDECODE_IGNOREERRORS(
            "mail.mime.uudecode.ignoreerrors"),
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * If set to <code>"true"</code>, a missing "being" or
     * "end" line in a uuencoded document will be ignored
     * when reading a message part. If set to
     * <code>"false"</code>, an exception is thrown for a
     * uuencoded message part without the required "begin"
     * and "end" lines. The default is false.
     */
    MAIL_MIME_UUDECODE_IGNOREMISSINGBEGINEND(
            "mail.mime.uudecode.ignoremissingbeginend"),
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * Normally, when writing out a MimeMultipart that
     * contains no body parts, or when trying to parse a
     * multipart message with no body parts, a
     * <code>MessagingException</code> is thrown. The MIME
     * spec does not allow multipart content with no body
     * parts. This System property may be set to
     * <code>"true"</code> to override this behavior. When
     * writing out such a MimeMultipart, a single empty part
     * will be included. When reading such a multipart, a
     * MimeMultipart will be created with no body parts. The
     * default value of this property is false.
     */
    MAIL_MIME_MULTIPART_ALLOWEMPTY(
            "mail.mime.multipart.allowempty"),
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * Normally the boundary parameter in the Content-Type
     * header of a multipart body part is used to specify
     * the separator between parts of the multipart body.
     * This System property may be set to
     * <code>"true"</code> to cause the parser to look for a
     * line in the multipart body that looks like a boundary
     * line and use that value as the separator between
     * subsequent parts. This may be useful in cases where a
     * broken anti-virus product has rewritten the message
     * incorrectly such that the boundary parameter and the
     * actual boundary value no longer match. The default
     * value of this property is false.
     */
    MAIL_MIME_MULTIPART_IGNOREEXISTINGBOUNDARYPARAMETER(
            "mail.mime.multipart.ignoreexistingboundaryparameter"),
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * Normally the header of a MIME part is separated from
     * the body by an empty line. This System property may
     * be set to <code>"true"</code> to cause the parser to
     * consider a line containing only whitespace to be an
     * empty line. The default value of this property is
     * false.
     */
    MAIL_MIME_MULTIPART_IGNOREWHITESPACELINES(
            "mail.mime.multipart.ignorewhitespacelines"),
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * The MIME spec does not allow body parts of type
     * multipart/* to be encoded. The
     * Content-Transfer-Encoding header is ignored in this
     * case. Setting this System property to
     * <code>"false"</code> will cause the
     * Content-Transfer-Encoding header to be honored for
     * multipart content. The default value of this property
     * is true.
     */
    MAIL_MIME_IGNOREMULTIPARTENCODING(
            "mail.mime.ignoremultipartencoding"),
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * The MIME spec does not allow body parts of type
     * message/* to be encoded. The
     * Content-Transfer-Encoding header is ignored in this
     * case. Some versions of Microsoft Outlook will
     * incorrectly encode message attachments. Setting this
     * System property to <code>"true"</code> will cause the
     * Content-Transfer-Encoding header to be honored for
     * message attachments. The default value of this
     * property is false.
     */
    MAIL_MIME_ALLOWENCODEDMESSAGES(
            "mail.mime.allowencodedmessages"),
    /**
     * <b>Type: String</b><br>
     * <br>
     * In some cases JavaMail is unable to process messages
     * with an invalid Content-Type header. The header may
     * have incorrect syntax or other problems. This
     * property specifies the name of a class that will be
     * used to clean up the Content-Type header value before
     * JavaMail uses it. The class must have a method with
     * this signature:
     * <CODE>public static String cleanContentType(MimePart mp, String contentType)</CODE>
     * Whenever JavaMail accesses the Content-Type header of
     * a message, it will pass the value to this method and
     * use the returned value instead. The value may be null
     * if the Content-Type header isn't present. Returning
     * null will cause the default Content-Type to be used.
     * The MimePart may be used to access other headers of
     * the message part to determine how to correct the
     * Content-Type. Note that the Content-Type handler
     * doesn't affect the <CODE>getHeader</CODE> method,
     * which still returns the raw header value. Note also
     * that the handler doesn't affect the IMAP provider;
     * the IMAP server is responsible for returning
     * pre-parsed, syntactically correct Content-Type
     * information.
     */
    MAIL_MIME_CONTENTTYPEHANDLER(
            "mail.mime.contenttypehandler"),
    /**
     * <b>Type: String</b><br>
     * <br>
     * A string containing other email addresses that the
     * current user is known by. The
     * <code>MimeMessage</code> <code>reply</code> method
     * will eliminate any of these addresses from the
     * recipient list in the message it constructs, to avoid
     * sending the reply back to the sender.
     */
    MAIL_ALTERNATES(
            "mail.alternates"),
    /**
     * <b>Type: boolean</b><br>
     * <br>
     * If set to <code>"true"</code>, the
     * <code>MimeMessage</code> <code>reply</code> method
     * will put all recipients except the original sender in
     * the <code>Cc</code> list of the newly constructed
     * message. Normally, recipients in the <code>To</code>
     * header of the original message will also appear in
     * the <code>To</code> list of the newly constructed
     * message.
     */
    MAIL_REPLYALLCC(
            "mail.replyallcc");
    
    private String _key;
    
    private MIME_Prop(String key) {
        _key = key;
    }
    
    @Override
    public String key() {
        // TODO Auto-generated method stub
        return _key;
    }
    
}
