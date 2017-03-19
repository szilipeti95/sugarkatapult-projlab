import java.util.Scanner;

public class Allomas extends SinElem {
	//szinet tarolja, szol a kocsinak hogy szallitson le
	
	private SinElem sinB;//masik irany
	private Szin szin;//allomas szine
	
	/*public Allomas(SinElem CsinB, Szin Cszin)
	{//konstruktor
		sinB=CsinB;//privat valtozok megadasa itt
		szin=Cszin;
	}*/
	
	@Override //sinEleme
	public void raLep(Kocsi k) {
        System.out.println("Allomas.raLep()");
        k.leszallit(this);//szerelvenynek szol hogy allomasra ert
	}
	
	@Override //sinEleme
	public void leptet(Mozdony m, SinElem s) {
		System.out.println("Allomas.leptet()");
        szerelvenyek++;//SinEleme
	}
	
	public Szin getSzin() {
		System.out.println("Allomas.getSzin()");
		
		//skeleton dolgok
		Scanner reader = new Scanner(System.in);
		System.out.println("Adja meg az allomas szinet: ");
		String valasz = reader.next();
		reader.close();
		switch(valasz)
		{//user alapjan szin kivalasztasa
		case "piros":
			szin=Szin.PIROS;
			break;
		case "kek":
			szin=Szin.KEK;
			break;
		case "zold":
			szin=Szin.ZOLD;
			break;
		case "sarga":
			szin=Szin.SARGA;
			break;
		default://user nem jo szint adott meg
			szin=Szin.PIROS;
			System.out.println("Nem jo szin, piros lesz");
		}
		
		return szin;
	}
	
	public Boolean getUtkozes() {//van e tobb szerelveny ugyanitt
		System.out.println("Allomas.getUtkozes()");
		
		return szerelvenyek > 1;
	}

	@Override //sinEleme
	public SinElem getKovSinElem(SinElem elozo) {
		System.out.println("Allomas.getKovSinElem()");
		
		
		//skeleton dolgok
		Scanner reader = new Scanner(System.in);
		System.out.println("Honnan jon a vonat? (a/b): ");
		String valasz = reader.next();
		switch (valasz)
		{//user alapjan megtudjuk, hogy honnan jon a vonat
		case "a":
		case "A":
			elozo=sinA;
			break;
		case "b":
		case "B":
			elozo=sinB;
			break;
		default:
			elozo=sinA;
			System.out.println("nem jo. Akkor 'a' sin felol jon.");
			break;
		}
		
		
		if(elozo==sinA)
			return sinB;
		return sinA;//default, elvileg itt elozo==sinB
	}
}

//Zotya