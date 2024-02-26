package energysystem;

public class Batery {
	private int trenEnerg, maxEnerg;
	
	public Batery(int max) {
		maxEnerg=max;
		trenEnerg=max;
	}
	
	public synchronized void dodajEnerg(int x) {
		trenEnerg=(trenEnerg+x >= maxEnerg) ? maxEnerg : trenEnerg + x;
	}
	
	public Boolean isFull() {
		return (trenEnerg == maxEnerg) ? true : false;
	}
	
	public void isprazni() {
		trenEnerg=0;
	}
}
