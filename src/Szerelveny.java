
/**
 * Szamon tartja, az elozo es az aktualis SinElemet, amin tartozkodik. 
 * Tarolja, hogy alagatban van-e. Jelre tovabb mozog
 */
public abstract class Szerelveny {
	/**
	 * Megadja melyik sinElemen talalhato a szerelveny
	 */
	protected SinElem sinElem;
	/**
	 * Alagutban van-e az adott szerlveny, vagy sem
	 */
	private boolean alagutban;
	/**
	 * Melyik sinrol erkezik
	 */
	protected SinElem elozoSinElem;
	/**
	 * Referencia a szerelvenyt koveto vagonra
	 */
	protected Vagon kovKocsi;
	/**
	 * A Szerelveny azonositoja
	 */
	protected String id;
	
	/**
	 * Konstruktor, inicializalja az adattagokat
	 * @param id a szerelveny azonositoja
	 * @param kovKocsi a kovetkezo kocsira mutato referencia
	 */
	Szerelveny(String id, Vagon kovKocsi) {
		this.id = id;
		this.kovKocsi = kovKocsi;
	}
	
	/**
	 * Visszater a szerelveny szonositojaval
	 * @return az azonosito
	 */
	String GetId() {return id;}
	
	/**
	 * Visszater a SinElemmel amin all eppen a szerelveny.
	 * @return a sinElem, amin a szerelveny all.
	 */
	public SinElem getSin() {
		return sinElem;
	}
    
	/**
	 * getter az elozo tickhez tartozo SinElemhez
	 * @return a SinElem, amin az elozo tickben allt a szerelveny
	 */
    public SinElem getElozoSin(){
        return elozoSinElem;
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
	
	/**
	 * A sinelem-et allitja be, amin a szerelveny epp tartozkodik
	 * @param s a beallitando sinElem
	 */
	public void SetSinElem(SinElem s){
		this.sinElem = s;
	}
    
	/**
	 * A sinelem-et allitja be, amin a szerelveny az elobb tartozkodott
	 * @param s a beallitando sinElem
	 */
    public void SetElozoElem(SinElem s){
		this.elozoSinElem = s;
	}
        
	/**
	 * kirajzoltatja magat a parameterben kapott rajzoloval
	 * @param r A rajzolo ami kirajzolja
	 */
    public abstract void rajzol(Rajzolo r);
        
        
}
