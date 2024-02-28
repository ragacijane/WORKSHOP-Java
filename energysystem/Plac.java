package energysystem;

import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Plac extends Panel{
	public ArrayList<Parcel> parcels = new ArrayList<>();
	public ArrayList<Producer> producers = new ArrayList<>();
	private Parcel old=null;
	private Boolean selected=false;
	private int row,col;
	
	public Plac(int r,int c) {
		this.setLayout(new GridLayout(row=r,col=c,4,4));
		for(int i=0; i<c*r; i++) {
			if( (int)(Math.random()*10) > 2.5) parcels.add(new GrassSurface());
			else parcels.add(new WaterSurface());
			//parcele.get(i).addMouseListener();
			for(Parcel ptr: parcels)
				add(ptr);
		}
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				selected=true;
				if(old == null )old=(Parcel)e.getComponent();
				else {
					old.reseted();
					old=(Parcel)e.getComponent();
				}
				old.selected();
			}
		});
	}
	
	public void addProducer(Producer p) {
		if(old !=null && ( old instanceof GrassSurface || old instanceof WaterSurface) ) {
			removeAll();
			int index= parcels.indexOf(old);
			parcels.set(index, p);
			updateWaterSurfaces(p);
			producers.add(p);
			p.startUp();
			old=null;
			for(Parcel ptr: parcels)
				add(ptr);
			revalidate();
		}
	}
	
	public void stopProducers() {
		for(Producer p: producers) {
			p.stop();
		}
	}
	
	public void updateWaterSurfaces(Producer x) {
		int indexes[],size;
		indexes=new int[8];
		int index= parcels.indexOf(x);
		
		if(index < this.col && index%col != 0 && index%col != col-1) {
			indexes[0]=index+col-1;
			indexes[1]=index+col;
			indexes[2]=index+col+1;
			indexes[3]=index-1;
			indexes[4]=index+1;
			size=5;
		}
		else if(index < col*row-1 && index > col*row-1-col  && index%col != 0 && index%col != col-1) {
			indexes[0]=index-col+1;
			indexes[1]=index-col;
			indexes[2]=index-col-1;
			indexes[3]=index-1;
			indexes[4]=index+1;
			size=5;
		}
		else if(index%col == 0 && index%col == col-1 && index/row != 0 && index/row != row-1 ) {
			indexes[0]=index-col+1;
			indexes[1]=index-col;
			indexes[2]=index+col+1;
			indexes[3]=index+col;
			indexes[4]=index+1;
			size=5;
		}
		else if(index%col == col-1 && index%col == col-1 && index/row != 0 && index/row != row-1 ) {
			indexes[0]=index-col-1;
			indexes[1]=index-col;
			indexes[2]=index+col-1;
			indexes[3]=index+col;
			indexes[4]=index-1;
			size=5;
		}
		else if(index%col == 0 && index/row == 0) {
			indexes[0]=index+col+1;
			indexes[1]=index+col;
			indexes[2]=index+1;
			size=3;
		}
		else if(index%col == 0 && index/row == row-1 ) {
			indexes[0]=index-col+1;
			indexes[1]=index-col;
			indexes[2]=index+1;
			size=3;
		}
		else if(index%col == col-1 && index/row == 0){
			indexes[0]=index+col-1;
			indexes[1]=index+col;
			indexes[2]=index-1;
			size=3;
		}
		else if(index%col == col-1 && index/row == row-1 ) {
			indexes[0]=index-col-1;
			indexes[1]=index-col;
			indexes[2]=index-1;
			size=3;
		}
		else {
			indexes[0]=index+col-1;
			indexes[1]=index+col;
			indexes[2]=index+col+1;
			indexes[3]=index-1;
			indexes[4]=index+1;
			indexes[5]=index-col+1;
			indexes[6]=index-col;
			indexes[7]=index-col-1;
			size=8;
		}
		
		for(int i=0;i<size;i++) {
			if(parcels.get(indexes[i]) instanceof WaterSurface) {
				x.addWaterSurface();
			}
		}
	}
	
}
