
import javax.swing.JOptionPane;

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
	private boolean nyert = false;
	private Controller controller;
	private Rajzolo rajzolo;
        private Menu menu;
        private int currentProgress = 0;

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
        nyert = false;
    	rajzolo.loadImages();
        timer = new Timer();
        currentProgress = menu.getProgress();
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
        if(!nyert){
            nyert = true; //lezárjuk
            timer.stop();
            System.out.println("vege vesztett");
            Terepasztal.getInstance().Vege();
            //JOptionPane.showMessageDialog(menu, "VESZTETTÉL!", "Defeat is unacceptable!", JOptionPane.WARNING_MESSAGE);
        }
        
        
	}

    /**
     * Játékos nyer
     * elfogytak az utasok
     */
	public void nyer() {
		System.out.println("vege nyert");
		timer.stop();
                nyert = true;
                Terepasztal.getInstance().Vege();
		if(currentProgress == menu.getProgress() && menu.getProgress() < 3 ){
                    menu.addProgress();
                    //JOptionPane.showMessageDialog(menu, "NYERTÉL!", "Victory lies ahead!", JOptionPane.WARNING_MESSAGE);
                }//max 3 map
			
		
	}
	
	public void setRajzolo(Rajzolo r1)
	{
		rajzolo=r1;
	}
	
	public void setController(Controller c1)
	{
		controller=c1;
	}
	
	public Rajzolo getRajzolo()
	{
		return rajzolo;
	}
        
        public void setMenu(Menu m){
            menu = m;
        }
	
        public boolean getNyert(){
            return nyert;
        }
        
}
