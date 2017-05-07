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
	public UtasKocsi(String id, Vagon kovKocsi, Szin szin, boolean ures) {
		super(id, kovKocsi);
		this.szin = szin;
		this.ures = ures;
	}

	/**
	 * Ez a fuggveny hivodik meg, mikor az utasoknak le kell szallniuk a kocsirol
	 * Megvizsgalja, hogy a kocsi szine megegyezik-e az allomas szinevel
	 * @param a az allamos, amelyiken el kell donteni, hogy le kell-e szallni az utasoknak
	 */
	public void leszallit(Allomas a) {
		if(szin.equals(a.getSzin()) && elozoUres && a.getUres() && !ures) {
			//A ket szin megegyezik, es az elutte lev kocsi is ures
			ures = true;
			//Nincs kovetkezo kocsi, ez a vonat kiurult
			Terepasztal.getInstance().vonatrolLeszalltak();
			if(kovKocsi!= null){
				kovKocsi.SetElozoUres(true);
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
        Terepasztal.getInstance().vonatraFelszalltak();
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
	 * @param urese true, ha az elozo kocsi ures, kulonben false
	 */
	@Override
	public void SetElozoUres(boolean urese) {
		elozoUres = urese;
	}
    
	/**
	 * kirajzoltatja magat es a kovetkezo kocsit (ha van) a parameterben kapott rajzoloval
	 * @param r A rajzolo ami kirajzolja
	 */
    @Override
    public void rajzol(Rajzolo r){
        r.rajzol(this);
        if(kovKocsi != null){//kirajzoljuk a kovetkezo kocsit, ha van
            if(kovKocsi.getSin() != null && kovKocsi.getSin().getX() >= 0 && kovKocsi.getSin().getX() < 15 &&kovKocsi.getSin().getY()>=0 && kovKocsi.getSin().getY()<15 ){
                kovKocsi.rajzol(r);
            }
        }
    }
	
}
