/**
 * Szamon tartja, hogy meg van-e epitve. Kezeli az alagut szajra töorteno kattintast.
 */
public class AlagutSzaj extends SinElem {
	private boolean megepitve;
	private SinElem alagutSin;
	
	
	AlagutSzaj(String id) 
    {
    	super(id);
    }
	/**
	 * Reagal arra, ha a felhasznalo rakattint az alagutSzaj-ra
	 */
	public void onInput() {
		//Elkerjuk a terepasztaltol az alagutat
		Alagut a = Terepasztal.getInstance().getAlagut();
        if (megepitve == true) {
        	//A felhasznalo egy mar megepitett alagutSzajra kattintott. Le kell bontani
			a.RemoveAlagutSzaj(this);
			megepitve = false;
		}
		else if (megepitve == false){
			//A felhasznalo egy meg nem megepitett alagutSzajra kattintott. fel kell epiteni
			a.AddAlagutSzaj(this);
			megepitve = true;
		}
	}
	
	/**
	 * Lepteti a mozdonyt, illetve felrobbantja a vonatot, ha nincs megepitve.
	 * @param A leptetendo Mozdony
	 * @param 
	 */
	public void leptet(Mozdony m, SinElem s) {
		Alagut a = Terepasztal.getInstance().getAlagut();
		if (a.getAllapot().equals(AlagutAllapot.VanAlagut)){
			//Ha az alagut fel van epitve, jelezzuk a mozdony fele, hogy alagutban halad
			m.alagutValt();
			szerelvenyek++;
		}
		else {
			//Ha nincs megepitve az alagut, a vonat utkozik
			m.utkozik(); 
			//Es elveszitjuk a jatekot
			Jatek.getInstance().veszt();
		}
	}
	
	@Override
	public void raLep(UtasKocsi k) {
		//Tudatjuk a kocsikkal, hogy alagutbn haladnak
		k.alagutValt();
		szerelvenyek++;
	}
	
	@Override
	public void raLep(SzenesKocsi k) {
		//Tudatjuk a kocsikkal, hogy alagutbn haladnak
		k.alagutValt();
		szerelvenyek++;
	}
	
	@Override
	public SinElem getKovSinElem(SinElem elozo) {
		//Visszaadja a következo SinElem-et
		return alagutSin;
	}
}
