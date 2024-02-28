package energysystem;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class EnergySystem extends Frame  {
	private Propery plac;
	private Batery batery;
	private Button button =new Button("ADD");
	
	public EnergySystem(int r, int c, int m) {
		super("Energy System");
		
		plac=new Propery(r,c);
		
		batery =new Batery(m);
		
		setSize(500,500);
		
		setResizable(false);
		
		populateWindow();
		
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				plac.stopProducers();
				dispose();
			}
		});
	}

	private void populateWindow() {
		Panel command=new Panel();
		
		button.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				plac.addProducer(new HydroPlant(batery));
			}
		});
		
		command.add(button);
		add(command,BorderLayout.NORTH);
		add(plac,BorderLayout.CENTER);
	}
	
	public static void main(String[]args) {
		new EnergySystem(7,7,1000);
	}

}
