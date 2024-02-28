package spacesimulator;
import java.awt.*;
import java.awt.event.*;

public class Simulator extends Frame implements ActionListener{
	private Space svemir=new Space();
	private Generator generator=new Generator(svemir);
	private Panel panelKomande=new Panel();

	public void populateWindow() {
		Button dugme=new Button("Pokreni!");
		add(svemir, BorderLayout.CENTER);	
		dugme.addActionListener(this);
		panelKomande.add(dugme);
		add(panelKomande, BorderLayout.SOUTH);
	}
	
	public Simulator() {
		super("");
		
		setSize(200,400);
		
		setResizable(false); 
		
		populateWindow();
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				svemir.stopSpace();
				generator.stopGenerator();
				dispose();
			}
		});
		
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		svemir.startSpace();
		generator.startGenerator();
		((Component) e.getSource()).setEnabled(false);
	}
	
	public static void main(String[]args) {
		new Simulator();
	}
}
