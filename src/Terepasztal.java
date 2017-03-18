import java.util.ArrayList;

public class Terepasztal {
	private int teliVonatSzam;
	private static Terepasztal instance;
	private ArrayList<Mozdony> mozdonyok;
	private ArrayList<SinElem> sinelemek;
	private ArrayList<BeSin> besinek;
	private Alagut alagut;

	private Terepasztal(){
        teliVonatSzam = 0;
        mozdonyok = new ArrayList<>();
        sinelemek = new ArrayList<>();
        besinek = new ArrayList<>();
	}

	public static Terepasztal getInstance(){
		if(instance == null){
		    System.out.println("Terepasztal create");
			instance = new Terepasztal();
		}
		return instance;
	}

	public void tick() {
	    for(Mozdony m: mozdonyok){
            System.out.println("MozdonyMozgat");
            m.mozog();
        }
        for(Mozdony m: mozdonyok){
            System.out.println("Mozdony utkozesVizsgal");
            m.utkozesVizsgal();
        }
	}
	
	public void init(String fileName) {
        System.out.println("Terepasztal init/beolvas");
	}
	
	public void onInput(int x, int y) {
	    System.out.println("SinElem kereses onInput");
        for(SinElem s: sinelemek){
            if(s.getX() == x && s.getY() == y){
                System.out.println("SinElem talalt");
                s.onInput();
            }
        }
	}
	
	public void AddAlagutSzaj() {
	    System.out.println("AddAlagutSzaj");
	    //TODO: implement
        //Amugy ez mi?
	}
	
	public void AddMozdony(Mozdony m) {
	    System.out.println("AddMozdony");
	    mozdonyok.add(m);
	}
	
	public void AddBeSin(BeSin b) {
        System.out.println("AddBeSin");
        besinek.add(b);
    }
	
	public void AddSinElem(SinElem s) {
        System.out.println("AddSinElem");
        sinelemek.add(s);
	}
	
	public boolean RemoveSinElem(SinElem s) {
	    System.out.println("RemoveSinElem");
	    return sinelemek.remove(s);
	}
	
	public Alagut getAlagut() {
	    System.out.println("getAlagut");
		return alagut;
	}
	
	public void vonatKiurult() {
	    teliVonatSzam--;
	    System.out.println("vonatKiurult");
	    System.out.println("teli vonatok szama: " + teliVonatSzam);
	    if(teliVonatSzam == 0) {
            Jatek.getInstance().nyer();
        }
	}

}
