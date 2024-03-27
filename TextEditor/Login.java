package TextEditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.StringTokenizer;

public class Login extends JPanel implements ActionListener {
    JLabel userL = new JLabel("Username: ");
    JTextField userField = new JTextField();
    JLabel passwordL = new JLabel("Password");
    JPasswordField passwordField = new JPasswordField();
    JPanel loginPanel = new JPanel(new GridLayout(3, 2));
    JPanel panel = new JPanel();
    JButton loginBtn = new JButton("Login");
    JButton registerBtn = new JButton("Register");
    CardLayout cardLayout;

    Login() {
        setLayout(new CardLayout());
        loginPanel.add(userL);
        loginPanel.add(userField);
        loginPanel.add(passwordL);
        loginPanel.add(passwordField);
        loginPanel.add(loginBtn);
        loginPanel.add(registerBtn);
        panel.add(loginPanel);
        add(panel, "login");
        cardLayout = (CardLayout) getLayout();
        loginBtn.addActionListener(this);
        registerBtn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginBtn) {
            try {
                //Geting password for a certain user and store it in pass
                BufferedReader input = new BufferedReader(new FileReader("TextEditor/passwords.txt"));
                String pass = null;
                String line = input.readLine();
                while (line != null) {
                    StringTokenizer st = new StringTokenizer(line);
                    if (userField.getText().equals(st.nextToken())) {
                        pass = st.nextToken();
                    }
                    line = input.readLine();
                }
                input.close();
                //To check password it is easier to code it on same method than decode already coded value
                //And after that compare it with one in file
                MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
                messageDigest.update(new String(passwordField.getPassword()).getBytes());
                byte byteData[] = messageDigest.digest();
                StringBuffer stringBuffer = new StringBuffer();
                for (int i = 0; i < byteData.length; i++)
                    stringBuffer.append(Integer.toString((byteData[i] & 0xFF) + 0x100, 16).substring(1));

                if (pass.equals(stringBuffer.toString())) {
                    add(new FileBrowser(userField.getText()),"filebrowser");
                    cardLayout.show(this,"filebrowser");
                }

            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (NoSuchAlgorithmException ex) {
                throw new RuntimeException(ex);
            }
        }
        if (e.getSource() == registerBtn) {
            add(new Register(), "register");
            cardLayout.show(this, "register");
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Text Editor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        Login login = new Login();
        frame.add(login);
        frame.setVisible(true);
    }
}
