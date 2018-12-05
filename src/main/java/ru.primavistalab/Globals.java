package ru.primavistalab;
import java.nio.file.Paths;

public class Globals {
  private static final String DLL_NAME =  "IBEScript.dll";
  private static final String INI_NAME =  "ScriptRunner.ini";

  public static boolean LastScriptSuccess = false;
  public static String IBEScriptFile;
  public static String IniSettingsFile;

  static {
    String currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
    IBEScriptFile = currentPath + "\\" + DLL_NAME;
    IniSettingsFile = currentPath + "\\" + INI_NAME;
  }
}
