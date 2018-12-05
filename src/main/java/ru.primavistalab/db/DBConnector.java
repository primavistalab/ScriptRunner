package ru.primavistalab.db;

import ru.primavistalab.settings.Settings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Класс подключения к FB
 */
public class DBConnector {
  private static DBConnector ourInstance = new DBConnector();
  public static DBConnector getInstance() {
    return ourInstance;
  }
  private DBConnector(){}

  private Connection FBConnection = null;

  public boolean Connect(Settings ASettings) {
    try {
      this.Close();

      final String DRIVER = "org.firebirdsql.jdbc.FBDriver";

      // jdbc:firebirdsql://127.0.0.1:3050/c:\form_103\data\PARTPOST.IB

      Class.forName(DRIVER);
      String url = "jdbc:firebirdsql://" + ASettings.getServerIp() +
        ":" +
        ASettings.getServerPort() +
        "/" +
        ASettings.getDatabase() +
        "?encoding=" +
        ASettings.getEncoding() +
        "&wireCrypt=DISABLED" +
        "&clientlib=" +
        ASettings.getClientLib() +
        "&sqldialect=" +
        ASettings.getSqlDialect();
      FBConnection = DriverManager.getConnection(url, ASettings.getUser(), ASettings.getPassword());
    } catch (ClassNotFoundException ex) {
      ex.printStackTrace();
      return false;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  public void Close(){
    if (FBConnection != null){
      try {
        FBConnection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
      finally {
        FBConnection = null;
      }
    }
  }
}
