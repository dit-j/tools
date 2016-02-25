package de.jawb.tools.util.service.mail.interfaces;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.MimeMultipart;

import de.jawb.tools.util.service.mail.EMail;
import de.jawb.tools.util.service.mail._exception.MailException;
import de.jawb.tools.util.service.mail.conf.MailServiceConfiguration;


/**
 * @author dit (14.06.2012)
 */
public abstract class AbstractMailService implements MailService {
    
    protected MailServiceConfiguration _configuration;
    
    /**
     * Konstruktor.
     * 
     * @param conf
     *            Konfiguration des Mail-Servers
     */
    public AbstractMailService(MailServiceConfiguration conf) {
        _configuration = conf;
    }
    
    /**
     * Fabrikmethode fuer {@link Session}
     * 
     * @return {@link Session}
     */
    protected abstract Session createSession();
    
    /**
     * Fabrikmethode fuer {@link MimeMultipart}
     * 
     * @return {@link MimeMultipart}
     */
    protected abstract MimeMultipart createMimeMultipart();
    
    /**
     * Fabrikmethode fuer {@link Message}
     * 
     * @param session
     * @param eMail
     * @return {@link Message}
     * @throws MessagingException
     * @throws AddressException
     */
    protected abstract Message createMessage(Session session, EMail eMail) throws AddressException,
            MessagingException;
    
    @Override
    public abstract void sendEMail(EMail eMail) throws MailException;
    
//    /**
//     * @param eMails
//     * @throws MailException
//     */
//    public abstract void sendEMails(List<EMail> eMails) throws MailException;
    
}
