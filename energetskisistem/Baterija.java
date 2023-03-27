package energetskisistem;

public class Baterija {
	private int trenEnerg, maxEnerg;
	
	public Baterija(int max) {
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
