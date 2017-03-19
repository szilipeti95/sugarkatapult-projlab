import java.util.Scanner;

public class AlagutSzaj extends SinElem {
	private Boolean megepitve;
	private SinElem alagutSin;
	
	//Reagál arra, ha a felhasználó rákattint az alagutSzaj-ra
	public void onInput() {
		System.out.println("AlagutSzaj.onInput()");
		Alagut a = Terepasztal.getInstance().getAlagut();
		if(a.getAllapot() == AlagutAllapot.VanAlagut){
			a.RemoveAlagutSzaj(this);
		}
		else{
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
	
	//Visszaadja, hogy van-e rajta ütközés
	public Boolean getUtkozes() {
		System.out.println("AlagutSzaj.getUtkozes()");
		return false;
	}

	@Override
	public SinElem getKovSinElem(SinElem elozo) {
		System.out.println("AlagutSzaj.getKovSinElem()");
		return alagutSin;
	}
}
