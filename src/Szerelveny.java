
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
	 * Kiirja az osszes, vagy a megadott attributum ertekeit
	 * @param id az azonosit, amire az infot kertek
	 * @param attr az attributum, aminek az erteket ki kell iratni. null, ha az osszes attributum kiiratando
	 */
	public void GetInfo(String id, String attr) {
		if (attr == null)
		{
			System.out.println(this.id + ":");
			System.out.println("sinElem: " + ((sinElem == null) ? "null" : sinElem.GetId()));
			System.out.println("alagutban: " + alagutban);
			System.out.println("elozoSinElem: " +((elozoSinElem == null) ? "null" : elozoSinElem.GetId()));
			System.out.println("kovKocsi: " + ((kovKocsi == null) ? "null" : kovKocsi.GetId()));
			
		}
		else
		{
			switch (attr) {
			case "sinelem":
				System.out.println(this.id + ":");
				System.out.println("sinElem: " + sinElem.GetId());
				break;
			case "elozosinelem":
				System.out.println(this.id + ":");
				System.out.println("elozoSinElem: " +((elozoSinElem == null) ? "null" : elozoSinElem.GetId()));
				break;
			case "allapot":
				System.out.println(this.id + ":");
				System.out.println("allapot: " + elozoSinElem.GetId());
				break;
			case "kovkocsi":
				System.out.println(this.id + ":");
				System.out.println("kovKocsi: " + ((kovKocsi == null) ? "null" : kovKocsi.GetId()));
				break;
		
			default:
				break;
			}
		}
		
	}
	
	/**
	 * A sinelem-et allitja be, amin a szerelveny epp tartozkodik
	 * @param s a beallitando sinElem
	 */
	public void SetSinElem(SinElem s){
		this.sinElem = s;
	}
}
