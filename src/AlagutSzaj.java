/**
 * Szamon tartja, hogy meg van-e epitve. Kezeli az alagut szajra töorteno kattintast.
 */
public class AlagutSzaj extends SinElem {
	/**
	 * alagutSzaj megepultsegenek allapota
	 */
	private boolean megepitve;
	/**
	 * hozza kapcsolodo alagutsin
	 */
	private SinElem alagutSin;
	
	/**
	 * konstruktor
	 * @param id alagutSzaj azonositoja
	 */
	AlagutSzaj(String id) 
    {
    	super(id);
    }
	
	/**
	 * alagutsin vegenek bekotese
	 * @param s amit hozza akarunk kotni
	 * @param c melyik felehez akarjuk kotni (<code>a</code> sinA / <code>b</code> alagutSin)
	 */
	@Override
    public void setSinElem(SinElem s, char c){
        if (c == 'a')
        	sinA = s;
        else if (c == 'b')
        	alagutSin = s;
    }
	
	/**
	 * Reagal arra, ha a felhasznalo rakattint az alagutSzaj-ra
	 */
	public void onInput() {
		//Elkerjuk a terepasztaltol az alagutat
		Alagut a = Terepasztal.getInstance().getAlagut();
        if (megepitve == true && !a.isFoglalt()) {
        	//A felhasznalo egy mar megepitett alagutSzajra kattintott. Le kell bontani
			a.RemoveAlagutSzaj(this);
			megepitve = false;
			System.out.println(id+ " alagutszaj lerombolva");
		}
		else if (megepitve == false && !a.getAllapot().equals(AlagutAllapot.VanAlagut)){
			//A felhasznalo egy meg nem megepitett alagutSzajra kattintott. fel kell epiteni
			a.AddAlagutSzaj(this);
			megepitve = true;
			System.out.println(id+ " alagutszaj megepitve");
		}
		else if(a.getAllapot().equals(AlagutAllapot.VanAlagut)){
			System.out.println("nem epitheto meg az alagutszaj");
		}
	}
	
	/**
	 * Lepteti a mozdonyt, illetve felrobbantja a vonatot, ha nincs megepitve.
	 * @param m A leptetendo Mozdony
	 * @param s Elozo sinelem
	 */
	@Override
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
		if(sinA.equals(elozo)){
			return alagutSin;
		}
		else{
			return sinA;
		}
	}
	
	
	@Override
	public void GetInfo(String attr) {
		super.GetInfo(attr);
		if (attr == null)
		{
			System.out.println("megepitve: " + megepitve);
			System.out.println("alagutSin: " + alagutSin.id);
		}
		else
		{
			switch (attr) {
			case "alagutsin":
				System.out.println(id + ":");
				System.out.println("alagutSin: " + alagutSin.id);
				break;
			case "megepitve":
				System.out.println(id + ":");
				System.out.println("megepitve: " + megepitve);
				break;
		
			default:
				break;
			}
		}
		
	}
}
