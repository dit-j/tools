package de.jawb.tools.util.service.mail.conf;

import de.jawb.tools.util.service.mail.conf.props.Main_Prop;
import de.jawb.tools.util.service.mail.conf.props.SMTP_Prop;

/**
 * @author dit (15.06.2012)
 */
public class ConfigurationFactory {
    
    private ConfigurationFactory() {
        //
    }
    
    /**
     * Erstellt eine Standardkonfiguration.
     * 
     * @param smtpHost
     *            Adresse des MailServers.
     * @return {@link MailServiceConfiguration}
     */
    public static MailServiceConfiguration localSmtpServerConfig(String smtpHost) {
        MailServiceConfiguration conf = new MailServiceConfiguration();
        
        conf.addProperty(SMTP_Prop.MAIL_SMTP_HOST, smtpHost);
        conf.addProperty(Main_Prop.MAIL_DEBUG, "true");
        
        return conf;
    }
}
