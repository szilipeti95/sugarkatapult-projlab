import java.util.Scanner;

public class AlagutSzaj extends SinElem {
	private boolean megepitve;
	private SinElem alagutSin;
	
	//Reagál arra, ha a felhasználó rákattint az alagutSzaj-ra
	public void onInput() {
		System.out.println("AlagutSzaj.onInput()");
		System.out.print("AlagutSzaj -> ");
		Alagut a = Terepasztal.getInstance().getAlagut();
		System.out.println("Meg van mar epitve ez az alagutSzaj? (igen/nem)");
        Scanner reader = new Scanner(System.in);
        String valasz = reader.next();
        if (valasz.equals("igen")) {
    		System.out.print("AlagutSzaj -> ");
			a.RemoveAlagutSzaj(this);
		}
		else if (valasz.equals("nem")){
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
				System.out.print("AlagutSzaj -> ");
				m.alagutValt(); break;
		case "nem":
			System.out.print("AlagutSzaj -> ");
			m.utkozik(); 
			System.out.print("AlagutSzaj -> ");
			Jatek.getInstance().veszt();
			break;
		}
	}
	@Override
	public void raLep(Kocsi k) {
		System.out.println("AlagutSzaj.raLep()");
		
		System.out.print("AlagutSzaj -> ");
		k.alagutValt();
	}
	

	@Override
	public SinElem getKovSinElem(SinElem elozo) {
		System.out.println("AlagutSzaj.getKovSinElem()");
		return alagutSin;
	}
}
