package de.jawb.tools.mail;

import java.io.File;
import java.util.Arrays;
import java.util.Set;

/**
 * @author dit (01.07.2011)
 */
public class EMail {


    private final String      sender;

    private final Set<String> recipients;
    private final String      subject;
    private final String      content;
    private final File[]      attachments;

    public EMail(String from, Set<String> recipients, String subject, String content, File... attachments) {
        this.sender = from;
        this.recipients = recipients;
        this.subject = subject;
        this.content = content;
        this.attachments = attachments;
    }

    public EMail(String from, Set<String> recipients, String subject, String content) {
        this(from, recipients, subject, content, (File[]) null);
    }

    public String getSender() {
        return sender;
    }

    public Set<String> getRecipients() {
        return recipients;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    public File[] getAttachments() {
        return attachments;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("EMail [sender=");
        builder.append(sender);
        builder.append(", recipients=");
        builder.append(recipients);
        builder.append(", subject=");
        builder.append(subject);
        builder.append(", content=");
        builder.append(content);
        builder.append(", attachments=");
        builder.append(Arrays.toString(attachments));
        builder.append("]");
        return builder.toString();
    }

}
