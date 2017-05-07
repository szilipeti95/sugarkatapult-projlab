/**
 * Kerdezgeti az alatta allo SinElemet, hogy melyik lesz a kovetkezo SinElem.
 * Szol az elso Kocsinak, hogy mozogjon (mozog). 
 * Megvizsgálja, hogy tortent-e utkozes
 */
public class Mozdony extends Szerelveny {
	/**
	 * True, ha a mozdony utkozott, kulonben false
	 */
	private boolean utkozott;
	
	/**
	 * Konstrukor, inicializalja az adattagokat
	 * @param id a szerelveny azonositoja
	 * @param kovKocsi a kovetkezo kocsira mutato referencia
	 * @param s a beallitando sinElem amin a Mozdony eppen tartozkodik
	 */
	Mozdony(String id, Vagon kovKocsi, SinElem s)
	{
		super(id, kovKocsi);
		if(kovKocsi!=null)
			kovKocsi.SetElozoUres(true);
		this.sinElem = s;
	}
	/**
	 * Ez a metodus hívidik meg, ha a mozdony nekimegy egy másik kocsinak.
	 * Atallitja az utkozott valtozo erteket true-ra
	 */
	public void utkozik() {
		utkozott = true;
	}
	
        
	/**
	 * Ezt a metodost hivja meg a terepasztal a vonatok mozgatasahoz.
	 * Majd ez felelos a további kocsik mozgatásáért 
	 */
	public void mozog() {		
		//Lekerdezzuk a SinElem-tol, amint allunk, hogy melyik a kovetkezo, amerre menni kell
		SinElem kovSin = sinElem.getKovSinElem(elozoSinElem);

		//ellepunk a sinElemrol, amin allunk
		sinElem.elLep();
		//és ralrpunk a kovetkezore
		kovSin.leptet(this, sinElem);
		
		if (kovKocsi != null) {
			kovKocsi.mozog(sinElem);
		}
		elozoSinElem = sinElem;
		//Elmetjuk, hogy mar a kovetkezo sinen allunk
		sinElem = kovSin;
		
	}
	
	/**
	 * Ezt a fuggvenyt hivja meg a terepasztal, hogy ellenorizze az utkozeseket
	 */
	public void utkozesVizsgal() {
		//Lekérdezzük, hogy a SinElem-en, amin a mozdony van, van-e másik szerelvény.
		boolean utkozes = sinElem.getUtkozes();
		if (utkozes)
		{
			utkozik();
			Jatek.getInstance().veszt();               
		}
	}
	
	/**
	 * getter, hogy utkozott-e a mozdony
	 * @return igaz, ha utkozott
	 */
    public boolean getUtkozott(){
        return utkozott;
    }
	
	/**
	 * kirajzoltatja magat es a maga utan huzott kocsit (ha van) a parameterben kapott rajzoloval
	 * @param r A rajzolo ami kirajzolja
	 */
    @Override
    public void rajzol(Rajzolo r){
        r.rajzol(this);
        if(kovKocsi != null)
            if(kovKocsi.getSin() != null && kovKocsi.getSin().getX() >= 0 && kovKocsi.getSin().getX() < 15)
                kovKocsi.rajzol(r);
    }
}
