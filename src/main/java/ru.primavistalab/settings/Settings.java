package ru.primavistalab.settings;


import org.ini4j.Wini;
import ru.primavistalab.Globals;

import java.io.File;
import java.io.IOException;


@SuppressWarnings("ALL")
public class Settings {
  private static final Settings ourInstance = new Settings();
  public static Settings getInstance() {
      return ourInstance;
  }

  public String getServerIp() { return ServerIp;}

  public void setServerIp(String serverIp) {
    ServerIp = serverIp;
  }

  public String getDatabase() {  return Database;  }

  public void setDatabase(String database) {
    Database = database;
  }

  public int getServerPort() {
    return ServerPort;
  }

  public void setServerPort(int serverPort) {
    ServerPort = serverPort;
  }

  public String getUser() {
    return User;
  }

  public void setUser(String user) {
    User = user;
  }

  public String getPassword() {
    return Password;
  }

  public void setPassword(String password) {
    Password = password;
  }

  public String getClientLib() {
    return ClientLib;
  }

  public void setClientLib(String clientLib) {
    ClientLib = clientLib;
  }

  public String getEncoding() {
    return Encoding;
  }

  public void setEncoding(String encoding) {
    Encoding = encoding;
  }

  public int getSqlDialect() {
    return SqlDialect;
  }

  public void setSqlDialect(int sqlDialect) {
    SqlDialect = sqlDialect;
  }

  public boolean isConnectToDB() {
    return ConnectToDB;
  }

  public void setConnectToDB(boolean connectToDB) {
    ConnectToDB = connectToDB;
  }

  private String ServerIp;
  private String Database;
  private int ServerPort;
  private String User;
  private String Password;
  private String ClientLib;
  private String Encoding;
  private int SqlDialect;
  private boolean ConnectToDB;

  private final String INI_SECTION = "ScriptRunner";

  private Settings() {
    ServerIp = "127.0.0.1";
    Database = "";
    ServerPort = 3050;
    User = "sysdba";
    Password = "masterkey";
    ClientLib = "gds32.dll";
    Encoding = "WIN1251";
    SqlDialect = 3;
    ConnectToDB = true;
  }

  public boolean readSettings(){
    try {
      //noinspection MismatchedQueryAndUpdateOfCollection
      Wini ini = new Wini(new File(Globals.IniSettingsFile));
      this.ServerIp = ini.get(INI_SECTION, "ServerIp");
      this.Database = ini.get(INI_SECTION, "Database");
      this.ServerPort = ini.get(INI_SECTION, "ServerPort", int.class);
      this.User = ini.get(INI_SECTION, "User");
      this.Password = ini.get(INI_SECTION, "Password");
      this.ClientLib = ini.get(INI_SECTION, "ClientLib");
      this.Encoding = ini.get(INI_SECTION, "Encoding");
      this.SqlDialect = ini.get(INI_SECTION, "SqlDialect", int.class);
      this.ConnectToDB = 0 < ini.get(INI_SECTION, "ConnectToDB", int.class);
    }
    catch (IOException e) {
      return false;
    }

    return true;
  }

  public boolean writeSettings(){
    try {
      Wini ini = new Wini(new File(Globals.IniSettingsFile));
      ini.put(INI_SECTION, "ServerIp", this.ServerIp);
      ini.put(INI_SECTION, "Database", this.Database);
      ini.put(INI_SECTION, "ServerPort", this.ServerPort);
      ini.put(INI_SECTION, "User", this.User);
      ini.put(INI_SECTION, "Password", this.Password);
      ini.put(INI_SECTION, "ClientLib", this.ClientLib);
      ini.put(INI_SECTION, "Encoding", this.Encoding);
      ini.put(INI_SECTION, "SqlDialect", this.SqlDialect);
      ini.put(INI_SECTION, "ConnectToDB", this.ConnectToDB ? 1 : 0);
      ini.store();
    }
    catch (IOException e) {
      return false;
    }
    return true;
  }

  public String GetDbPath(){
    StringBuilder server = new StringBuilder();

    if (!ServerIp.isEmpty()) {
      server.append(ServerIp);
      server.append("/");
      server.append(ServerPort);
      server.append(":");
    }

    return server + Database;
  }

  public String GetIBEConnectionString(){
    String sb = "db_name=" + GetDbPath() + "; clientlib=" + getClientLib() +
      "; user_name=" + getUser() + "; password=" + getPassword() + "; lc_ctype=" +
      getEncoding() + "; sql_dialect=" + getSqlDialect();
    return sb;
  }

  @Override
  public String toString(){
    return "";
  }
}
