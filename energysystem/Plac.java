package energysystem;

import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Plac extends Panel{
	public ArrayList<Parcela> parcele= new ArrayList<>();
	public ArrayList<Proizvodjac> proizvodjaci= new ArrayList<>();
	private Parcela old=null;
	private Boolean selected=false;
	private int row,col;
	
	public Plac(int r,int c) {
		this.setLayout(new GridLayout(row=r,col=c,4,4));
		for(int i=0; i<c*r; i++) {
			if( (int)(Math.random()*10) > 2.5)parcele.add(new TravnataPovrs());
			else parcele.add(new VodenaPovrs());
			//parcele.get(i).addMouseListener();
			for(Parcela ptr:parcele) 
				add(ptr);
		}
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				selected=true;
				if(old == null )old=(Parcela)e.getComponent();
				else {
					old.reseted();
					old=(Parcela)e.getComponent();
				}
				old.selected();
			}
		});
	}
	
	public void dodajPotrosaca(Proizvodjac p) {
		if(old !=null && ( old instanceof TravnataPovrs || old instanceof VodenaPovrs) ) {
			removeAll();
			int index=parcele.indexOf(old);
			parcele.set(index, p);
			azurirajVodenePovrsi(p);
			proizvodjaci.add(p);
			p.pokreni();
			old=null;
			for(Parcela ptr:parcele)
				add(ptr);
			revalidate();
		}
	}
	
	public void zaustaviProizvodjace() {
		for(Proizvodjac p:proizvodjaci) {
			p.zaustavi();
		}
	}
	
	public void azurirajVodenePovrsi(Proizvodjac x) {
		int indexi[],size;
		indexi=new int[8];
		int index=parcele.indexOf(x);
		
		if(index < this.col && index%col != 0 && index%col != col-1) {
			indexi[0]=index+col-1;
			indexi[1]=index+col;
			indexi[2]=index+col+1;
			indexi[3]=index-1;
			indexi[4]=index+1;
			size=5;
		}
		else if(index < col*row-1 && index > col*row-1-col  && index%col != 0 && index%col != col-1) {
			indexi[0]=index-col+1;
			indexi[1]=index-col;
			indexi[2]=index-col-1;
			indexi[3]=index-1;
			indexi[4]=index+1;
			size=5;
		}
		else if(index%col == 0 && index%col == col-1 && index/row != 0 && index/row != row-1 ) {
			indexi[0]=index-col+1;
			indexi[1]=index-col;
			indexi[2]=index+col+1;
			indexi[3]=index+col;
			indexi[4]=index+1;
			size=5;
		}
		else if(index%col == col-1 && index%col == col-1 && index/row != 0 && index/row != row-1 ) {
			indexi[0]=index-col-1;
			indexi[1]=index-col;
			indexi[2]=index+col-1;
			indexi[3]=index+col;
			indexi[4]=index-1;
			size=5;
		}
		else if(index%col == 0 && index/row == 0) {
			indexi[0]=index+col+1;
			indexi[1]=index+col;
			indexi[2]=index+1;
			size=3;
		}
		else if(index%col == 0 && index/row == row-1 ) {
			indexi[0]=index-col+1;
			indexi[1]=index-col;
			indexi[2]=index+1;
			size=3;
		}
		else if(index%col == col-1 && index/row == 0){
			indexi[0]=index+col-1;
			indexi[1]=index+col;
			indexi[2]=index-1;
			size=3;
		}
		else if(index%col == col-1 && index/row == row-1 ) {
			indexi[0]=index-col-1;
			indexi[1]=index-col;
			indexi[2]=index-1;
			size=3;
		}
		else {
			indexi[0]=index+col-1;
			indexi[1]=index+col;
			indexi[2]=index+col+1;
			indexi[3]=index-1;
			indexi[4]=index+1;
			indexi[5]=index-col+1;
			indexi[6]=index-col;
			indexi[7]=index-col-1;
			size=8;
		}
		
		for(int i=0;i<size;i++) {
			if(parcele.get(indexi[i]) instanceof VodenaPovrs ) {
				x.dodajVodenuPovrs();
			}
		}
	}
	
}
