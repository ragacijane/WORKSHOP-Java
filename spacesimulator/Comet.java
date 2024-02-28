package spacesimulator;

import java.awt.Color;
import java.awt.Graphics;

public class Comet extends SpaceObject {
	protected double random;
	
	private int[][] getPolygonArrays(int cx, int cy, double r, int sides, double orr) {
        int[] x = new int[sides];
        int[] y = new int[sides];
        double thetaInc = 2*Math.PI/sides;
        double theta = (sides % 2 == 0) ? thetaInc : -Math.PI/2;
        theta+=orr;
        for(int j = 0; j < sides; j++) {
            x[j] = (int)(cx + r*Math.cos(theta));
            y[j] = (int)(cy + r*Math.sin(theta));
            theta += thetaInc;
        }
        return new int[][]{ x, y };
    }
	
	public Comet(int x, int y, double r, double ra) {
		super(x, y, Color.GRAY, r);
		random=ra;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(this.objectColor);
		int[][] xy = getPolygonArrays(centerX,centerY,R,5,random);
		g.fillPolygon(xy[0], xy[1], 5);
	}
	
}
