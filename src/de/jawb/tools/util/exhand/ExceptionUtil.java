package de.jawb.tools.util.exhand;

import de.jawb.tools.util.string.StringUtil;

/**
 * Ausnahmenbehandlung.
 * 
 * @author dit (17.08.2011)
 */
public class ExceptionUtil {
    
    protected static String     _markedLinePrefix;
    
    private static final String HTML_LINE     = "<hr />";
    private static final String HTML_NEW_LINE = "<br>";
    
    private ExceptionUtil() {
        _markedLinePrefix = "de.jawb.";
    }
    
    /**
     * Erstellt aus dem StackTrace einer Ausnahme einen String.
     * 
     * @param errorObj
     *            eine Ausnahme oder Error.
     * @return StackTrace eines {@link Throwable} Objekts als String.
     */
    public static String getStackTraceAsString(Throwable errorObj) {
        if (errorObj == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder(errorObj.toString() + "\n");
        StackTraceElement[] items = errorObj.getStackTrace();
        for (StackTraceElement stackTraceElement : items) {
            sb.append(stackTraceElement.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
    
    /**
     * Erstellt aus dem StackTrace einer Ausnahme einen String zum Anzeigen auf
     * einer HTML Seite
     * 
     * @param errorObj
     *            eine Ausnahme oder Error.
     * @return StackTrace eines {@link Throwable} Objekts als String.
     */
    public static String getStackTraceAsWebString(Throwable errorObj) {
        if (errorObj == null) {
            return "getStackTraceAsWebString# errorObj=null";
        }
        String msg = errorObj.getMessage();
        StringBuilder sb = new StringBuilder(HTML_NEW_LINE);
        sb.append("<b>");
        sb.append(msg);
        sb.append("</b>");
        sb.append(HTML_NEW_LINE);
        sb.append(HTML_NEW_LINE);
        sb.append(HTML_LINE);
        sb.append(HTML_NEW_LINE);
        sb.append(errorObj.toString() + HTML_NEW_LINE);
        StackTraceElement[] items = errorObj.getStackTrace();
        for (StackTraceElement stackTraceElement : items) {
            String stackTraceLine = stackTraceElement.toString();
            if (stackTraceLine.startsWith(_markedLinePrefix)) {
                sb.append("<span><b>");
                sb.append(stackTraceLine);
                sb.append("</span></b>");
            } else {
                sb.append("<span><small>");
                sb.append(stackTraceLine);
                sb.append("</span></small>");
            }
            sb.append(HTML_NEW_LINE);
        }
        return sb.toString();
    }
    
    /**
     * Gibt die Fehlernachricht einer Exception zurueck. Falls Exception keine
     * Nachricht enthaelt wird
     * {@link ExceptionUtil#getStackTraceAsString(Throwable)} als
     * Fehlermeldung zurueckgegeben.
     * 
     * @param e
     *            Ausnahme
     * @return Fehlermeldung.
     */
    public static String getErrorMessage(Throwable e) {
        String error = e.getMessage();
        if (StringUtil.isEmpty(error)) {
            
            error = e.getClass() + " ";
            
            Throwable cause = e.getCause();
            if (cause != null) {
                error += cause.getMessage();
            }
            if (StringUtil.isEmpty(error)) {
                error += getStackTraceAsString(e);
            }
        }
        return error;
    }
    
    /**
     * Ruft die Methode {@link Throwable#printStackTrace()} aus
     * 
     * @param throwable
     *            Objekt des Typs {@link Throwable}
     */
    public static void printStackTrace(Throwable throwable) {
        throwable.printStackTrace();
    }
    
    /**
     * Aktuellen Stacktrace ausgeben.
     */
    public static void printStackTrace() {
        printStackTrace(new Exception());
    }
    
//    /**
//     * Setzt die Fehlermeldung als {@link SessionAttributes#LAST_ERROR} in
//     * session.
//     *
//     * @param e
//     *            Exception
//     * @param session
//     *            Session des Klients
//     */
//	public  void handleException(Exception e, HttpSession session) {
//		if (!(e instanceof NotLoggedInException)) {
//			SessionUtil.setInfo("criticalError", getStackTraceAsWebString(e), session);
//			handleException(e);
//		}
//	}
    
//    public void handleException(Exception e) {
//        // ErrorLog.getInstance().log(e);
//
//        if (e instanceof MessagingException) {
//            _mailServiceError++;
//        }
//
//        if (_mailServiceError < 5) {
//            if (!(e instanceof BadLoginDataException)) {
//                // NotifyService.notifyAdmin(getStackTraceAsWebString(e));
//            }
//        }
//    }
    
    public static final void setMarkedLinePrefix(String prefix) {
        _markedLinePrefix = prefix;
    }
}
