package energysystem;

import java.awt.Color;

public class HydroPlant extends Producer {
	private Thread thread;
	private int brojVodenihPovrsi;
	
	public HydroPlant(Batery b) {
		super("H", Color.BLUE, 1500, b);
		brojVodenihPovrsi=0;
	}
	
	@Override
	public void dodajVodenuPovrs() {
		brojVodenihPovrsi++;
	}
	public void postaviVodenuPovrs(int x) {
		brojVodenihPovrsi=x;
	}
	@Override
	protected Boolean proizvedi() {
		if( brojVodenihPovrsi == 0)return false;
		else {
			batery.dodajEnerg(brojVodenihPovrsi);
			return true;
		}
	}

	@Override
	public void pokreni() {
		work=true;
		thread=new Thread(this);
		thread.start();
	}

	@Override
	public void zaustavi() {
		work=false;
		if(thread !=null)thread.interrupt();
	}
}
