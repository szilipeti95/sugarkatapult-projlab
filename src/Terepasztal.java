import java.util.ArrayList;
import java.util.Scanner;

public class Terepasztal {
	private int teliVonatSzam;
	private static Terepasztal instance;
	private ArrayList<Mozdony> mozdonyok;
	private ArrayList<SinElem> sinelemek;
	private ArrayList<BeSin> besinek;
	private Alagut alagut;

	/**
	 * Terepasztal alapértékeit beállítja, listákat létrehozza
	 */
	private Terepasztal(){
        teliVonatSzam = 0;
        mozdonyok = new ArrayList<>();
        sinelemek = new ArrayList<>();
        besinek = new ArrayList<>();
        alagut = new Alagut();

	}
	/**
	 * Singleton osztály, lekérdezi a Terepasztalt
	 * @return Terepasztal referencia
	 */
	public static Terepasztal getInstance(){
		if(instance == null){
			instance = new Terepasztal();
		}
		return instance;
	}

	/**
	 * Timer tickre ez a függvény hívódik meg.
	 * Mozgatja a vonatokat, és vizsgálja az ütközéseket
	 */
	public void tick() {
	    for(Mozdony m: mozdonyok){
            m.mozog();
        }
        for(Mozdony m: mozdonyok){
            m.utkozesVizsgal();
        }
	}

	/**
	 * Inicializálja a Terepasztal elemeit
	 * @param fileName a fájl neve amiből a beolvasás történik
	 */
	public void init(String fileName) {
		//TODO: implement
	}

	/**
	 * Kattintásra hívódik meg a függvény
	 * Meghívja annak a SinElemnek az onClickjét amire kattintott a játékos
	 * @param x a kattintás x koordinátája
	 * @param y a kattintás y koordinátája
	 */
	public void onInput(int x, int y) {
		//megkeresi a sinelemet aminek x,y koordinátája megegyezik az inputtal
	    for(int i=0; i<sinelemek.size(); i++){
	    	// ha megegyezik meghívja az onInput függvényt
	    	if(sinelemek.get(i).getX() == x && sinelemek.get(i).getY() == y){
				sinelemek.get(i).onInput();
	    		break;
			}
		}
	}

	/**
	 *
	 */
	@Deprecated
	public void AddAlagutSzaj() {
	    System.out.println("Terepasztal.AddAlagutSzaj()");
	    //TODO: implement
        //Amugy ez mi?
	}

	/**
	 * Mozdony hozzáadás a listához
	 * @param m a mozdony amit hozzá kell adni a terepasztalhoz
	 */
	public void AddMozdony(Mozdony m) {
	    mozdonyok.add(m);
	    teliVonatSzam++;
	}

	/**
	 * BeSin hozzáadás a listához
	 * @param b BeSin refrerencia ami hozzáadódik a listához
	 */
	public void AddBeSin(BeSin b) {
        besinek.add(b);
    }

	/**
	 * SinElem hozzáadás a listához
	 * @param s SinElem referencia ami hozzáadódik a listához
	 */
	public void AddSinElem(SinElem s) {
        sinelemek.add(s);
	}
	/**
	 * SinElem törlése listából.
	 * @param s SinElem referencia amit törölni kell a listából
	 * @return true ha sikerült a törlés, false ha nem
	 */
	public boolean RemoveSinElem(SinElem s) {
	    return sinelemek.remove(s);
	}

	/**
	 * Visszaadja a terepasztal alagútját
	 * @return Alagut referencia
	 */
	public Alagut getAlagut() {
		return alagut;
	}

	/**
	 * teliVonatSzam számontartja hogy hány vonat van a terepasztalon
	 * aminek még van utasa.
	 * Ha ez a szám 0 a játéknak vége és a játékos nyert.
	 */
	public void vonatKiurult() {
	    teliVonatSzam--;
	    //ha elfogytak a vonatok
	    if (teliVonatSzam == 0)
	    {
			Jatek.getInstance().nyer();
        }
	}

	/**
	 * bead egy új vonatot
	 * @param index besin indexe ahol jön az új vonat
	 */
	public void VonatBead(int index) {
		besinek.get(index).VonatBead();
	}

}
