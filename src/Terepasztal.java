import java.util.ArrayList;

public class Terepasztal {
	private int teliVonatSzam;
	private static Terepasztal instance;
	private ArrayList<Mozdony> mozdonyok;
	private ArrayList<SinElem> sinelemek;
	private ArrayList<BeSin> besinek;
	private Alagut alagut;

	private Terepasztal(){
        System.out.println("Terepasztal.Constructor()");
        teliVonatSzam = 0;
        mozdonyok = new ArrayList<>();
        sinelemek = new ArrayList<>();
        besinek = new ArrayList<>();

	}

	public static Terepasztal getInstance(){
	    System.out.println("Terepasztal.getInstance()");
		if(instance == null){
			instance = new Terepasztal();
		}
		return instance;
	}

	public void tick() {
        System.out.println("Terepasztal.tick()");
	    for(Mozdony m: mozdonyok){
            m.mozog();
        }
        for(Mozdony m: mozdonyok){
            m.utkozesVizsgal();
        }
	}
	
	public void init(String fileName) {
		System.out.println("Terepasztal.init()");
		mozdonyok.add(new Mozdony());
		sinelemek.add(new Sin());
		BeSin besin = new BeSin();
		sinelemek.add(besin);
		besinek.add(besin);
	}
	
	public void onInput(int x, int y) {
	    System.out.println("Terepasztal.onInput()");
        for(SinElem s: sinelemek){
            if(s.getX() == x && s.getY() == y){
                System.out.println("SinElem talalt");
                s.onInput();
            }
        }
	}
	
	public void AddAlagutSzaj() {
	    System.out.println("Terepasztal.AddAlagutSzaj()");
	    //TODO: implement
        //Amugy ez mi?
	}
	
	public void AddMozdony(Mozdony m) {
	    System.out.println("Terepasztal.AddMozdony()");
	    mozdonyok.add(m);
	}
	
	public void AddBeSin(BeSin b) {
        System.out.println("Terepasztal.AddBeSin()");
        besinek.add(b);
    }
	
	public void AddSinElem(SinElem s) {
        System.out.println("Terepasztal.AddSinElem()");
        sinelemek.add(s);
	}
	
	public boolean RemoveSinElem(SinElem s) {
	    System.out.println("Terepasztal.RemoveSinElem()");
	    return sinelemek.remove(s);
	}
	
	public Alagut getAlagut() {
	    System.out.println("Terepasztal.getAlagut()");
		return alagut;
	}
	
	public void vonatKiurult() {
	    teliVonatSzam--;
	    System.out.println("Terepasztal.vonatKiurult()");
	    System.out.println("teli vonatok szama: " + teliVonatSzam);
	    if(teliVonatSzam == 0) {
            Jatek.getInstance().nyer();
        }
	}

}
