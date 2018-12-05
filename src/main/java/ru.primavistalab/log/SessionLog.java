package ru.primavistalab.log;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;

public class SessionLog {
  private static final String LOG_PATH = "u:\\IntelliJ IDEA 2018.2.6\\projects\\src\\main\\resources\\logs\\";
  private static final String LOG_FILE_TEMPLATE = "%1$sjsr-%2$tY-%2$tm-%2$td %2$tH-%2$tM-%2$tS.log";
  private PrintWriter logFile;

  private static SessionLog ourInstance = new SessionLog();
  public static SessionLog getInstance() {
    return ourInstance;
  }

  private SessionLog() {
    String logFileName = String.format(LOG_FILE_TEMPLATE, LOG_PATH, new Date());
    try {
      logFile = new PrintWriter(logFileName, "UTF-8");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
  }

  public void Writeln(String AMessage){
    String timeStampMsg = String.format("[%1$td.%1$tm.%1$tY %1$tH:%1$tM:%1$tS.%1$tL] %2$s", new Date(), AMessage);
    logFile.println(timeStampMsg);
    logFile.flush();
  }

  public void Close(){
    logFile.flush();
    logFile.close();
  }

}
