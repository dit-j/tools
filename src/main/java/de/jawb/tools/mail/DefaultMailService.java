package de.jawb.tools.mail;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import de.jawb.tools.mail.conf.MailServiceConfiguration;
import de.jawb.tools.mail.conf.props.AUTH_Prop;
import de.jawb.tools.mail.interfaces.AbstractMailService;

/**
 * Standardimplementierung des {@link AbstractMailService} -Interfaces
 *
 * @author dit (15.06.2012)
 */
public class DefaultMailService extends AbstractMailService {

    private static final String DFLT_TYPE = "text/html; charset=utf-8";

    public DefaultMailService(MailServiceConfiguration conf) {
        super(conf);
    }

    @Override
    protected Session createSession() {

        Properties props = _configuration.getProperties();

        final String username = _configuration.getValue(AUTH_Prop.USER);
        final String password = _configuration.getValue(AUTH_Prop.PASS);

        Session session = null;

        if ((username != null) && (password != null)) {
            session = Session.getInstance(props, new Authenticator() {

                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
        } else {
            session = Session.getDefaultInstance(props);
        }
        return session;
    }

    @Override
    protected MimeMultipart createMimeMultipart() {
        return new MimeMultipart("alternative");
    }

    @Override
    protected Message createMessage(Session session, EMail mail) throws AddressException, MessagingException {

        //
        // ERSTELLE Message
        //
        MimeMessage msg = new MimeMessage(session);
        //
        // ABSENDER
        //
        msg.setFrom(new InternetAddress(mail.getSender()));

        //
        // EMPFAENGER LISTE
        //
        String[] recipients = mail.getRecipients();
        InternetAddress[] addressTo = new InternetAddress[recipients.length];
        for (int i = 0; i < recipients.length; i++) {
            addressTo[i] = new InternetAddress(recipients[i]);
        }
        msg.setRecipients(Message.RecipientType.TO, addressTo);

        //
        // BETREFF
        //
        msg.setSubject(mail.getSubject(), "UTF-8");

        //
        // ANHANG
        //
        if (mail.getAttachments() != null) {
            MimeMultipart multipart = createMimeMultipart();
            for (File file : mail.getAttachments()) {
                DataSource fileDataSource = new FileDataSource(file);
                BodyPart messageBodyPart = new MimeBodyPart();
                messageBodyPart.setDataHandler(new DataHandler(fileDataSource));
                messageBodyPart.setFileName(file.getName());

                //
                // FUEGE ANHAENGE HINZU
                //
                multipart.addBodyPart(messageBodyPart);
            }

            //
            // NACHRICHT SELBST
            //
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setHeader("MIME-Version", "1.0");
            mimeBodyPart.setHeader("Content-Type", mimeBodyPart.getContentType());
            mimeBodyPart.setContent(mail.getMessage(), DFLT_TYPE);
            multipart.addBodyPart(mimeBodyPart);

            msg.setContent(multipart);

            return msg;
        }
        //
        // NACHRICHT SELBST
        //
        msg.setContent(mail.getMessage(), DFLT_TYPE);
        return msg;
    }
}
