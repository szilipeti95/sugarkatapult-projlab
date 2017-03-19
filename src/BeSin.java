import java.util.Scanner;

public class BeSin extends SinElem {//vonatok belelepesi pontja
	
	@Override //sinEleme
	public void leptet(Mozdony m, SinElem s) {
		System.out.println("BeSin.leptet()");
        szerelvenyek++;//SinEleme
	}
	
	public void VonatBead() {
		System.out.println("BeSin.VonatBead()");
		
		Terepasztal.getInstance().AddMozdony(new Mozdony());
	}
	public Boolean getUtkozes() {
		System.out.println("BeSin.getUtkozes()");
		
		//skeleton dolgok
		Scanner reader = new Scanner(System.in);
		System.out.println("Adja meg a szerelvenyek szamat (min. 0): ");
		try
		{//user inputot beolvassuk
			szerelvenyek = Integer.parseInt(reader.next());
		}
		catch (Exception e)
		{//rossz input, nem szam
			szerelvenyek=0;
			System.out.println("Nem szam, 0 lesz - " + e.getMessage());
		}finally{}
		
		return szerelvenyek > 0;//el�g egy szerelveny is, hogy utkozzon
	}

	@Override
	public SinElem getKovSinElem(SinElem elozo) {//itt az elozo elvileg null
		System.out.println("BeSin.getKovSinElem()");
		return sinA;//ez jo, mert nincs sinBje a BeSinnek
	}
}

//Zotya
