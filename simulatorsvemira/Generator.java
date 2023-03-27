package simulatorsvemira;

import java.util.ArrayList;

public class Generator extends Thread {
	private Boolean work=false;
	//private ArrayList<NebeskoTelo> nebeskaTela= new ArrayList<>();
	private Svemir mojsvemir;
	
	public Generator(Svemir s) {
		mojsvemir=s;
	}
	
	public void run() {
		int xKomete,yKomete=0,radian;
		double random;
		try {
			while(!Thread.interrupted()) {
				xKomete=(int)(Math.random()*200);
				radian=(int)(Math.random()*21+10); // (int)(Math.random()*(max-min+1)+min);
				random=(Math.random()*(Math.PI*2+1));
				synchronized (this) {
					while(!work) {
						wait();
					}
					mojsvemir.addNebeskoTelo((new Kometa(xKomete,yKomete,radian,random)));
				}
				sleep(900);
			}
		}catch(InterruptedException e) {}
	}

	public synchronized void startGenerator() {
		start();
		work = true;
		notify();
	}
	
	public synchronized void stopGenerator() {
		interrupt();
		work=false;
	}
}
