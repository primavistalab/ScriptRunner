package ru.primavistalab.callback;

import com.sun.jna.Callback;
import com.sun.jna.win32.StdCallLibrary;

public class ScriptBeforeExecCallback implements StdCallLibrary.StdCallCallback {
  public int callback(String AStmtText, String AMessage){
    return 0;
  }
}
