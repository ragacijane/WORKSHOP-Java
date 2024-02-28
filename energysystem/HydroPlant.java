package energysystem;

import java.awt.Color;

public class HydroPlant extends Producer {
	private Thread thread;
	private int numOfWaterSurface;
	
	public HydroPlant(Batery b) {
		super("H", Color.BLUE, 1500, b);
		numOfWaterSurface =0;
	}
	
	@Override
	public void addWaterSurface() {
		numOfWaterSurface++;
	}
	public void postaviVodenuPovrs(int x) {
		numOfWaterSurface =x;
	}
	@Override
	protected Boolean produce() {
		if( numOfWaterSurface == 0)return false;
		else {
			batery.addEnergy(numOfWaterSurface);
			return true;
		}
	}

	@Override
	public void startUp() {
		work=true;
		thread=new Thread(this);
		thread.start();
	}

	@Override
	public void stop() {
		work=false;
		if(thread !=null)thread.interrupt();
	}
}
