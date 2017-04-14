
/**
 * Ugyan az a felelossege, mint az ososztalyanak, a Vagonnak. 
 * Az allomasnal nem csinal semmit.
 */
public class SzenesKocsi extends Vagon {

	public SzenesKocsi() {
	}
	
	/**
	 * Ez a meteodus allitja be, hogy az elozo kocsi ures-e
	 * Mivel a szenesKocsin nincsenek utasok, ezert ez automatikusan bealltja
	 * a kovetkezo kocsinak a megfelelo attributumat
	 * @param ures true, ha az elozo kocsi ures, kulonben false
	 */
	@Override
	public void SetElozoUres(boolean ures) {
		kovKocsi.SetElozoUres(ures);
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

}
