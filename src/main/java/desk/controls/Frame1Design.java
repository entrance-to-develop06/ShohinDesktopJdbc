package main.java.desk.controls;

import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.table.TableColumn;

public class Frame1Design {
    private JFrame fWindow = new JFrame();
    private JPanel fPanel = new JPanel();
    private JTable table1;
    private JScrollPane scroll1;
    private DefaultTableModel dTableModel;
    private String[] headers = {"UUID", "商品番号", "商品名", "編集日付", "編集時刻", "備考"};
    private JTextArea labelArea = new JTextArea();
    private JLabel labelUuid = new JLabel();
    private JLabel labelFoot = new JLabel();
    private JTextField textShohinCode = new JTextField();
    private JTextField textShohinName = new JTextField();
    private JTextField textRemarks = new JTextField();
    private JButton buttonRead = new JButton();
    private JButton buttonAdd = new JButton();
    private JButton buttonChange = new JButton();
    private JButton buttonErase = new JButton();

    public Frame1Design() {
        createWindow();
    }

    private JLabel labelsSetting(String name, String txt, int x, int y, int w, int h) {
        var label = new JLabel();
        label.setName(name);
        label.setText(txt);
        label.setBounds(x, y, w, h);
        fPanel.add(label);

        return label;
    }

    private JComponent componentsSetting(JComponent ctl, String name, int x, int y, int w, int h) {
        ctl.setName(name);
        ctl.setBounds(x, y, w, h);
        fPanel.add(ctl);
        return ctl;
    }

    private void createWindow() {
        fWindow.setTitle("商品管理アプリ(Jdbc Driver + デスクトップ + SQLServer)");
        fWindow.setLocation(500,200);
        fWindow.setSize(800, 600);
        fWindow.setUndecorated(false); // タイトルバー表示・非表示
        fWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dTableModel = new DefaultTableModel(headers, 0);
        table1 = new JTable(dTableModel);
        scroll1 = new JScrollPane(table1);
        scroll1.setBounds(25,25,730,200);
        fPanel.setPreferredSize(new Dimension(700, 100));
        fPanel.add(scroll1);

        labelArea.setText("");
        labelArea.setFocusable(false);
        labelArea = (JTextArea)componentsSetting(labelArea, "labelArea", 25, 235, 350, 200);
        labelsSetting("label1", "ユニークID：", 400, 250, 100, 25);
        labelsSetting("label2", "商品番号：", 400, 300, 100, 25);
        labelsSetting("label3", "商品名：", 400, 350, 100, 25);
        labelsSetting("label4", "備考：", 400, 400, 100, 25);
        labelUuid.setHorizontalAlignment(JLabel.RIGHT);
        labelUuid = (JLabel)componentsSetting(labelUuid, "labelUuid", 475, 250, 270, 25);
        labelFoot.setText("Copyright  ©  2021-2025  entrance-to-develop06");
        labelFoot = (JLabel)componentsSetting(labelFoot, "labelFoot", 30, 530, 500, 25);

        textShohinCode = (JTextField)componentsSetting(textShohinCode, "textShohinCode", 600, 300, 150, 25);
        textShohinName = (JTextField)componentsSetting(textShohinName, "textShohinName", 550, 350, 200, 25);
        textRemarks = (JTextField)componentsSetting(textRemarks, "textRemarks", 450, 400, 300, 25);

        buttonRead.setText("抽出");
        buttonRead = (JButton)componentsSetting(buttonRead, "buttonRead", 50, 470, 150, 50);
        buttonAdd.setText("追加");
        buttonAdd = (JButton)componentsSetting(buttonAdd, "buttonAdd", 230, 470, 150, 50);
        buttonChange.setText("更新");
        buttonChange = (JButton)componentsSetting(buttonChange, "buttonChange", 410, 470, 150, 50);
        buttonErase.setText("削除");
        buttonErase = (JButton)componentsSetting(buttonErase, "buttonErase", 590, 470, 150, 50);
        fPanel.setLayout(null);
    }

    public void getTableRowSetTextField() {
        if (dTableModel.getRowCount() > 0) { //setRowCount(0)で全表示行クリアした後にもvalueChange()メソッドが呼ばれるので追加
            labelUuid.setText((table1.getValueAt(table1.getSelectedRow(), 0)).toString());
            textShohinCode.setText(((Integer) table1.getValueAt(table1.getSelectedRow(), 1)).toString());
            textShohinName.setText(table1.getValueAt(table1.getSelectedRow(), 2).toString());
            textRemarks.setText(table1.getValueAt(table1.getSelectedRow(), 5).toString());
        }
    }

    public void textFieldClear() {
        labelUuid.setText("");
        textShohinCode.setText("");
        textShohinName.setText("");
        textRemarks.setText("");
    }

    public void tableSetting() {
        table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //table1.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        TableColumn col = table1.getColumnModel().getColumn(0);
        col.setPreferredWidth(250);
        col = table1.getColumnModel().getColumn(1);
        col.setPreferredWidth(60);
        col = table1.getColumnModel().getColumn(2);
        col.setPreferredWidth(120);
        col = table1.getColumnModel().getColumn(3);
        col.setPreferredWidth(70);
        col = table1.getColumnModel().getColumn(4);
        col.setPreferredWidth(60);
        col = table1.getColumnModel().getColumn(5);
        col.setPreferredWidth(165);
        /**col = Table1.getColumnModel().getColumn(5);
         col.setPreferredWidth(35);**/
    }

    public JFrame getFWindow() {
        return fWindow;
    }

    public JPanel getFPanel() {
        return fPanel;
    }

    public JTable getTable1() {
        return table1;
    }
    public DefaultTableModel getDTableModel() {
        return dTableModel;
    }
    public JTextArea getLabelArea() {
        return labelArea;
    }
    public JLabel getLabelUuid() {
        return labelUuid;
    }
    public JLabel getLabelFoot() {
        return labelFoot;
    }
    public JTextField getTextShohinCode() {
        return textShohinCode;
    }
    public JTextField getTextShohinName() {
        return textShohinName;
    }
    public JTextField getTextRemarks() {
        return textRemarks;
    }
    public JButton getButtonRead() {
        return buttonRead;
    }
    public JButton getButtonAdd() {
        return buttonAdd;
    }
    public JButton getButtonChange() {
        return buttonChange;
    }
    public JButton getButtonErase() {
        return buttonErase;
    }
}