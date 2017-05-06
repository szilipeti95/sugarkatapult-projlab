/**
 * Ugyanaz a felelossege, mint szoulojenek, a Vagonnak. Ezenivul tarolja a szinét, ami
 * alapjan az utasok leszallnak a kocsibol, illetve tarolja,
 * hogy vannak-e utasok a kocsin
 */
public class UtasKocsi extends Vagon {
	/**
	 * Az UtasKocsi szine. Ez alapjan szallnak le rola az utasok
	 */
	private Szin szin;
	/**
	 * Megadja, hogy a kocsi ures-e
	 */
	private boolean ures;
	/**
	 * Megadja, hogy az elozo kocsi ures-e
	 */
	private boolean elozoUres;
	

	/**
	 * Konstrukor, inicializalja az adattagokat
	 * @param id a szerelveny azonositoja
	 * @param kovKocsi a kovetkezo kocsira mutato referencia
	 * @param szin a beallitando szin
	 */
	public UtasKocsi(String id, Vagon kovKocsi, Szin szin) {
		super(id, kovKocsi);
		this.szin = szin;
	}

	/**
	 * Ez a fuggveny hivodik meg, mikor az utasoknak le kell szallniuk a kocsirol
	 * Megvizsgalja, hogy a kocsi szine megegyezik-e az allomas szinevel
	 * @param a az allamos, amelyiken el kell donteni, hogy le kell-e szallni az utasoknak
	 */
	public void leszallit(Allomas a) {
		if(szin.equals(a.getSzin()) && elozoUres) {
			//A ket szin megegyezik, es az elutte lev kocsi is ures
			ures = true;
			if(kovKocsi!= null)
			kovKocsi.SetElozoUres(true);
			
			if (kovKocsi == null) {
				//Nincs kovetkezo kocsi, ez a vonat kiurult
				Terepasztal.getInstance().vonatKiurult();
			}
		}
	}
	
	/** Ezt a függvényt hívja meg az előző szerelvény
	 *  Ez felelős a kocsi mozgatasáért, és a követező kocsi mozgatásáért is
	 * @param kovSin a sinElem, amire a kovetkezo kocsinak lepnie kell 
	 */
	@Override
	public void mozog(SinElem kovSin) {
		//Ellepunk az aktuális sínről
		sinElem.elLep();
		
		//rálépünk a paraméterként kapott "következő" sínre
		kovSin.raLep(this);
		
		if (kovKocsi != null) {
			//Ha van a kocsihoz kapcsolódva még kocsi, akkor azt is mozgatjuk
			kovKocsi.mozog(sinElem);
		}
		elozoSinElem = sinElem;
		//Elmentjük, hogy már a következő sinen állunk
		sinElem = kovSin;
	}
	
	/**
	 * Az allomasnal uj utasok szallnak a kocsira ennek a fugvenynek a meghivasaval
	 */
	public void felszall() {
		ures = false;
		if(kovKocsi!=null)
			kovKocsi.SetElozoUres(false);
	}
	
	/**
	 * Visszaadja a kocsi szinet
	 * @return a kocsi szine
	 */
	public Szin GetSzin() {
		return szin;
	}
	
	/**
	 * Visszaadj,a hogy ures-e a kocsi
	 * @return true, ha ures, kulonben false
	 */
	public boolean GetUres() {
		return ures;
	}
	
	/**
	 * Ez a meteodus allitja be, hogy az elozo kocsi ures-e
	 * @param ures true, ha az elozo kocsi ures, kulonben false
	 */
	@Override
	public void SetElozoUres(boolean ures) {
		elozoUres = ures;
	}
	
	/**
	 * Kiirja az osszes, vagy a megadott attributum ertekeit
	 * @param id az azonosit, amire az infot kertek
	 * @param attr az attributum, aminek az erteket ki kell iratni. null, ha az osszes attributum kiiratando
	 */
	@Override
	public void GetInfo(String id, String attr) {
		super.GetInfo(id, attr);
		if(id.equals(this.id.split("-")[1])) {
			if (attr == null)
			{
				System.out.println("szin: " + szin);
				System.out.println("ures: " + ures);
				System.out.println("elozoUres: " + elozoUres);
			}
			else
			{
				switch (attr) {
				case "elozoUres":
					System.out.println(this.id + ":");
					System.out.println("elozoUres: " + elozoUres);
					break;
				case "szin":
					System.out.println(this.id + ":");
					System.out.println("szin: " + szin);
					break;
				case "ures":
					System.out.println(this.id + ":");
					System.out.println("ures: " + ures);
					break;
			
				default:
					break;
				}
			}
		}
		
	}
        
        @Override
        public void rajzol(Rajzolo r){
            r.rajzol(this);
            if(kovKocsi != null){
                if(kovKocsi.getSin() != null && kovKocsi.getSin().getX() >= 0 && kovKocsi.getSin().getX() < 15){
                    kovKocsi.rajzol(r);
                }
            }
        }
	
}
