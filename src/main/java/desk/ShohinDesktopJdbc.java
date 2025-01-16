package main.java.desk;

import main.java.desk.controls.Frame1Control;

public class ShohinDesktopJdbc implements Runnable {

    public static void main(String[] args) {
        var thread = new Thread(new ShohinDesktopJdbc());
        thread.setUncaughtExceptionHandler(new OriginalUncaughtException());
        thread.start();
    }

    @Override
    public void run() {
        var f = new Frame1Control();
        f.goEvent();
    }

}