package Messanger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

public class ChatClient extends JFrame implements Runnable {
    Socket socket;
    JTextArea textArea;
    JButton send, logout;
    JTextField textField;
    Thread thread;

    DataOutputStream dout;

    DataInputStream din;

    String LoginName;

    ChatClient(String login) throws IOException {
        super(login);
        LoginName = login;

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //super.windowClosing(e);
                try {
                    dout.writeUTF(LoginName+" "+"LOGOUT");
                    System.exit(-1);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        textArea = new JTextArea(18,50);
        textField = new JTextField(50);

        send = new JButton("Send");
        logout = new JButton("Logout");

        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    dout.writeUTF(LoginName+" "+"DATA "+textField.getText().toString());
                    textField.setText("");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    dout.writeUTF(LoginName+" "+"LOGOUT");
                    System.exit(-1);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        textField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    try {
                        if(textField.getText().length() > 0) {
                            dout.writeUTF(LoginName + " " + "DATA " + textField.getText().toString());
                        }
                        textField.setText("");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        socket = new Socket("localhost",5217);

        din = new DataInputStream(socket.getInputStream());
        dout = new DataOutputStream(socket.getOutputStream());

        dout.writeUTF(LoginName);
        dout.writeUTF(LoginName+" "+"LOGIN");

        thread = new Thread(this);
        thread.start();
        setup();

    }

    private void setup(){
        setSize(500,400);

        JPanel panel = new JPanel();

        panel.add(new JScrollPane(textArea));

        panel.add(textField);
        panel.add(send);
        panel.add(logout);

        add(panel);

        setVisible(true);
    }
    @Override
    public void run() {
        while(true){
            try {
                textArea.append("\n"+din.readUTF());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
