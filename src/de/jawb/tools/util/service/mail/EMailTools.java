package de.jawb.tools.util.service.mail;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author dit (15.06.2012)
 */
public class EMailTools {
    
    /**
     * Startet die in dem System definierte Standardsoftware
     * zum versenden der Emails.
     * 
     * @param list
     *            Empfaengerliste.
     * @throws URISyntaxException
     *             falls URI ungueltig ist.
     * @throws IOException
     *             Fehler beim Senden.
     */
    public static void sendEmailUsingOSSoftware(String... list) throws IOException,
            URISyntaxException {
        StringBuilder sb = new StringBuilder("mailto:");
        for (int i = 0; i < list.length; i++) {
            sb.append(list[i]);
            if (i != (list.length - 1)) {
                sb.append(";");
            }
        }
        Desktop.getDesktop().mail(new URI(sb.toString()));
    }
    
}
