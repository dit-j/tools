package de.jawb.tools.mail._exception;

/**
 * @author dit (14.06.2012)
 */
public class MailException extends Exception {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * Konstruktor.
     */
    public MailException() {
        super();
    }
    
    /**
     * Konstruktor.
     * 
     * @param message
     * @param cause
     */
    public MailException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * Konstruktor.
     * 
     * @param message
     */
    public MailException(String message) {
        super(message);
    }
    
    /**
     * Konstruktor.
     * 
     * @param cause
     */
    public MailException(Throwable cause) {
        super(cause);
    }
    
}
