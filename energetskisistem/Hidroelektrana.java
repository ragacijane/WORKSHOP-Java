package energetskisistem;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

public class Hidroelektrana extends Proizvodjac{
	private Thread thread;
	private int brojVodenihPovrsi;
	
	public Hidroelektrana(Baterija b) {
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
			baterija.dodajEnerg(brojVodenihPovrsi);
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
