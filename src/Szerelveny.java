
/**
 * Szamon tartja, az elozo es az aktualis SinElemet, amin tartozkodik. 
 * Tarolja, hogy alagatban van-e. Jelre tovabb mozog
 */
public abstract class Szerelveny {
	protected SinElem sinElem;
	private boolean alagutban;
	protected SinElem elozoSinElem;
	protected Vagon kovKocsi;
	
	
	/**
	 * Visszater a SinElemmel amin all eppen a szerelveny.
	 * @return a sinElem, amin a szerelveny all.
	 */
	public SinElem getSin() {
		return sinElem;
	}
		
	/**
	 * Ezt a fuggveny hivja meg az alagtSzaj, mikor a szerelveny belep az alaguttba.
	 */
	public void alagutValt() {
		alagutban = !alagutban;
	}
	
	/**
	 * Visszater az alagutban attributum ertekevel
	 * @return true- hogy ha alagutban van, kulonben false
	 */
	public boolean alagutbanVan() {
		return alagutban;
	}
}
