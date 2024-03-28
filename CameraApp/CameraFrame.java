package CameraApp;

import org.opencv.highgui.VideoCapture;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CameraFrame extends JFrame implements ActionListener {
    CameraPanel cameraPanel;
    CameraFrame(){
        System.loadLibrary("opencv_java249");
        VideoCapture list = new VideoCapture(0);
        cameraPanel = new CameraPanel();
        Thread thread = new Thread(cameraPanel);
        JMenu camera = new JMenu("Camera");
        JMenuBar bar = new JMenuBar();
        bar.add(camera);
        int i = 1;
        while(list.isOpened()){
            JMenuItem cam = new JMenuItem("Camera "+i);
            System.out.println(list);
            cam.addActionListener(this);
            camera.add(cam);
            list.release();
            list = new VideoCapture(i);
            i++;
        }
        thread.start();
        add(cameraPanel);
        setJMenuBar(bar);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,400);
        setVisible(true);
    }
    public static void main(String[] args){
        CameraFrame cameraFrame = new CameraFrame();
    }

    // Changing between multiple cameras
    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem source = (JMenuItem) e.getSource();
        int num = Integer.parseInt(source.getText().substring(7))-1;
        cameraPanel.switchCamera(num);
    }
}
