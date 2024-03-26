package TextEditor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.io.*;

public class Editor extends JPanel implements ActionListener {
    File file;
    JButton save = new JButton("Save");
    JButton saveClose = new JButton("Save and Close");
    JTextArea text= new JTextArea(20,40);

    public Editor(String s) {
        file = new File(s);
        save.addActionListener(this);
        saveClose.addActionListener(this);
        if(file.exists()){
            try {
                BufferedReader input = new BufferedReader(new FileReader(file));
                String line = input.readLine();
                while(line!=null){
                    text.append(line+'\n');
                    line = input.readLine();
                }
                input.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        add(save);
        add(saveClose);
        add(text);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        FileWriter output;
        try {
            output = new FileWriter(file);
            output.write(text.getText());
            output.close();
            if(e.getSource() == saveClose){
                Login login = (Login) getParent();
                login.cardLayout.show(login,"filebrowser");
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
