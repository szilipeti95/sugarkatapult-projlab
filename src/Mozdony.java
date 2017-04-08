/**
 * Kerdezgeti az alatta allo SinElemet, hogy melyik lesz a kovetkezo SinElem.
 * Szol az elso Kocsinak, hogy mozogjon (mozog). 
 * Megvizsgálja, hogy tortent-e utkozes
 */
public class Mozdony extends Szerelveny {
	private boolean utkozott;
	
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


}
