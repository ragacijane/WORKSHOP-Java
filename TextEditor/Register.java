package TextEditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.StringTokenizer;

public class Register extends JPanel implements ActionListener {
    JLabel userL = new JLabel("Username: ");
    JTextField userField = new JTextField();
    JLabel passwordL = new JLabel("Password: ");
    JPasswordField passwordField = new JPasswordField();
    JLabel confirmPasswordL = new JLabel("Confirm Password: ");
    JPasswordField confirmPasswordField = new JPasswordField();
    JButton registerBtn = new JButton("Register");
    JButton backBtn = new JButton("Back");

    public Register(){
        //  Creating panel
        JPanel registerPanel = new JPanel();
        registerPanel.setLayout(new GridLayout(4,2));
        registerPanel.add(userL);
        registerPanel.add(userField);
        registerPanel.add(passwordL);
        registerPanel.add(passwordField);
        registerPanel.add(confirmPasswordL);
        registerPanel.add(confirmPasswordField);
        registerPanel.add(registerBtn);
        registerPanel.add(backBtn);
        registerBtn.addActionListener(this);
        backBtn.addActionListener(this);
        add(registerPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backBtn){
            Login login = (Login) getParent();
            login.cardLayout.show(login,"login");
        }
        if(e.getSource() == registerBtn &&
                passwordField.getPassword().length > 0 &&
                userField.getText().length() > 0){
            String pass = new String(passwordField.getPassword());
            String confirm = new String(confirmPasswordField.getPassword());
            if(pass.equals(confirm)){
                try {
                    //  Checking if user exists
                    BufferedReader input = new BufferedReader(new FileReader("TextEditor/passwords.txt"));
                    String line = input.readLine();
                    while(line != null){
                        StringTokenizer st = new StringTokenizer(line);
                        if(userField.getText().equals(st.nextToken())){
                            System.out.println("User already exists!");
                            return;
                        }
                        line = input.readLine();
                    }
                    input.close();
                    //  This part of code encodes password
                    MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
                    messageDigest.update(pass.getBytes());
                    byte byteData[] = messageDigest.digest();
                    StringBuffer stringBuffer = new StringBuffer(); //  String buffer for easier merging bits
                    for(int i=0; i<byteData.length;i++)
                        stringBuffer.append(Integer.toString((byteData[i] & 0xFF)+0x100,16).substring(1));
                    BufferedWriter output = new BufferedWriter(new FileWriter("TextEditor/passwords.txt",true));
                    output.write(userField.getText()+" "+stringBuffer.toString()+"\n");
                    output.close();
                    //  Changing card to shop up after succesful register
                    Login login = (Login) getParent();
                    login.cardLayout.show(login,"login");

                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (NoSuchAlgorithmException ex) {
                    throw new RuntimeException(ex);
                }
            }

        }
    }
}
