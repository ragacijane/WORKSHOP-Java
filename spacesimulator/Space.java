package spacesimulator;
import java.awt.*;
import java.util.ArrayList;


public class Space extends Canvas implements Runnable{
	private Thread thread;
	public ArrayList<SpaceObject> spaceObjects = new ArrayList<>();
	
	public void Svemir() {
	}
	
	public void paint(Graphics g) {
		this.setBackground(Color.black);
		for(SpaceObject iterator : spaceObjects) {
			iterator.paint(getGraphics());
			iterator.changeY(5);
		}

	}
	
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				Thread.sleep(100);
				repaint();
			}
		}catch(InterruptedException e) {return;}
	}
	
	public void addSpaceObject(SpaceObject n) {
		spaceObjects.add(n);
	}
	
	public void startSpace() {
		thread= new Thread(this);
		thread.start();
	}
	public void stopSpace() {
		if(thread !=null)thread.interrupt();
	}
}
