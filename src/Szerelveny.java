
/**
 * Szamon tartja, az elozo es az aktualis SinElemet, amin tartozkodik. 
 * Tarolja, hogy alagatban van-e. Jelre tovabb mozog
 */
public abstract class Szerelveny {
	protected SinElem sinElem;
	private boolean alagutban;
	protected SinElem elozoSinElem;
	protected Vagon kovKocsi;
	protected String id;
	
	
	Szerelveny(String id, Vagon kovKocsi) {
		this.id = id;
		this.kovKocsi = kovKocsi;
	}
	
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
	
	public void GetInfo(String id, String attr) {
		if (attr == null)
		{
			System.out.println(this.id + ":");
			System.out.println("sinElem: " + sinElem.GetId());
			System.out.println("alagutban: " + alagutban);
			//BeSin miatt
			if (elozoSinElem != null)
				System.out.println("elozoSinElem: " + elozoSinElem.GetId());
			//Ha nincs kovKocsi nem irjuk ki
			if (kovKocsi != null)
				System.out.println("kovKocsi: " + kovKocsi.GetId());
			
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
				System.out.println("elozoSinElem: " + alagutban);
				break;
			case "allapot":
				System.out.println(this.id + ":");
				System.out.println("allapot: " + elozoSinElem.GetId());
				break;
			case "kovkocsi":
				System.out.println(this.id + ":");
				System.out.println("kovKocsi: " + kovKocsi.GetId());
				break;
		
			default:
				break;
			}
		}
		
	}
	
	public void SetSinElem(SinElem s){
		this.sinElem = s;
	}
}
