import java.util.Scanner;

public class Mozdony extends Szerelveny {
	private boolean utkozott;
	
	//Ez a metódus hívidik meg, ha a mozdony nekimegy egy másik kocsinak.
	public void utkozik() {
		System.out.println("Mozdony.utkozik()");
		utkozott = true;
	}
	
	//Ezt a metódost hívja meg a terepasztal a vonatok mozgatásához.
	//Majd ez felelős a további kocsik mozgatásáért
	public void mozog() {
		System.out.println("Mozdony.mozog()");
		
		//Lekérdezzük a SinElem-től, amint állunk, hogy melyik a következő, amerre menni kell
		SinElem kovSin = sinElem.getKovSinElem(elozoSinElem);
		System.out.println("Mi legyen a kovetkezo sin?: (sin, allomas, valto, alagutszaj)");
		Scanner reader = new Scanner(System.in);
		switch (reader.next()) {
		case "valto":
			kovSin = new Valto();
			break;
		case "allomas":
			kovSin = new Allomas();
			break;
		case "alagutszaj":
			kovSin = new AlagutSzaj();
			break;
		case "sin":
		default:
			kovSin = new Sin();
			break;
		}
		//ellépünk a sinElemről, amin állunk
		sinElem.elLep();
		//és rálépünk a következőre
		kovSin.leptet(this, sinElem);
		
		System.out.println("Van következő kocsi? (igen/nem): ");
		if(reader.next().equals("igen")) {
			kovKocsi = new Kocsi();
			//Ha van a mozdonyhoz kapcsolódva még kocsi, akkor azt is mozgatjuk
			kovKocsi.mozog(sinElem);
		}
		//Elmetjük, hogy már a következő sinen állunk
		sinElem = kovSin;
		
	}
	
	//Ezt a függvényt hívja meg a terepasztal, hogy ellenőrizze az ütközéseket
	public void utkozesVizsgal() {
		System.out.println("Mozdony.utkozesVizsgal()");
		
		//Lekérdezzük, hogy a SinElem-en, amin a mozdony van, van-e másik szerelvény.
		boolean utkozes = sinElem.getUtkozes();
		Scanner reader = new Scanner(System.in);
		System.out.println("Volt ütközés? (igen/nem): ");
		if(reader.next().equals("igen")) {
			//Volt ütközés
			utkozik();
			Jatek.getInstance().veszt();
		}
	}

}
