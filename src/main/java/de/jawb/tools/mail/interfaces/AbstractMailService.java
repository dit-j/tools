package de.jawb.tools.mail.interfaces;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.MimeMultipart;

import de.jawb.tools.mail.EMail;
import de.jawb.tools.mail._exception.MailException;
import de.jawb.tools.mail.conf.MailServiceConfiguration;

/**
 * @author dit (14.06.2012)
 */
public abstract class AbstractMailService implements MailService {

    protected MailServiceConfiguration configuration;

    public AbstractMailService(MailServiceConfiguration conf) {
        configuration = conf;
    }

    protected abstract Session createSession();
    protected abstract MimeMultipart createMimeMultipart();
    protected abstract Message createMessage(Session session, EMail eMail) throws AddressException, MessagingException;

    @Override
    public final void sendEMail(EMail mail) throws MailException {
        try {
            Session session = createSession();
            Message msg = createMessage(session, mail);
            //
            // SENDEN
            //
            Transport.send(msg);
        } catch (Exception e) {
            throw new MailException("Can't send Email.", e.getCause());
        }
    }
}
