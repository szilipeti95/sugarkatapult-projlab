/**
 * Ugyanaz a felelossege, mint szoulojenek, a Vagonnak. Ezenivul tarolja a szinét, ami
 * alapjan az utasok leszallnak a kocsibol, illetve tarolja,
 * hogy vannak-e utasok a kocsin
 */
public class UtasKocsi extends Vagon {
	private Szin szin;
	private boolean ures;
	private boolean elozoUres;

	/**
	 * Ez a fuggveny hivodik meg, mikor az utasoknak le kell szallniuk a kocsirol
	 * Megvizsgalja, hogy a kocsi szine megegyezik-e az allomas szinevel
	 * @param a az allamos, amelyiken el kell donteni, hogy le kell-e szallni az utasoknak
	 */
	public void leszallit(Allomas a) {
		if(szin.equals(a.getSzin()) && elozoUres) {
			//A ket szin megegyezik, es az elutte lev kocsi is ures
			ures = true;
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
				
		//Elmentjük, hogy már a következő sinen állunk
		sinElem = kovSin;
	}
	
	/**
	 * Az allomasnal uj utasok szallnak a kocsira ennek a fugvenynek a meghivasaval
	 */
	public void felszall() {
		ures = false;
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
	
}
