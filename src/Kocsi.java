import java.util.Scanner;

public class Kocsi extends Szerelveny {
	private Szin szin;
	private Boolean ures;
	
	//Ezt a függvényt hívja meg az elõzõ szerelvény
	//Ez felelõs a kocsi mozgatásáért, és a követezõ kocsi mozgatásáért is
	public void mozog(SinElem kovSin) {
		System.out.println("Kocsi.mozog()");
		//Ellepunk az aktuális sínrõl
		sinElem.elLep();
		
		//rálépünk a paraméterként kapott "következõ" sínre
		kovSin.raLep();
		
		Scanner reader = new Scanner(System.in);
		System.out.println("Van következõ kocsi? (igen/nem): ");
		if(reader.next().equals("igen")) {
			//Ha van a kocsihoz kapcsolódva még kocsi, akkor azt is mozgatjuk
			kovKocsi.mozog(sinElem);
			
			
		}
			
		//Elmetjük, hogy már a következõ sinen állunk
		sinElem = kovSin;
		
	}
	
	//Ez a függvény hívódik meg, mikor az utasoknak le kell szállniuk a kocsiról
	//Megvizsgálja, hogy a kocsi színe megegyezik-e az állomás színével
	@Override
	public void leszallit(Allomas a) {
		System.out.println("Kocsi.leszallit()");
		
		Scanner reader = new Scanner(System.in);
		System.out.println("Egyezik az állomás színe? (igen/nem): ");
		if(reader.next().equals("igen")) {
			//A két szín megegyezik
			ures = true;
			
			System.out.println("Van következõ kocsi? (igen/nem): ");
			if(reader.next().equals("nem")) {
				//Nincs következõ kocsi, ez a vonat kiürült
				terepAsztal.vonatKiurult();
			}
		}
	}
}
