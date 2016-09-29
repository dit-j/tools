package de.jawb.tools.exeptionhandling;

import static de.jawb.tools.string.StringUtil.isEmpty;

/**
 * Ausnahmenbehandlung.
 * 
 * @author dit (17.08.2011)
 */
public class AppExceptionHandlerUtil {
    
    private static final String GRAY_LINE         = "<span style=\"color:#444;display:block;\">";
    private static final String RED_LINE          = "<span style=\"color:#f00;display:block;\">";
    
    private static String       _markedLinePrefix = "de.mytourapp";
    private static final String HTML_SEPARATOR    = "<hr />";
    private static final String HTML_NEW_LINE     = "<br>";
                                                  
    private AppExceptionHandlerUtil() {
        //
    }
    
    /**
     * Erstellt aus dem StackTrace einer Ausnahme einen
     * String.
     * 
     * @param errorObj
     *            eine Ausnahme oder Error.
     * @return StackTrace eines {@link Throwable} Objekts
     *         als String.
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
     * Erstellt aus dem StackTrace einer Ausnahme einen
     * String zum Anzeigen auf einer HTML Seite
     * 
     * @param errorObj
     *            eine Ausnahme oder Error.
     * @return StackTrace eines {@link Throwable} Objekts
     *         als String.
     */
    public static String asHTML(Throwable errorObj) {
        if (errorObj == null) {
            return "getStackTraceAsWebString# errorObj=null";
        }
        StringBuilder sb = new StringBuilder();
        
        String msg = getErrorMessage(errorObj);
        sb.append("<b>").append(msg).append("</b>");
        
        sb.append(HTML_NEW_LINE);
        sb.append(HTML_SEPARATOR);
//        sb.append(HTML_NEW_LINE);

//        sb.append("<span class=\"exceptionMsg\">");
//        sb.append(errorObj.toString());
//        sb.append(HTML_NEW_LINE);
//        sb.append("</span>");
        
        StackTraceElement[] items = errorObj.getStackTrace();
        int i = 0;
        for (StackTraceElement stackTraceElement : items) {
            String stackTraceLine = stackTraceElement.toString();
            if (stackTraceLine.startsWith(_markedLinePrefix)) {
                sb.append(RED_LINE);
            } else {
                sb.append(GRAY_LINE);
            }
            
            int j = i++;
            while (j-- > 0)
                sb.append("&nbsp;&nbsp;&nbsp;");
                
            sb.append(stackTraceLine).append("</span>");
        }
        
        return sb.toString();
    }
    
    /**
     * Gibt die Fehlernachricht einer Exception zurueck.
     * Falls Exception keine Nachricht enthaelt wird
     * {@link AppExceptionHandlerUtil#getStackTraceAsString(Throwable)}
     * als Fehlermeldung zurueckgegeben.
     * 
     * @param e
     *            Ausnahme
     * @return Fehlermeldung.
     */
    public static String getErrorMessage(Throwable e) {
        String error = e.getMessage();
        if (isEmpty(error)) {
            Throwable cause = e.getCause();
            if (cause != null) {
                error = cause.getMessage();
            }
            if ((error == null) || (error.length() == 0)) {
                error = getStackTraceAsString(e);
            }
        }
        return e.getClass().getSimpleName() + ": " + error;
    }
    
    /**
     * Gibt die Fehlernachricht einer Exception zurueck.
     * Falls Exception keine Nachricht enthaelt wird
     * {@link AppExceptionHandlerUtil#getStackTraceAsString(Throwable)}
     * als Fehlermeldung zurueckgegeben.
     * 
     * @param e
     *            Ausnahme
     * @return Fehlermeldung.
     */
    public static String getNestedErrorMessage(Exception e) {
        
        String error = null;
        
        Throwable cause = e.getCause();
        if (cause != null) {
            error = cause.getMessage();
        }
        if ((error == null) || (error.length() == 0)) {
            error = getStackTraceAsString(e);
        }
        return error;
    }
    
    /**
     * Ruft die Methode {@link Throwable#printStackTrace()}
     * aus
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
    
    public static final void setMarkedLinePrefix(String prefix) {
        _markedLinePrefix = prefix;
    }
    
    public static void main(String[] args) {
        System.out.println(asHTML(new RuntimeException("blabla blabla")));
    }
}
