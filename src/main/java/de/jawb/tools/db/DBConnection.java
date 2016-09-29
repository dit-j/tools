package de.jawb.tools.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class DBConnection {
    
    protected Connection _connection;
    protected Statement  _statement;
    protected ResultSet  _resultset;
    
    protected String     _driver;
    protected String     _dbHost;
    protected String     _dbName;
    protected String     _dbUser;
    protected String     _dbPass;
    
    protected String     _dbURL = null;
    
    /**
     * Konstruktor.
     * 
     * @param driver
     *            JDBC-Treiber
     * @param dbHost
     *            Hostadresse
     * @param dbName
     *            Name der Datenbank
     * @param userName
     *            Benutzername
     * @param password
     *            Password
     */
    public DBConnection(String driver, String dbHost, String dbName, String userName,
            String password) {
        _driver = driver;
        _dbHost = dbHost;
        _dbName = dbName;
        _dbUser = userName;
        _dbPass = password;
        createURL();
    }
    
    /**
     * Erstellt DBVerbindungsURL von der Form
     * <code> jdbc:<em>subprotocol</em>:<em>subname</em></code>
     */
    protected abstract void createURL();
    
    /**
     * Stellt eine Verbindung zu gegebenen DatenBank her.
     * 
     * @throws SQLException
     *             Falls Fehler auftritt.
     */
    public void connect() throws SQLException {
        try {
            Class.forName(_driver).newInstance();
        } catch (Exception e) {
            throw new SQLException(e);
        }
        _connection = DriverManager.getConnection(_dbURL);
        _connection.setAutoCommit(false);
        _statement = _connection.createStatement();
        if (_statement == null) {
            throw new SQLException("statement could not be created");
        }
    }
    
    /**
     * Alle Aenderungen werden gespeichert.
     * 
     * @throws SQLException
     *             Falls <code>COMMIT</code> fehlschlaegt.
     */
    public void commit() throws SQLException {
        if ((_connection == null) || _connection.isClosed()) {
            throw new SQLException("not connected to Database");
        }
        _connection.commit();
    }
    
    /**
     * Alle Aenderungen werden verworfen.
     * 
     * @throws SQLException
     *             Falls <code>ROLLBACK</code> fehlschlaegt.
     */
    public void rollback() throws SQLException {
        _connection.rollback();
    }
    
    /**
     * Schliesst die Verbindung zur DatenBank.
     * 
     * @throws SQLException
     *             Falls es Fehler beim Schliessen der Verbindung auftritt.
     */
    public void disconnect() throws SQLException {
        _connection.close();
        
        if (_resultset != null) {
            try {
                _resultset.close();
            } catch (SQLException e) {
                _resultset = null;
            }
            
            if (_statement != null) {
                try {
                    _statement.close();
                } catch (SQLException sqlEx) {
                    _statement = null;
                }
            }
        }
    }
    
}
