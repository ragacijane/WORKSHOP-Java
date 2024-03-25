package DatabaseManipulation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

public class ConnectDialog extends JDialog implements ActionListener {

    boolean isCancelled = true;
    JLabel hostLabel = new JLabel("Host Name");
    JTextField host = new JTextField();
    JLabel portLabel = new JLabel("Port");
    JTextField port = new JTextField();
    JLabel databaseLabel = new JLabel("Database");
    JTextField database = new JTextField();
    JLabel usernameLabel = new JLabel("Username");
    JTextField username = new JTextField();
    JLabel passwordLabel = new JLabel("Password");
    JPasswordField password = new JPasswordField();
    Properties props;
    JButton ok = new JButton("OK");
    JButton cancle = new JButton("Cancle");
    public ConnectDialog(JFrame owner, String tittle, Properties p){
        super(owner,tittle,true);
        setSize(300,300);
        props = new Properties(p);

        ok.setPreferredSize(new Dimension(75,25));
        ok.addActionListener(this);

        cancle.setPreferredSize(new Dimension(75,25));
        cancle.addActionListener(this);

        JPanel dataPanel= new JPanel();
        JPanel btnPanel = new JPanel();

        dataPanel.setLayout(new GridLayout(5,2));
        dataPanel.add(hostLabel);
        dataPanel.add(host);
        dataPanel.add(portLabel);
        dataPanel.add(port);
        dataPanel.add(databaseLabel);
        dataPanel.add(database);
        dataPanel.add(usernameLabel);
        dataPanel.add(username);
        dataPanel.add(passwordLabel);
        dataPanel.add(password);

        btnPanel.add(ok);
        btnPanel.add(cancle);

        add(dataPanel,BorderLayout.NORTH);
        add(btnPanel, BorderLayout.SOUTH);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == ok)
            isCancelled = false;
        this.dispose();
    }

    public Properties getProps() {
        props.setProperty("Database",database.getText());
        props.setProperty("Host",host.getText());
        props.setProperty("Port", port.getText());
        props.setProperty("Username",username.getText());
        return props;
    }
}
