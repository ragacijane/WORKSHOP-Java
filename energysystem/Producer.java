package energysystem;

import java.awt.Color;

public abstract class Producer extends Parcel implements Runnable {
	
	protected int basicTime, totalTime;
	protected Boolean work=false;
	protected Batery batery =null;
	
	public Producer(String n, Color c, int vrm, Batery b) {
		super(n, c);
		basicTime =vrm;
		totalTime =(int)(Math.random()*301) + vrm;
		batery =b;
	}

	@Override
	public void run() {
		Boolean produced=false;
		try {
			while(!Thread.interrupted()) {
				synchronized (this) {
					while(!work){
						wait();
					}
				}
				Thread.sleep(totalTime);
				produced= produce();
				if ( produced == true)
					this.setForeground(Color.RED);
				Thread.sleep(300);
				this.setForeground(Color.WHITE);
			}
			
		}catch ( InterruptedException e) {

			Thread.currentThread().interrupt();
			return;
		}
	}
	
	public abstract void startUp();
	
	public void pause() {
		work=false;
	}
	
	public abstract void stop();

	protected abstract Boolean produce();

	protected abstract void addWaterSurface();
}
