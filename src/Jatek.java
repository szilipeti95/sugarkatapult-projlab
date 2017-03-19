public class Jatek {
	private static Jatek instance;
	private Timer timer;

	//Singleton osztály, visszaadja a Jatekot
    public static Jatek getInstance(){
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
	
	public void veszt() {
        System.out.println("Jatek.veszt()");
        System.exit(0);
	}
	
	public void nyer() {
        System.out.println("Jatek.nyert()");
        System.exit(0);
	}
}
