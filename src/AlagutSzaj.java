import java.util.Scanner;

public class AlagutSzaj extends SinElem {
	private Boolean megepitve;
	private SinElem alagutSin;
	
	//Reagál arra, ha a felhasználó rákattint az alagutSzaj-ra
	public void onInput() {
		System.out.println("AlagutSzaj.onInput()");
		Alagut a = Terepasztal.getInstance().getAlagut();
		//if(a.getAllapot() == AlagutAllapot.VanAlagut){
		System.out.println("Meg van mar epitve ez az alagutSzaj? (igen/nem)");
        Scanner reader = new Scanner(System.in);
        String valasz = reader.next();
        if (valasz.equals("igen")) {
			a.RemoveAlagutSzaj(this);
		}
		else if (valasz.equals("nem")){
			a.AddAlagutSzaj(this);
		}
	}
	
	//
	public void leptet(Mozdony m, SinElem s) {
		System.out.println("AlagutSzaj.leptet()");
		
		System.out.print("Meg van epitve az alagut? (i/n): ");
		Scanner reader = new Scanner(System.in);
		String valasz = reader.next();
		switch (valasz){
		case "i": m.alagutValt(); break;
		case "n": m.utkozik(); Jatek.getInstance().veszt();
		}
	}
	@Override
	public void raLep(Kocsi k) {
		System.out.println("AlagutSzaj.raLep()");
		
		k.alagutValt();
	}
	

	@Override
	public SinElem getKovSinElem(SinElem elozo) {
		System.out.println("AlagutSzaj.getKovSinElem()");
		return alagutSin;
	}
}
