package main.java.desk;

import java.io.Serializable;

/**
 * メイン画面のデザイン設定値格納
 */
public class Frame1Config implements Serializable { //extends FileConfig {
    private String buttonRead;
    private String buttonAddText;
    private int buttonAddX;
    private int buttonAddY;
    private int buttonAddW;
    private int buttonAddH;
    private String buttonChange;
    private String buttonErase;

    public String getButtonRead() {
        return buttonRead;
    }
}