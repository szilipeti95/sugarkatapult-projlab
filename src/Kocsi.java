import java.util.Scanner;

public class Kocsi extends Szerelveny {
	private Szin szin;
	private Boolean ures;
	
	
	//Ezt a függvényt hívja meg az előző szerelvény
	//Ez felelős a kocsi mozgatasáért, és a követező kocsi mozgatásáért is
	public void mozog(SinElem kovSin) {
		System.out.println("Kocsi.mozog()");
		//Ellepunk az aktuális sínről
		System.out.print("Kocsi -> ");
		sinElem.elLep();
		
		//rálépünk a paraméterként kapott "következő" sínre
		System.out.print("Kocsi -> ");
		kovSin.raLep(this);
		
		Scanner reader = new Scanner(System.in);
		System.out.println("Van következő kocsi? (igen/nem): ");
		if(reader.next().equals("igen")) {
			kovKocsi = new Kocsi();
			//Ha van a kocsihoz kapcsolódva még kocsi, akkor azt is mozgatjuk
			System.out.print("Kocsi -> ");
			kovKocsi.mozog(sinElem);
			
			
		}
			
		//Elmentjük, hogy már a következő sinen állunk
		sinElem = kovSin;
		
	}
	
	//Ez a függvény hívódik meg, mikor az utasoknak le kell szállniuk a kocsiról
	//Megvizsgálja, hogy a kocsi színe megegyezik-e az állomás színével
	public void leszallit(Allomas a) {
		System.out.println("Kocsi.leszallit()");
		
		Scanner reader = new Scanner(System.in);
		System.out.println("Egyezik az allomas szine? (igen/nem): ");
		if(reader.next().equals("igen")) {
			//A két szín megegyezik
			ures = true;
			
			System.out.println("Van kovetkezo kocsi? (igen/nem): ");
			if(reader.next().equals("nem")) {
				//Nincs következő kocsi, ez a vonat kiürült
				System.out.print("Kocsi -> ");
				Terepasztal.getInstance().vonatKiurult();
			}
		}
	}
}
