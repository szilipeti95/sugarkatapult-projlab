/** Absztrakt osztaly amely a mozdony utan allo vagonokat testesiti meg. Feladata, hogy
 * a kovetkezo SinElemre mozogjon, illetve ertesitse a vonatban a következo vagont,
 * hogy melyik SinElemre kell lepnie
 */
public abstract class Vagon extends Szerelveny {
	
	/**
	 * konstruktor
	 * @param id Azonosito
	 * @param kovKocsi kovetkezo vagon
	 */
	public Vagon(String id, Vagon kovKocsi) {
		super(id, kovKocsi);
	}
	
	/** Ezt a függvényt hívja meg az előző szerelvény
	 *  Ez felelős a kocsi mozgatasáért, és a követező kocsi mozgatásáért is
	 * @param kovSin a sinElem, amire a kovetkezo kocsinak lepnie kell 
	 */
	public abstract void mozog(SinElem kovSin);
	
	/**
	 * Ez a meteodus allitja be, hogy az elozo kocsi ures-e
	 * @param ures true, ha az elozo kocsi ures, kulonben false
	 */
	public abstract void SetElozoUres(boolean ures);

}
