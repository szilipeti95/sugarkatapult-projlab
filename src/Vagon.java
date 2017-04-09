import java.util.Scanner;

/** Absztrakt osztaly amely a mozdony utan allo vagonokat testesiti meg. Feladata, hogy
 * a kovetkezo SinElemre mozogjon, illetve ertesitse a vonatban a következo vagont,
 * hogy melyik SinElemre kell lepnie
 */
public abstract class Vagon extends Szerelveny {

	public Vagon() {
		
	}
	
	/** Ezt a függvényt hívja meg az előző szerelvény
	 *  Ez felelős a kocsi mozgatasáért, és a követező kocsi mozgatásáért is
	 * @param kovSin a sinElem, amire a kovetkezo kocsinak lepnie kell 
	 */
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
	 * Ez a meteodus allitja be, hogy az elozo kocsi ures-e
	 * @param ures true, ha az elozo kocsi ures, kulonben false
	 */
	public abstract void SetElozoUres(boolean ures);

}
