package de.jawb.tools.mail.interfaces;

import de.jawb.tools.mail.EMail;
import de.jawb.tools.mail._exception.MailException;

/**
 * @author dit (10.11.2012)
 */
public interface MailService {
    
    /**
     * Versendet eine Email.
     * 
     * @param eMail
     *            Email-Objekt
     * @throws MailException
     */
    void sendEMail(EMail eMail) throws MailException;
}
