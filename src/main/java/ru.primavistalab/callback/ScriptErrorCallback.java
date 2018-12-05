package ru.primavistalab.callback;

import com.sun.jna.Callback;
import com.sun.jna.win32.StdCallLibrary;
import ru.primavistalab.log.SessionLog;

public class ScriptErrorCallback implements StdCallLibrary.StdCallCallback {
  public int callback(String AStmtText, String AErrMessage){
    SessionLog.getInstance().Writeln("Ошибка выполнения скрипта:");
    SessionLog.getInstance().Writeln(AStmtText);
    SessionLog.getInstance().Writeln(AErrMessage);
    return 1;
  }
}
