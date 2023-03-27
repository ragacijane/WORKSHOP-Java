package simulatorsvemira;
import java.awt.*;
import java.util.ArrayList;


public class Svemir extends Canvas implements Runnable{
	private Thread thread;
	public ArrayList<NebeskoTelo> nebeskaTela = new ArrayList<>();
	
	public void Svemir() {
	}
	
	public void paint(Graphics g) {
		this.setBackground(Color.black);
		for(NebeskoTelo iterator : nebeskaTela) {
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
	
	public void addNebeskoTelo(NebeskoTelo n) {
		nebeskaTela.add(n);
	}
	
	public void pokreniSvemir() {
		thread= new Thread(this);
		thread.start();
	}
	public void zaustaviSvemir() {
		if(thread !=null)thread.interrupt();
	}
}
