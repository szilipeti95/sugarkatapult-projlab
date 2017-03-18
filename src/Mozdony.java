import java.util.Scanner;

public class Mozdony extends Szerelveny {
	private Boolean utkozott;
	public void utkozik() {
		System.out.println("Mozdony.utkozik()");
	}
	
	//Ezt a metódost hívja meg a terepasztal a vonatok mozgatásához.
	//Majd ez felelõs a további kocsik mozgatásáért
	public void mozog() {
		System.out.println("Mozdony.mozog()");
		
		//Lekérdezzük a SinElem-tõl, amint állunk, hogy melyik a következõ, amerre menni kell
		SinElem kovSin = sinElem.getKovSinElem();
		//ellépünk a sinElemrõl, amin állunk
		sinElem.elLep();
		//és rálépünk a következõre
		kovSin.raLep();
		kovSin.leptet(this, sinElem);
		
		Scanner reader = new Scanner(System.in);
		System.out.println("Van következõ kocsi? (igen/nem): ");
		if(reader.next().equals("igen"))
			//Ha van a mozdonyhoz kapcsolódva még kocsi, akkor azt is mozgatjuk
			kovKocsi.mozog(sinElem);
			
		//Elmetjük, hogy már a következõ sinen állunk
		sinElem = kovSin;
	}
	
	//Ezt a függvényt hívja meg a terepasztal, hogy ellenõrizze az ütközéseket
	public void utkozesVizsgal() {
		System.out.println("Mozdony.utkozesVizsgal()");
		
		//Lekérdezzük, hogy a SinElem-en, amin a mozdony van, van-e másik szerelvény.
		Boolean utkozes = sinElem.getUtkozes();
		Scanner reader = new Scanner(System.in);
		System.out.println("Volt ütközés? (igen/nem): ");
		if(reader.next().equals("igen")) {
			//Volt ütközés
			utkozik();
			jatek.veszt();
		}
	}

	@Override
	public void leszallit(Allomas a) {
		// TODO Auto-generated method stub
		
	}
}
