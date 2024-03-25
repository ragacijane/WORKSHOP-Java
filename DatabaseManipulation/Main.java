package DatabaseManipulation;

import javax.swing.*;
import java.util.Properties;

public class Main {

    public static void main(String[] args){
        JFrame frame = new JFrame("Database");
        Properties props = new Properties();
        ConnectDialog dialog = new ConnectDialog(frame,"Database Connector", props);
        dialog.setVisible(true);
        if(dialog.isCancelled)
            System.exit(-1);
        Connector connector = new Connector(dialog.getProps(), new String(dialog.password.getPassword()));
        if(!connector.open())
            System.exit(-1);
        Database dataPanel = new Database(connector);



        frame.setSize(800,600);
        frame.add(dataPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
