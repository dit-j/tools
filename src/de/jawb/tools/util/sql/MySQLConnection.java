package de.jawb.tools.util.sql;

/**
 * OberKlasse fuer alle Klassen mit deren Hilfe man die
 * Verbindung zum <tt>MySQL</tt>-DatenkBank herstellen kann
 * und bestimmte Befehle auszufuehren.
 * 
 * @author dit (14.10.10)
 */
public abstract class MySQLConnection extends DBConnection {
    
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    
    /**
     * @param dbHost
     *            Hostadresse
     * @param dbName
     *            Name der Datenbank
     * @param userName
     *            Benutzername
     * @param password
     *            Password
     */
    public MySQLConnection(String dbHost, String dbName, String userName, String password) {
        super(JDBC_DRIVER, dbHost, dbName, userName, password);
        
    }
    
    @Override
    protected final void createURL() {
        StringBuilder sb = new StringBuilder("jdbc:mysql://");
        sb.append(_dbHost);
        sb.append("/");
        sb.append(_dbName);
        sb.append("?user=");
        sb.append(_dbUser);
        sb.append("&password=");
        sb.append(_dbPass);
        this._dbURL = sb.toString();
    }
    
}
