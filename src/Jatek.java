public class Jatek {
	private static Jatek instance;
	private Timer timer;

	private Jatek(){
        System.out.println("Jatek.Constructor()");
    }

    public static Jatek getInstance(){
        System.out.println("Jatek.getInstance()");
        if(instance == null){
            instance = new Jatek();
        }
        return instance;
    }

	public void start(String palya) {
        System.out.println("Jatek.start()");
        timer = new Timer();
        Terepasztal.getInstance().init(palya);
        timer.start();
	}
	
	public void veszt() {
        System.out.println("Jatek.veszt()");
	}
	
	public void nyer() {
        System.out.println("Jatek.nyert()");
	}
}
