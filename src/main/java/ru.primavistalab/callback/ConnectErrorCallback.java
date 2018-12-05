package ru.primavistalab.callback;

import com.sun.jna.Callback;
import com.sun.jna.win32.StdCallLibrary;
import ru.primavistalab.log.SessionLog;

public class ConnectErrorCallback implements StdCallLibrary.StdCallCallback {
  public int callback(String AErrorMessage){
    SessionLog.getInstance().Writeln(AErrorMessage);
    return 0;
  }
}
