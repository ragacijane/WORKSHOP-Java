package DatabaseManipulation;

import sun.management.snmp.jvmmib.JvmRTBootClassPathTableMeta;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Database extends JPanel {
    static JTextArea sqlStatement = new JTextArea();
    static Connector connector;
    JLabel prompt = new JLabel("Please enter your SQL statement below:");
    JButton exe = new JButton("Execute");
    JButton reset = new JButton("Reset");
    static JTable table = new JTable();
    static DefaultTableModel model = (DefaultTableModel) table.getModel();

    public Database(Connector conn){
        connector=conn;
        add(prompt);
        JScrollPane sqlPane = new JScrollPane(sqlStatement);
        sqlPane.setPreferredSize(new Dimension(750, 100));
        add(sqlPane);
        exe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                execute();
            }
        });
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setColumnCount(0);
                model.setRowCount(0);
            }
        });
        add(exe);
        add(reset);
        JScrollPane tablePane = new JScrollPane(table);
        tablePane.setPreferredSize(new Dimension(750,100));
        add(tablePane);
    }

    private static void execute(){
        model.setColumnCount(0);
        model.setRowCount(0);
        String query = sqlStatement.getText();
        try {
            if(query.length()>=6 && query.substring(0,6).equalsIgnoreCase("SELECT")){
                ResultSet resultSet = connector.executeQuery(query);
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                    model.addColumn(resultSetMetaData.getColumnName(i));
                }
                while (resultSet.next()) {
                    String[] data = new String[resultSetMetaData.getColumnCount()];
                    for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                        data[i - 1] = resultSet.getString(i);
                    }
                    model.addRow(data);
                }
            }
            else
                connector.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
