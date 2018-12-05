package ru.primavistalab.callback;

import com.sun.jna.win32.StdCallLibrary;

public class ScriptIBEBlockProgress implements StdCallLibrary.StdCallCallback {
  public int callback(String AProgressMessage){
    return 0;
  }
}
