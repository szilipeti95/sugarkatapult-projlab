import java.util.Scanner;

public class AlagutSzaj extends SinElem {
	private boolean megepitve;
	private SinElem alagutSin;
	
	//Reagál arra, ha a felhasználó rákattint az alagutSzaj-ra
	public void onInput() {
		System.out.println("AlagutSzaj.onInput()");
		
		//Elkerjuk a terepasztaltol az alagutat
		System.out.print("AlagutSzaj -> ");
		Alagut a = Terepasztal.getInstance().getAlagut();
		System.out.println("Meg van mar epitve ez az alagutSzaj? (igen/nem)");
        Scanner reader = new Scanner(System.in);
        String valasz = reader.next();
        if (valasz.equals("igen")) {
        	//A felhasznalo egy mar megepitett alagutSzajra kattintott. Le kell bontani
    		System.out.print("AlagutSzaj -> ");
			a.RemoveAlagutSzaj(this);
		}
		else if (valasz.equals("nem")){
			//A felhasznalo egy meg nem megepitett alagutSzajra kattintott. fel kell epiteni
			System.out.print("AlagutSzaj -> ");
			a.AddAlagutSzaj(this);
		}
	}
	
	//
	public void leptet(Mozdony m, SinElem s) {
		System.out.println("AlagutSzaj.leptet()");
		
		System.out.println("Meg van epitve az alagut? (igen/nem): ");
		Scanner reader = new Scanner(System.in);
		String valasz = reader.next();
		switch (valasz){
		case "igen":
				//Ha az alagut fel van epitve, jelezzuk a mozdony fele, hogy alagutban halad
				System.out.print("AlagutSzaj -> ");
				m.alagutValt(); break;
		case "nem":
			//Ha nincs megepitve az alagutSzaj. A vonat utkozik
			System.out.print("AlagutSzaj -> ");
			m.utkozik(); 
			//Es elveszitjuk a jatekot
			System.out.print("AlagutSzaj -> ");
			Jatek.getInstance().veszt();
			break;
		}
	}
	@Override
	public void raLep(Kocsi k) {
		System.out.println("AlagutSzaj.raLep()");
		
		//Tudatjuk a kocsikkal, hogy alagutbn haladnak
		System.out.print("AlagutSzaj -> ");
		k.alagutValt();
	}
	

	@Override
	public SinElem getKovSinElem(SinElem elozo) {
		System.out.println("AlagutSzaj.getKovSinElem()");
		return alagutSin;
	}
}
