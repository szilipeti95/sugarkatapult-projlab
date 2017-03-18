import java.util.Scanner;

public class Kocsi extends Szerelveny {
	private Szin szin;
	private Boolean ures;
	
	//Ezt a f�ggv�nyt h�vja meg az el�z� szerelv�ny
	//Ez felel�s a kocsi mozgat�s��rt, �s a k�vetez� kocsi mozgat�s��rt is
	public void mozog(SinElem kovSin) {
		System.out.println("Kocsi.mozog()");
		//Ellepunk az aktu�lis s�nr�l
		sinElem.elLep();
		
		//r�l�p�nk a param�terk�nt kapott "k�vetkez�" s�nre
		kovSin.raLep();
		
		Scanner reader = new Scanner(System.in);
		System.out.println("Van k�vetkez� kocsi? (igen/nem): ");
		if(reader.next().equals("igen")) {
			//Ha van a kocsihoz kapcsol�dva m�g kocsi, akkor azt is mozgatjuk
			kovKocsi.mozog(sinElem);
			
			
		}
			
		//Elmetj�k, hogy m�r a k�vetkez� sinen �llunk
		sinElem = kovSin;
		
	}
	
	//Ez a f�ggv�ny h�v�dik meg, mikor az utasoknak le kell sz�llniuk a kocsir�l
	//Megvizsg�lja, hogy a kocsi sz�ne megegyezik-e az �llom�s sz�n�vel
	@Override
	public void leszallit(Allomas a) {
		System.out.println("Kocsi.leszallit()");
		
		Scanner reader = new Scanner(System.in);
		System.out.println("Egyezik az �llom�s sz�ne? (igen/nem): ");
		if(reader.next().equals("igen")) {
			//A k�t sz�n megegyezik
			ures = true;
			
			System.out.println("Van k�vetkez� kocsi? (igen/nem): ");
			if(reader.next().equals("nem")) {
				//Nincs k�vetkez� kocsi, ez a vonat ki�r�lt
				terepAsztal.vonatKiurult();
			}
		}
	}
}
