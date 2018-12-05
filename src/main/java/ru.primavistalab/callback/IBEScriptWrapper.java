package ru.primavistalab.callback;

import com.sun.jna.Library;
import com.sun.jna.Native;
import ru.primavistalab.Globals;


/**
 * IBEScript.dll interface wrapper
 */
public interface IBEScriptWrapper extends Library {
  IBEScriptWrapper IBEScript =
    (IBEScriptWrapper) Native.loadLibrary(Globals.IBEScriptFile, IBEScriptWrapper.class);

  int Connect(String AConnectString, ConnectErrorCallback AConnectErrorCallback);
  void Disconnect();
  void ExecScriptFile2(String AScriptFile, ScriptErrorCallback AScriptErrorCallback,
                       ScriptBeforeExecCallback AScriptBeforeExecCallback,
                       ScriptAfterExecCallback AScriptAfterExecCallback,
                       ScriptIBEBlockProgress AScriptIBEBlockProgress);
}
