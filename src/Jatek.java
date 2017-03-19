public class Jatek {
	private static Jatek instance;
	private Timer timer;


	private Jatek(){
        System.out.println("Jatek.Constructor()");
    }

    //Singleton osztály, visszaadja a Jatekot
    public static Jatek getInstance(){
        System.out.println("Jatek.getInstance()");
        if(instance == null){
            instance = new Jatek();
        }
        return instance;
    }

    //Játék elindítása.
    //A menüben ha a játékos elindítja a játékot meghívódik ez a függvény
    //Betölti a terepasztalt és elindítja a Timert
	public void start(String palya) {
        System.out.println("Jatek.start()");
        timer = new Timer();
        Terepasztal.getInstance().init(palya);
        timer.start();
	}
	//Vége a játéknak, veszt a játékos.
    //akkor hívódik meg ha történt ütközés
	public void veszt() {
        System.out.println("Jatek.veszt()");
	}
	//Vége a játéknak, nyert a játékos.
    //Akkor hívódik meg ha a Terepasztalon elfogytak a vonatok.
	public void nyer() {
        System.out.println("Jatek.nyert()");
	}
}
