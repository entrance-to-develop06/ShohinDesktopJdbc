package main.java.desk.controls;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import main.java.desk.models.appservices.BusinessAppException;
import main.java.desk.models.appservices.ShohinAppService;
import main.java.desk.models.domainobjects.DomainObjectException;
import main.java.desk.models.repositorys.ShohinRepositoryImpl;

public class Frame1Control implements ActionListener, ListSelectionListener {

    private Frame1Design window;
    private ShohinAppService service = new ShohinAppService(new ShohinRepositoryImpl());

    public Frame1Control() {

    }

    public void goEvent() {
        window = new Frame1Design();
        window.tableSetting();
        window.getButtonRead().addActionListener(this);
        window.getButtonAdd().addActionListener(this);
        window.getButtonChange().addActionListener(this);
        window.getButtonErase().addActionListener(this);
        window.getTable1().getSelectionModel().addListSelectionListener(this);

        //window.getFwindow().add(window.getFpanel());
        window.getFWindow().getContentPane().add(window.getFPanel(), BorderLayout.CENTER);
        window.getFWindow().setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (window.getButtonRead().equals(event.getSource())) {
            readAll();
        } else if (window.getButtonAdd().equals(event.getSource())) {
            add();
        } else if (window.getButtonChange().equals(event.getSource())) {
            change();
        } else {
            erase();
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent listevent) {
        if(listevent.getValueIsAdjusting()) {
            return;
        }
        window.getTableRowSetTextField();
    }

    private void readAll() {
        window.getDTableModel().setRowCount(0);

        var list = service.getAllShohinList();

        for (int i = 0; i < list.size(); i++) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            String date = "";
            try {
                var wdate = sdf.parse(list.get(i).getEditDate().toString());
                date = format.format(wdate);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            var time = new StringBuilder(list.get(i).getEditTime().toString());
            if (time.length() < 6)
                time.insert(0, "0");
            time = time.insert(4, ':').insert(2, ':');
            Object[] obj = {list.get(i).getUniqueId(),
                    list.get(i).getShohinCode(),
                    list.get(i).getShohinName(),
                    date,
                    time,
                    list.get(i).getRemarks()};
            window.getDTableModel().addRow(obj);
        }

        window.getLabelArea().append("全件表示しました。\n");
    }

    private void add() {
        try {
            service.registerShohin(window.getTextShohinCode().getText(), window.getTextShohinName().getText(), window.getTextRemarks().getText());
        }
        catch (DomainObjectException | BusinessAppException e) {
            msgDialogModal("タイトル", e.getMessage(), 2);
            return;
        }

        window.getLabelArea().append("1件追加しました。\n");
        msgDialogModal("タイトル", "追加しました。", 1);
    }

    private void change() {
        var id = window.getLabelUuid().getText();
        var code = window.getTextShohinCode().getText();
        var name = window.getTextShohinName().getText();
        var note = window.getTextRemarks().getText();
        try {
            service.editShohin(id, code, name, note);
        }
        catch (DomainObjectException | BusinessAppException e) {
            msgDialogModal("エラー", e.getMessage(), 2);
            return;
        }

        window.getLabelArea().append("1件更新しました。\n");
        msgDialogModal("情報", "更新しました。", 1);
    }

    private void erase() {
        try {
            service.removeShohin(window.getLabelUuid().getText());
        }
        catch (DomainObjectException | BusinessAppException e) {
            msgDialogModal("エラー", e.getMessage(), 2);
            return;
        }

        window.getLabelArea().append("1件削除しました。\n");
        msgDialogModal("タイトル", "該当商品を削除しました。", 1);
    }

    private void msgDialogModal(String title, String message, int type) {
        JOptionPane.showMessageDialog(window.getFWindow(), message, title, type);
    }
}