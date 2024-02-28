package energysystem;

public class Batery {
	private int tempEnergy, maxEnergy;
	
	public Batery(int max) {
		maxEnergy =max;
		tempEnergy =max;
	}
	
	public synchronized void addEnergy(int x) {
		tempEnergy =(tempEnergy +x >= maxEnergy) ? maxEnergy : tempEnergy + x;
	}
	
	public Boolean isFull() {
		return (tempEnergy == maxEnergy) ? true : false;
	}
	
	public void doEmpty() {
		tempEnergy =0;
	}
}
