package de.jawb.tools.exeption;

import de.jawb.tools.string.StringUtil;

/**
 * Ausnahmenbehandlung.
 *
 * @author dit (17.08.2011)
 */
public class ExceptionUtil {

    private static final String GRAY_LINE     = "<span style=\"color:#444;display:block;\">";
    private static final String RED_LINE      = "<span style=\"color:#f00;display:block;\">";
    private static final String HTML_LINE     = "<hr />";
    private static final String HTML_NEW_LINE = "<br>";

    /**
     * Erstellt aus dem StackTrace einer Ausnahme einen String.
     *
     * @param errorObj
     *            eine Ausnahme oder Error.
     * @return StackTrace eines {@link Throwable} Objekts als String.
     */
    public static String getStackTraceAsString(Throwable errorObj) {
        StringBuilder sb = new StringBuilder(errorObj.toString()).append("\n");
        StackTraceElement[] items = errorObj.getStackTrace();
        for (StackTraceElement stackTraceElement : items) {
            sb.append(stackTraceElement.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Erstellt aus dem StackTrace einer Ausnahme einen String zum Anzeigen auf einer HTML Seite
     *
     * @param errorObj
     *            eine Ausnahme oder Error.
     * @return StackTrace eines {@link Throwable} Objekts als String.
     */
    public static String asHTML(Throwable errorObj, String highlightPackage) {

        StringBuilder sb = new StringBuilder();

        String msg = getErrorMessage(errorObj);
        sb.append("<b>").append(msg).append("</b>");

        sb.append(HTML_NEW_LINE);
        sb.append(HTML_LINE);

        StackTraceElement[] items = errorObj.getStackTrace();
        int i = 0;
        for (StackTraceElement stackTraceElement : items) {
            String stackTraceLine = stackTraceElement.toString();
            if (highlightPackage != null && stackTraceLine.startsWith(highlightPackage)) {
                sb.append(RED_LINE);
            } else {
                sb.append(GRAY_LINE);
            }

            int j = i++;
            while (j-- > 0){
                sb.append("&nbsp;&nbsp;&nbsp;");
            }

            sb.append(stackTraceLine).append("</span>");
        }

        return sb.toString();
    }

    /**
     * Gibt die Fehlernachricht einer Exception zurueck. Falls Exception keine Nachricht enthaelt wird
     * {@link ExceptionUtil#getStackTraceAsString(Throwable)} als Fehlermeldung zurueckgegeben.
     *
     * @param e
     *            Ausnahme
     * @return Fehlermeldung.
     */
    public static String getErrorMessage(Throwable e) {
        String error = e.getMessage();
        if (StringUtil.isEmpty(error)) {

            error = e.getClass().getName();

            Throwable cause = e.getCause();
            if (cause != null) {
                error = error + " " + cause.getMessage();
            }
        }
        return error;
    }

    /**
     * Aktuellen Stacktrace ausgeben.
     */
    public static void printStackTrace() {
        new Exception().printStackTrace();
    }

}
