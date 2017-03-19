import java.util.ArrayList;
import java.util.Scanner;

public class Terepasztal {
	private int teliVonatSzam;
	private static Terepasztal instance;
	private ArrayList<Mozdony> mozdonyok;
	private ArrayList<SinElem> sinelemek;
	private ArrayList<BeSin> besinek;
	private Alagut alagut;

	//Terepasztal alapértékeit beállítja, listákat létrehozza
	private Terepasztal(){
        teliVonatSzam = 0;
        mozdonyok = new ArrayList<>();
        sinelemek = new ArrayList<>();
        besinek = new ArrayList<>();
        alagut = new Alagut();

	}
    //Singleton osztály, lekérdezi a Terepasztalt
	public static Terepasztal getInstance(){
		if(instance == null){
			instance = new Terepasztal();
		}
		return instance;
	}

	//Timer tickre ez a függvény hívódik meg.
    //Mozgatja a vonatokat, és vizsgálja az ütközéseket
	public void tick() {
        System.out.println("Terepasztal.tick()");
	    for(Mozdony m: mozdonyok){
            m.mozog();
        }
        for(Mozdony m: mozdonyok){
            m.utkozesVizsgal();
        }
	}

	//Inicializálja a Terepasztal elemeit (mozdonyok, sinek, besinek)
	public void init(String fileName) {
		System.out.println("Terepasztal.init()");
		AddMozdony(new Mozdony());
		AddSinElem(new Sin());
		BeSin besin = new BeSin();
		AddBeSin(besin);
		besinek.add(besin);
	}

	//Kattintásra hívódik meg a függvény
    //Meghívja annak a SinElemnek az onClickjét amire kattintott a játékos
	public void onInput(int x, int y) {
	    System.out.println("Terepasztal.onInput()");
        
	    System.out.println("Mire kattintott: (valto, alagutszaj)");
		Scanner reader = new Scanner(System.in);
		String valasz = reader.next();
		SinElem s = null;
		boolean jo = false;
		while(!jo) {
			if (valasz.equals("valto")){
				jo = true;
				s = new Valto();
			}
			else if (valasz.equals("alagutszaj")){
				jo = true;
				s = new AlagutSzaj();
			}
		}
		s.onInput();
	}
	
	public void AddAlagutSzaj() {
	    System.out.println("Terepasztal.AddAlagutSzaj()");
	    //TODO: implement
        //Amugy ez mi?
	}
	//Mozdony hozzáadás a listához
	public void AddMozdony(Mozdony m) {
	    System.out.println("Terepasztal.AddMozdony()");
	    mozdonyok.add(m);
	}
	//BeSin hozzáadás a listához
	public void AddBeSin(BeSin b) {
        System.out.println("Terepasztal.AddBeSin()");
        besinek.add(b);
    }
	//SinElem hozzáadás a listához
	public void AddSinElem(SinElem s) {
        System.out.println("Terepasztal.AddSinElem()");
        sinelemek.add(s);
	}
	//SinElem törlése listából.
    //Visszatérési érték boolean az alapján hogy sikerült-e vagy se.
	public boolean RemoveSinElem(SinElem s) {
	    System.out.println("Terepasztal.RemoveSinElem()");
	    return sinelemek.remove(s);
	}

	//Visszaadja a terepasztal alagútját
	public Alagut getAlagut() {
	    System.out.println("Terepasztal.getAlagut()");
		return alagut;
	}

	//teliVonatSzam számontartja hogy hány vonat van a terepasztalon
    //aminek még van utasa.
    //Ahogy egy kiürül meghívódik a függvény és csökkenti ezt a számot
    //Ha ez a szám 0 a játéknak vége és a játékos nyert.
	public void vonatKiurult() {
	    teliVonatSzam--;
	    System.out.println("Terepasztal.vonatKiurult()");
	    System.out.println("teli vonatok szama: " + teliVonatSzam);
	    if(teliVonatSzam == 0) {
            Jatek.getInstance().nyer();
        }
	}
	
	public void VonatBead() {
		System.out.println("Terepasztal.VonatBead()");
		
		besinek.get(0).VonatBead();
	}

}
