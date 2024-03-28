package JsoupWebParser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class SwingLink extends JLabel {
    String text;
    URI uri;

    public SwingLink(String textIn,String uriIn){
        super();
        try {
            uri=new URI(uriIn);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        text = textIn;
        setText(text,true);
        setToolTipText(uri.toString());
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                open(uri);
            }
            public void mouseEntered(MouseEvent e){
                setText(text,false);
            }
            public void mouseExited(MouseEvent e){
                setText(text,true);
            }
        });
    }
    public void setText(String s,Boolean ul){
        String link = ul ? "<u>"+text+"<u>" :text;
        setText("<html><span style=\"color:#000099;\">" + link +"</span></html>");
    }
    private void open(URI uri){
        if(Desktop.isDesktopSupported()){
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(uri);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
