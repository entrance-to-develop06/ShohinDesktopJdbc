package main.java.desk;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/** 最新の例外格納クラス
 * @author none **/
public class LastException {
    protected static String lastExcepTitle = "";
    protected static String lastExcepPlace = "";
    protected static String lastExcepParam = "";
    protected static String lastExcepMessage = "";
    protected static String lastExcepTrace = "";
    private static final String FILE_NAME = "Exception.txt";

    /** 最新の例外情報をセットします
     * @param method 例外が発生したメソッド名
     * @param param 例外の発生したメソッドが引き受けた引数、参考値など
     * @param ex Exceptionまたは派生クラス **/
    public static void setLastException(String method, String param, Exception ex) {
        lastExcepTitle = ex.getClass().getName();
        lastExcepPlace = method;
        lastExcepParam = param;
        lastExcepMessage = ex.getMessage();
        var sw = new StringWriter();
        try(var pw = new PrintWriter(sw);) {
            ex.printStackTrace(pw);
            pw.flush();
            lastExcepTrace = sw.toString();
        }
    }

    public static void logWrite() {
        Logger logger = Logger.getLogger(OriginalUncaughtException.class.getName());
        try {
            Handler handler = new FileHandler(FILE_NAME, true);
            handler.setFormatter(new SimpleFormatter()); //デフォルトはxml形式なのでフォーマットを変更
            logger.addHandler(handler);
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
        logger.log(Level.WARNING, lastExcepTrace);
    }

    /** 最新の例外のクラス名が格納されます
     * @return String **/
    public static String getLastExcepTitle() {
        return lastExcepTitle;
    }
    /** 最新の例外のクラス名が格納されます
     * @param value **/
    public static void setLastExcepTitle(String value) {
        lastExcepTitle = value;
    }

    /** 最新の例外のメソッド名が格納されます
     * @return String **/
    public static String getLastExcepPlace() {
        return lastExcepPlace;
    }
    /** 最新の例外のメソッド名が格納されます
     * @param value **/
    public static void setLastExcepPlace(String value) {
        lastExcepPlace = value;
    }

    /** 最新の例外のパラメータが格納されます<br />
     * (メソッドに与えた引数や参考になる情報など)
     * @return String **/
    public static String getLastExcepParam() {
        return lastExcepParam;
    }
    /** 最新の例外のパラメータが格納されます<br />
     * (メソッドに与えた引数や参考になる情報など)
     * @param value **/
    public static void setLastExcepParam(String value) {
        lastExcepParam = value;
    }

    /** 最新の例外のメッセージが格納されます
     * @return String **/
    public static String getLastExcepMessage() {
        return lastExcepMessage;
    }
    /** 最新の例外のメッセージが格納されます
     * @param value **/
    public static void setLastExcepMessage(String value) {
        lastExcepMessage = value;
    }

    /** 最新の例外のスタックトレースが格納されます
     * @return String **/
    public static String getLastExcepTrace() {
        return lastExcepTrace;
    }
    /** 最新の例外のメッセージが格納されます
     * @param value **/
    public static void setLastExcepTrace(String value) {
        lastExcepTrace = value;
    }
}