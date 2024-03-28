package CameraApp;

import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.highgui.VideoCapture;
import org.opencv.objdetect.CascadeClassifier;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

public class CameraPanel extends JPanel implements Runnable, ActionListener {
    BufferedImage image;
    VideoCapture capture;
    JButton screenshot;
    CascadeClassifier faceDetector;
    MatOfRect faceDetections;
    CameraPanel(){
        faceDetector = new CascadeClassifier(CameraPanel.class.getResource("haarcascade_frontalface_alt.xml").getPath().substring(1));
        faceDetections = new MatOfRect();
        screenshot = new JButton("Screenshot");
        screenshot.addActionListener(this);
        add(screenshot);
    }
    //  Take screenshots!
    @Override
    public void actionPerformed(ActionEvent e) {
        File output = new File("Screenshot1.png");
        int i = 0;
        while(output.exists()){
            i++;
            output = new File("Screenshot"+i+".png");
        }
        try {
            ImageIO.write(image,"png",output);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    @Override
    public void run() {
        System.loadLibrary("opencv_java249");
        capture = new VideoCapture(0);
        Mat webcamImage = new Mat();
        if ( capture.isOpened())
            while(true){
                capture.read(webcamImage);
                if(!webcamImage.empty()){
                    JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
                    topFrame.setSize(webcamImage.width() + 40,webcamImage.height() + 110);
                    matToBufferedImage(webcamImage);
                    //  FAce detection
                    faceDetector.detectMultiScale(webcamImage,faceDetections);
                    repaint();
                }
            }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(this.image == null) return;
        g.drawImage(image,10,40,image.getWidth(),image.getHeight(),null);
        g.setColor(Color.green);
        for(Rect rect: faceDetections.toArray()){
            g.drawRect(rect.x +10, rect.y +40,rect.width,rect.height);
        }
    }
    public void matToBufferedImage(Mat matRGB){
        int width = matRGB.width();
        int height = matRGB.height();
        int channels = matRGB.channels();
        byte[] source = new byte[width*height*channels];
        matRGB.get(0,0,source);
        image = new BufferedImage(width,height,BufferedImage.TYPE_3BYTE_BGR);
        final byte[] target = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(source,0,target,0,source.length);
    }

    public void switchCamera(int x){
        capture = new VideoCapture(x);
    }
}
