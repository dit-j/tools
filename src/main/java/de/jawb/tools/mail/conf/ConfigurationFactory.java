package de.jawb.tools.mail.conf;

import de.jawb.tools.mail.conf.props.AUTH_Prop;
import de.jawb.tools.mail.conf.props.Main_Prop;
import de.jawb.tools.mail.conf.props.SMTP_Prop;

/**
 * @author dit (15.06.2012)
 */
public class ConfigurationFactory {

    private ConfigurationFactory() {
        //
    }

    public static MailServiceConfiguration localSmtpServerConfig(String smtpHost) {
        MailServiceConfiguration conf = new MailServiceConfiguration();

        conf.addProperty(SMTP_Prop.MAIL_SMTP_HOST, smtpHost);
        conf.addProperty(Main_Prop.MAIL_DEBUG, "true");

        return conf;
    }

    public static MailServiceConfiguration gmailSmtpServerConfig(String userName, String password, boolean debug) {
        MailServiceConfiguration conf = new MailServiceConfiguration();

        conf.addProperty(SMTP_Prop.MAIL_SMTP_HOST, "smtp.gmail.com");
        conf.addProperty(SMTP_Prop.MAIL_SMTP_PORT, "465");
        conf.addProperty(SMTP_Prop.MAIL_SMTP_AUTH, "true");
        conf.addProperty(SMTP_Prop.MAIL_SMTP_SOCKETFACTORY_PORT, "465");
        conf.addProperty(SMTP_Prop.MAIL_SMTP_SOCKETFACTORY_CLASS, "javax.net.ssl.SSLSocketFactory");

        conf.addProperty(AUTH_Prop.USER, userName);
        conf.addProperty(AUTH_Prop.PASS, password);

        conf.addProperty(Main_Prop.MAIL_DEBUG, Boolean.toString(debug));

        return conf;
    }
}
