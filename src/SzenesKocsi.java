/**
 * Ugyan az a felelossege, mint az ososztalyanak, a Vagonnak. 
 * Az allomasnal nem csinal semmit.
 */
public class SzenesKocsi extends Vagon {

	/**
	 * Konstrukor, inicializalja az adattagokat
	 * @param id a szerelveny azonositoja
	 * @param kovKocsi a kovetkezo kocsira mutato referencia
	 */
	public SzenesKocsi(String id, Vagon kovKocsi) {
		super(id, kovKocsi);
	}
	
	/**
	 * Ez a meteodus allitja be, hogy az elozo kocsi ures-e
	 * Mivel a szenesKocsin nincsenek utasok, ezert ez automatikusan bealltja
	 * a kovetkezo kocsinak a megfelelo attributumat
	 * @param ures true, ha az elozo kocsi ures, kulonben false
	 */
	@Override
	public void SetElozoUres(boolean ures) {
		if(kovKocsi!= null)
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
				
                elozoSinElem = sinElem;
		//Elmentjük, hogy már a következő sinen állunk
		sinElem = kovSin;
	}
	
	/**
	 * kirajzoltatja magat a parameterben kapott rajzoloval
	 * @param r A rajzolo ami kirajzolja
	 */
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
