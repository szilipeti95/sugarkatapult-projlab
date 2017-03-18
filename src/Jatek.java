public class Jatek {
	private static Jatek instance;
	private Timer timer;

	private Jatek(){
    }

    public static Jatek getInstance(){
        if(instance == null){
            System.out.println("Jatek inicializalasa");
            instance = new Jatek();
        }
        return instance;
    }

	public void start(String palya) {
        System.out.println("Jatek inditasa");
        timer = new Timer();
        Terepasztal.getInstance().init(palya);
        timer.start();
	}
	
	public void veszt() {
        System.out.println("Vesztett");
	}
	
	public void nyer() {
        System.out.println("Nyert");
	}
}
