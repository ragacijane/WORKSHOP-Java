package TextEditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class FileBrowser extends JPanel implements ActionListener {
    JLabel label = new JLabel("File list:");
    JButton newFile = new JButton("New File");
    JButton open = new JButton("Open");
    ButtonGroup buttonGroup;
    File directory;
    JTextField newFileTf = new JTextField(10);
    public FileBrowser(String dir){
        directory = new File(dir);
        directory.mkdir();
        JPanel fileList = new JPanel(new GridLayout(directory.listFiles().length +3, 1));
        fileList.add(label);
        buttonGroup = new ButtonGroup();
        for ( File file : directory.listFiles()){
            JRadioButton radio = new JRadioButton(file.getName());
            radio.setActionCommand(file.getName());
            buttonGroup.add(radio);
            fileList.add(radio);
        }
        JPanel newPanel = new JPanel();
        newPanel.add(newFileTf);
        newPanel.add(newFile);
        fileList.add(open);
        fileList.add(newPanel);
        add(fileList);
        newFile.addActionListener(this);
        open.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Login login = (Login) getParent();
        if(e.getSource() == open){
            login.add(new Editor(directory.getName()+"\\"+buttonGroup.getSelection().getActionCommand()),"editor");
            login.cardLayout.show(login,"editor");
        }
        if(e.getSource() == newFile){
            String file = directory.getName()+"\\"+newFileTf.getText()+".txt";
            if(newFileTf.getText().length() > 0 && !(new File(file).exists())){
                login.add(new Editor(file),"editor");
                login.cardLayout.show(login,"editor");
            }
        }
    }
}
