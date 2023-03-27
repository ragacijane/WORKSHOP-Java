package energetskisistem;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class EnergetskiSistem extends Frame  {
	private Plac plac;
	private Baterija baterija;
	private Button dugme=new Button("Dodaj.");
	
	public EnergetskiSistem(int r,int c,int m) {
		super("Energetski sistem");
		
		plac=new Plac(r,c);
		
		baterija=new Baterija(m);
		
		setSize(500,500);
		
		setResizable(false);
		
		populateWindow();
		
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				plac.zaustaviProizvodjace();
				dispose();
			}
		});
	}

	private void populateWindow() {
		Panel komanda=new Panel();
		
		dugme.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				plac.dodajPotrosaca(new Hidroelektrana(baterija));
			}
		});
		
		komanda.add(dugme);
		add(komanda,BorderLayout.NORTH);
		add(plac,BorderLayout.CENTER);
	}
	
	public static void main(String[]args) {
		new EnergetskiSistem(7,7,1000);
	}

}
