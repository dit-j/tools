package de.jawb.tools.mail;

import java.io.File;

/**
 * @author dit (01.07.2011)
 */
public class EMail {

    private String[] recipients;
    private String   subject;
    private String   text;
    private File[]   attachments;

    private String   sender;
    private boolean  htmlContent;

    /**
     * @param recipients
     *            Empfaenger
     * @param subject
     *            Betreff
     * @param text
     *            Nachricht.
     */
    public EMail(String[] recipients, String subject, String text) {
        this(recipients, subject, text, (File[])null);
    }

    /**
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
        this.recipients = recipients;
        this.subject = subject;
        this.text = text;
        this.attachments = attachments;
    }

    /**
     * Gibt recipients zurueck
     *
     * @return the recipients
     */
    public String[] getRecipients() {
        return recipients;
    }

    /**
     * Aendert recipients
     *
     * @param recipients
     *            neuer Wert
     */
    public void setRecipients(String[] recipients) {
        this.recipients = recipients;
    }

    /**
     * Gibt subject zurueck
     *
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Aendert subject
     *
     * @param subject
     *            neuer Wert
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Gibt text zurueck
     *
     * @return the text
     */
    public String getMessage() {
        return text;
    }

    /**
     * Aendert text
     *
     * @param text
     *            neuer Wert
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Gibt attachments zurueck
     *
     * @return the attachments
     */
    public File[] getAttachments() {
        return attachments;
    }

    /**
     * Aendert attachments
     *
     * @param attachments
     *            neuer Wert
     */
    public void setAttachments(File[] attachments) {
        this.attachments = attachments;
    }

    /**
     * Gibt sender zurueck
     *
     * @return the sender
     */
    public String getSender() {
        return sender;
    }

    /**
     * Aendert sender
     *
     * @param sender
     *            neuer Wert
     */
    public void setSender(String sender) {
        this.sender = sender;
    }

}
