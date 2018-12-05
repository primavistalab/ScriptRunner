package ru.primavistalab.callback;

import com.sun.jna.Callback;
import com.sun.jna.win32.StdCallLibrary;
import ru.primavistalab.Globals;

public class ScriptAfterExecCallback implements StdCallLibrary.StdCallCallback {
  public int callback(String AStmtText, int ASuccess){
    if (ASuccess == 1)
      return 0;
    else {
      Globals.LastScriptSuccess = false;
      return 1;  // Abort script execution
    }
  }
}
