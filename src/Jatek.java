/**
 * szamontartja a szukseges osztalyokat
 * nyeres, vesztes kezelese
 * 
 * Singleton
 * Tartalmazza a terepasztalt es a timert.
 * A timer mukodteti a terepasztalt.
 */
public class Jatek {
    /**
     * Jatek egyetlen peldanya
     */
	private static Jatek instance;
    /**
     * Az idozito referenciaja
     */
	private Timer timer;
	private boolean nyert = false;
	private Rajzolo rajzolo;
    private Menu menu;
    private int currentProgress = 0;

    /**
     * privat konstruktor
     */
    private Jatek(){
           
    }

    /**
     * Singleton osztaly
     * @return a Jatek maga
     */
    public static Jatek getInstance(){
        if(instance == null){
            instance = new Jatek();
        }
        return instance;
    }

    /**
     * Jatek elinditasa.
     * A menuben ha a jatekos elindítja a jatekot meghívódik ez a fuggveny
     * Betolti a terepasztalt es elindítja a Timert
     * @param palya a palyanak a neve
     */
    public void start(String palya) {
        nyert = false;
    	rajzolo.loadImages();
        timer = new Timer();
        currentProgress = menu.getProgress();
        Terepasztal.getInstance().reset();
		Terepasztal.getInstance().init(palya);
		timer.start();
	}

    /**
     * Jatekos veszt
     * ket vonat osszeutkozott a palyan
     */
	public void veszt() {
        if(!nyert){
            Terepasztal.getInstance().Vege();
            nyert = false; //lezarjuk
            timer.stop();
            //a vesztettel uzenetet kesobb irjuk ki
        }
	}

    /**
     * Jatekos nyer
     * elfogytak az utasok
     */
	public void nyer() {
		timer.stop();
        nyert = true;
        Terepasztal.getInstance().Vege();
		if(currentProgress == menu.getProgress() && menu.getProgress() < 3 ){
			menu.addProgress();//megnyitjuk a kovetkezo palya lehetoseget, ha van
			//a nyertel uzenetet kesobb irjuk ki
        }//max 3 map
	}
	
	/**
	 * setter a rajzolohoz
	 * @param r1 rajzolo megadasa
	 */
	public void setRajzolo(Rajzolo r1)
	{
		rajzolo=r1;
	}
	
	/**
	 * getter a rajzolohoz
	 */
	public Rajzolo getRajzolo()
	{
		return rajzolo;
	}
    
	/**
	 * setter a menuhoz
	 * @param m menu megadasa
	 */
    public void setMenu(Menu m){
        menu = m;
    }

    /**
     * getter a nyeres lekerdezesehez
     * @return igaz, ha a jatekos nyert
     */
    public boolean getNyert(){
        return nyert;
    }
        
}
