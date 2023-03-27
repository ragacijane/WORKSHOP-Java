package energetskisistem;

import java.awt.Color;
import java.awt.event.ActionEvent;

public abstract class Proizvodjac extends Parcela implements Runnable {
	
	protected int osnovnoVreme, ukupnoVreme;
	protected Boolean work=false;
	protected Baterija baterija=null;
	
	public Proizvodjac(String n, Color c, int vrm,Baterija b) {
		super(n, c);
		osnovnoVreme=vrm;
		ukupnoVreme=(int)(Math.random()*301) + vrm;
		baterija=b;
	}

	@Override
	public void run() {
		Boolean proizvedeno=false;
		try {
			while(!Thread.interrupted()) {
				synchronized (this) {
					while(!work){
						wait();
					}
				}
				Thread.sleep(ukupnoVreme);
				proizvedeno=proizvedi();
				if ( proizvedeno == true)
					this.setForeground(Color.RED);
				Thread.sleep(300);
				this.setForeground(Color.WHITE);
			}
			
		}catch ( InterruptedException e) {

			Thread.currentThread().interrupt();
			return;
		}
	}
	
	public abstract void pokreni();
	
	public void pauziraj() {
		work=false;
	}
	
	public abstract void zaustavi();

	protected abstract Boolean proizvedi();

	protected abstract void dodajVodenuPovrs();
}
