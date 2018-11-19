/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package amer.common;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.PooledConnection;
import oracle.jdbc.pool.OracleConnectionPoolDataSource;
import org.openide.LifecycleManager;

/**
 *
 * @author S53788
 */
public class dbHandler {

  private String DriverType;
  private String ServerName;
  private int PortNumber;
  private String ServiceName;
  private String User;
  private String Password;
  private boolean isConnected;
  private Connection conn;
  private int queryCount;
  private String connString;
  // controller booleans
  private boolean useConnString;
  private boolean usernameSet;
  private boolean hasError;
  private Statement stmt;
  private String dbname;
  private boolean badlogin;

  public dbHandler(String database_name) {
    isConnected = false;
    usernameSet = false;
    hasError = true;
    dbname = database_name;
    badlogin = false;
  }

  /**
   * Manually set the required information for this oracle connection
   *
   * @param driver_type The type of driver to use
   * @param server_name IP / host name of the db server
   * @param port_no Port no for that database
   * @param service_name Service Name / SID
   */
  public void setDBConnInfo(String driver_type, String server_name, int port_no, String service_name) {
    this.DriverType = driver_type;
    this.PortNumber = port_no;
    this.ServerName = server_name;
    this.ServiceName = service_name;
    this.useConnString = false;
    this.hasError = false;
  }

  /**
   * Set the connection info using the connection string
   *
   * @param connection_string
   */
  public void setDBConnInfo(String connection_string) {
    this.connString = connection_string;
    this.useConnString = true;
    this.hasError = false;

  }

  /**
   * Set the username and password for this connection
   *
   * @param user
   * @param pass
   */
  public void setUserPass(String user, String pass) {
    this.User = user;
    this.Password = pass;
    this.usernameSet = true;
  }

  /**
   * Try to open the connection to the database
   *
   * @throws SQLException This exception is thrown when the attempt to connect
   * exceed 20 times
   */
  public void openConnection() throws SQLException {
    //CommonUtility.println("Connecting to " + dbname);
    if (!usernameSet) {
      System.err.println("Username and password is not set");
      throw new SQLException("Username / pass is not set");
    }

    if (hasError) {
      System.err.println("Connection info is not set");
      throw new SQLException("Connection info is not set");
    }

//    int attemptleft = 20;

    OracleConnectionPoolDataSource asd = new OracleConnectionPoolDataSource();
    if (this.useConnString) {
      asd.setURL(connString);
      //CommonUtility.println(connString);
    } else {
      asd.setDriverType(DriverType);
      asd.setServerName(ServerName);
      asd.setPortNumber(PortNumber);
      asd.setServiceName(ServiceName);
    }

    asd.setUser(User);
    asd.setPassword(Password);
    
    

    while (!isConnected) {

//      WaitTimeout wt = new WaitTimeout();
//      Thread twt = new Thread(wt);

//      twt.start();
      
      try {
        PooledConnection pc = asd.getPooledConnection();
        conn = pc.getConnection();
        isConnected = true;
        
        
      } catch (SQLException se) {
        if (se.getMessage().contains("logon") || se.getMessage().contains("username")) {
          Utilities.popup("Incorrect DB password. Do not attempt to rerun. Contact Amer for further assistance.");
          badlogin = true;
          LifecycleManager.getDefault().exit(1);
        } else {
          throw se;
        }
      }

      
      
      
      if (conn != null) {
        //CommonUtility.println("Connected to: " + dbname);
        stmt = conn.createStatement();
        isConnected = true;
        queryCount = 0;
      } else {
        throw new SQLException("Unable to connect");
      } 

    }
  }

  /**
   * Try to close the active connection
   *
   * @throws SQLException
   */
  public void closeConnection() throws SQLException {
    if (isConnected) {
      stmt.close();
      conn.close();
    }

    isConnected = false;
    queryCount = 0;
    //CommonUtility.println("Connection closed: " + dbname);
  }

  /**
   * Try to re-establish the connection
   *
   * @throws SQLException
   */
  public void reconnect() throws SQLException {
    closeConnection();
    openConnection();
  }

  /**
   * Execute the given select query and return the result It is not recommended
   * to use the same connection to query other result while the resultset is
   * still being used
   *
   * @param query SQL select query
   * @return Resultset for the query
   * @throws SQLException
   */
  public ResultSet executeSelect(String query) throws SQLException {

    if (!isConnected) {
      openConnection();
    }

    queryCount++;

    try {
      return stmt.executeQuery(query);
    } catch (SQLException sqle) {
      System.err.println("Error - " + query);
      throw sqle;
    }
  }

  public boolean executeUpdate(String query) throws SQLException {

    if (!isConnected) {
      openConnection();
    }

    queryCount++;

    try {
      return stmt.executeUpdate(query) == 0;
    } catch (SQLException e) {
      System.err.println("Error - " + query);
      throw e;
    }

  }

  public static String dbGetString(ResultSet rs, int pos) throws SQLException {
    String ret = "";

    if (rs.getString(pos) == null) {
      ret = "";
    } else {
      ret = rs.getString(pos);
    }

    return ret;
  }

  public static String dbGetString(ResultSet rs, String pos) throws SQLException {
    String ret = "";

    if (rs.getString(pos) == null) {
      ret = "";
    } else {
      ret = rs.getString(pos);
    }

    return ret;
  }

  public boolean isCOnnected() {
    return isConnected;
  }

  public PreparedStatement createPS(String sql) throws SQLException {
    return conn.prepareStatement(sql);
  }
  
  class WaitTimeout implements Runnable{
    
    @Override
    public void run() {
      
      // sleep for 2 minutes
      try {
        Thread.sleep(30000);
      } catch (InterruptedException e) {
        Utilities.log("db", "timeout wait interrupted. ", 0);
      }
      
      // if still not connected, kill the tryconnect thread
      if(!isConnected){
        Utilities.popup("Timed out connecting to DB. Try connecting to VPN first");
        LifecycleManager.getDefault().exit(1);
        
      }
      
      
    }
    
  }


}
