package simulatorsvemira;

import java.awt.Color;
import java.awt.Graphics;

public class NebeskoTelo extends Objekat{
	protected double R;
	
	public NebeskoTelo(int x, int y, Color c, double r) {
		super(x, y, c);
		setR(r);
	}
	
	public void setR(double r) {
		this.R=r;
	}
	
	public double getR() {
		return this.R;
	}

	@Override
	public void paint(Graphics g) {}
	
	
	
}
