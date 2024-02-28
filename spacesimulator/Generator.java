package spacesimulator;

public class Generator extends Thread {
	private Boolean work=false;
	//private ArrayList<NebeskoTelo> nebeskaTela= new ArrayList<>();
	private Space mySpace;
	
	public Generator(Space s) {
		mySpace =s;
	}
	
	public void run() {
		int xComet,yComet=0,radian;
		double random;
		try {
			while(!Thread.interrupted()) {
				xComet=(int)(Math.random()*200);
				radian=(int)(Math.random()*21+10); // (int)(Math.random()*(max-min+1)+min);
				random=(Math.random()*(Math.PI*2+1));
				synchronized (this) {
					while(!work) {
						wait();
					}
					mySpace.addSpaceObject((new Comet(xComet,yComet,radian,random)));
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
