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
	 * Kiirja az osszes, vagy a megadott attributum ertekeit
	 * @param id az azonosit, amire az infot kertek
	 * @param attr az attributum, aminek az erteket ki kell iratni. null, ha az osszes attributum kiiratando
	 */
	@Override
	public void GetInfo(String id, String attr) {
		if(id.equals(this.id)) {
			super.GetInfo(id, attr);
			if (attr == null)
			{
				System.out.println("utkozott: " + utkozott);
				if (id.split("-").length > 1)
				kovKocsi.GetInfo(id.split("-")[1], attr);
			}
			else
			{
				switch (attr) {
				case "utkozott":
					System.out.println(this.id + ":");
					System.out.println("utkozott: " + utkozott);
					break;
			
				default:
					break;
				}
			}
		}
		else if (id.split("-").length > 1) {
			if(kovKocsi != null)
				kovKocsi.GetInfo(id.split("-")[1], attr);
		}
		
	}


}
