package main.java.desk;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread.UncaughtExceptionHandler;

/** 最新の例外格納クラス
 * @author none **/
public class OriginalUncaughtException extends LastException implements UncaughtExceptionHandler {
    //集約例外プロシージャー
    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        //例外の内容やトレース内容をLogに出力したい場合やユーザーに画面出力したい場合にここへ書きます。

        lastExcepTitle = throwable.getClass().getName();
        lastExcepPlace = Thread.currentThread().getStackTrace()[1].getMethodName();
        lastExcepParam = "";
        lastExcepMessage = throwable.getMessage();
        var sw = new StringWriter();
        try(var pw = new PrintWriter(sw);) {
            throwable.printStackTrace(pw);
            pw.flush();
            lastExcepTrace = sw.toString();
        }
        logWrite();
    }
}