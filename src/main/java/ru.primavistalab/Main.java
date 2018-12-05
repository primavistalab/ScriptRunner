package ru.primavistalab;

import ru.primavistalab.callback.ScriptAfterExecCallback;
import ru.primavistalab.callback.ScriptBeforeExecCallback;
import ru.primavistalab.callback.ConnectErrorCallback;
import ru.primavistalab.callback.ScriptErrorCallback;
import ru.primavistalab.callback.IBEScriptWrapper;
import ru.primavistalab.callback.ScriptIBEBlockProgress;
import ru.primavistalab.log.SessionLog;
import ru.primavistalab.settings.Settings;

import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.nio.file.Files;

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;

public class Main {
  public static void main(String[] args) {
    try {
      SessionLog.getInstance().Writeln("Welcome to FBScriptRunner (Java version) v1.0\r\n");

      if (args.length == 0) {
        SessionLog.getInstance().Writeln("Не задан(ы) SQL-файл(ы) для выполнения.");
        return;
      }

      if (!Files.exists(Paths.get(Globals.IBEScriptFile), LinkOption.NOFOLLOW_LINKS)){
        SessionLog.getInstance().Writeln("Не найден файл dll '" + Globals.IBEScriptFile + "'");
        return;
      }

      if (!Files.exists(Paths.get(Globals.IniSettingsFile), LinkOption.NOFOLLOW_LINKS)){
        SessionLog.getInstance().Writeln("Не найден файл настроек '" + Globals.IniSettingsFile + "'");
        return;
      }

      if (!Settings.getInstance().readSettings()) {
        SessionLog.getInstance().Writeln("Ошибка чтения настроек из файла '" +
          Globals.IniSettingsFile + "'");
        return;
      }

      IBEScriptWrapper IBEScript = IBEScriptWrapper.IBEScript;

      if (Settings.getInstance().isConnectToDB()) {
        if (IBEScript.Connect(Settings.getInstance().GetIBEConnectionString(), new ConnectErrorCallback()) != 0) {
          SessionLog.getInstance().Writeln("Ошибка подключения к базе данных '" + Settings.getInstance().GetDbPath() + "'\r\n" +
            "Проверьте параметры подключения к БД.");
          return;
        }
        else {
          SessionLog.getInstance().Writeln("Успешное подключение к базе данных '" + Settings.getInstance().GetDbPath() + "'");
          IBEScript.Disconnect();
        }
      }

      for (String sqlFile : args) {
        try {
          if (Settings.getInstance().isConnectToDB()) {
            IBEScript.Connect(Settings.getInstance().GetIBEConnectionString(), new ConnectErrorCallback());
          }

          SessionLog.getInstance().Writeln("Выполнение скрипта '" + sqlFile + "'");
          Globals.LastScriptSuccess = true;

          IBEScript.ExecScriptFile2(sqlFile, new ScriptErrorCallback(),
            new ScriptBeforeExecCallback(), new ScriptAfterExecCallback(), new ScriptIBEBlockProgress());

          if (Globals.LastScriptSuccess) {
            SessionLog.getInstance().Writeln("Скрипт успешно выполнен.");
          }
          else {
            break;
          }
        }
        finally {
          if (Settings.getInstance().isConnectToDB()) {
            IBEScript.Disconnect();
          }
        }

      } // for (String sqlFile : args)
    } // try
    finally {
      SessionLog.getInstance().Close();
    }
  }
}

