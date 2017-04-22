/**
 * Tartalmazza a terepasztalt és a timert.
 * A timer működteti a terepasztalt.
 */
public class Jatek {
    /**
     * Játék egyetlen példánya
     */
	private static Jatek instance;
    /**
     * Az időzítő referenciája
     */
	private Timer timer;

    /**
     * privát konstruktor
     */
    private Jatek(){

    }

    /**
     * //Singleton osztály
     * @return a Jatek maga
     */
    public static Jatek getInstance(){
        if(instance == null){
            instance = new Jatek();
        }
        return instance;
    }

    /**
     * Játék elindítása.
     * A menüben ha a játékos elindítja a játékot meghívódik ez a függvény
     * Betölti a terepasztalt és elindítja a Timert
     * @param palya a pályának a neve
     */
    public void start(String palya) {
        timer = new Timer();
        Terepasztal.getInstance().reset();
		Terepasztal.getInstance().init(palya);
		timer.start();
	}

    /**
     * Játékos veszt
     * két vonat összeütközött a pályán
     */
	public void veszt() {
        //System.exit(0);
        System.out.println("vege vesztett");
	}

    /**
     * Játékos nyer
     * elfogytak az utasok
     */
	public void nyer() {
      //  System.exit(0);
		System.out.println("vege nyert");
	}
}
