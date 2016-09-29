package de.jawb.tools.mail;

import java.io.File;

/**
 * @author dit (01.07.2011)
 */
public class EMail {
    
    private String[] _recipients;
    private String   _subject;
    private String   _text;
    private File[]   _attachments;
    
    private String   _sender;
    private boolean  _htmlContent;
    
    /**
     * Konstruktor.
     * 
     * @param recipients
     *            Empfaenger
     * @param subject
     *            Betreff
     * @param text
     *            Nachricht.
     */
    public EMail(String[] recipients, String subject, String text) {
        _recipients = recipients;
        _subject = subject;
        _text = text;
        _attachments = null;
    }
    
    /**
     * Konstruktor.
     * 
     * @param recipients
     *            Empfaenger
     * @param subject
     *            Betreff
     * @param text
     *            Nachricht.
     * @param attachments
     *            Anhaenge
     */
    public EMail(String[] recipients, String subject, String text, File... attachments) {
        super();
        _recipients = recipients;
        _subject = subject;
        _text = text;
        _attachments = attachments;
    }
    
    /**
     * Gibt recipients zurueck
     * 
     * @return the recipients
     */
    public String[] getRecipients() {
        return _recipients;
    }
    
    /**
     * Aendert recipients
     * 
     * @param recipients
     *            neuer Wert
     */
    public void setRecipients(String[] recipients) {
        _recipients = recipients;
    }
    
    /**
     * Gibt subject zurueck
     * 
     * @return the subject
     */
    public String getSubject() {
        return _subject;
    }
    
    /**
     * Aendert subject
     * 
     * @param subject
     *            neuer Wert
     */
    public void setSubject(String subject) {
        _subject = subject;
    }
    
    /**
     * Gibt text zurueck
     * 
     * @return the text
     */
    public String getMessage() {
        return _text;
    }
    
    /**
     * Aendert text
     * 
     * @param text
     *            neuer Wert
     */
    public void setText(String text) {
        _text = text;
    }
    
    /**
     * Gibt attachments zurueck
     * 
     * @return the attachments
     */
    public File[] getAttachments() {
        return _attachments;
    }
    
    /**
     * Aendert attachments
     * 
     * @param attachments
     *            neuer Wert
     */
    public void setAttachments(File[] attachments) {
        _attachments = attachments;
    }
    
    /**
     * Gibt sender zurueck
     * 
     * @return the sender
     */
    public String getSender() {
        return _sender;
    }
    
    /**
     * Aendert sender
     * 
     * @param sender
     *            neuer Wert
     */
    public void setSender(String sender) {
        _sender = sender;
    }
    
    /**
     * Gibt htmlContent zurueck
     * 
     * @return the htmlContent
     */
    @Deprecated
    public boolean isHtmlContent() {
        return _htmlContent;
    }
    
    /**
     * Aendert htmlContent
     * 
     * @param htmlContent
     *            neuer Wert
     */
    @Deprecated
    public final void setHtmlContent(boolean htmlContent) {
        _htmlContent = htmlContent;
    }
    
}
