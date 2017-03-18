import java.util.Scanner;

public class Mozdony extends Szerelveny {
	private Boolean utkozott;
	public void utkozik() {
		System.out.println("Mozdony.utkozik()");
	}
	
	//Ezt a met�dost h�vja meg a terepasztal a vonatok mozgat�s�hoz.
	//Majd ez felel�s a tov�bbi kocsik mozgat�s��rt
	public void mozog() {
		System.out.println("Mozdony.mozog()");
		
		//Lek�rdezz�k a SinElem-t�l, amint �llunk, hogy melyik a k�vetkez�, amerre menni kell
		SinElem kovSin = sinElem.getKovSinElem();
		//ell�p�nk a sinElemr�l, amin �llunk
		sinElem.elLep();
		//�s r�l�p�nk a k�vetkez�re
		kovSin.raLep();
		kovSin.leptet(this, sinElem);
		
		Scanner reader = new Scanner(System.in);
		System.out.println("Van k�vetkez� kocsi? (igen/nem): ");
		if(reader.next().equals("igen"))
			//Ha van a mozdonyhoz kapcsol�dva m�g kocsi, akkor azt is mozgatjuk
			kovKocsi.mozog(sinElem);
			
		//Elmetj�k, hogy m�r a k�vetkez� sinen �llunk
		sinElem = kovSin;
	}
	
	//Ezt a f�ggv�nyt h�vja meg a terepasztal, hogy ellen�rizze az �tk�z�seket
	public void utkozesVizsgal() {
		System.out.println("Mozdony.utkozesVizsgal()");
		
		//Lek�rdezz�k, hogy a SinElem-en, amin a mozdony van, van-e m�sik szerelv�ny.
		Boolean utkozes = sinElem.getUtkozes();
		Scanner reader = new Scanner(System.in);
		System.out.println("Volt �tk�z�s? (igen/nem): ");
		if(reader.next().equals("igen")) {
			//Volt �tk�z�s
			utkozik();
			jatek.veszt();
		}
	}

	@Override
	public void leszallit(Allomas a) {
		// TODO Auto-generated method stub
		
	}
}
