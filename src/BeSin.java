import java.util.Scanner;

public class BeSin extends SinElem {//vonatok belelepesi pontja
	
	@Override //sinEleme
	public void leptet(Mozdony m, SinElem s) {
		System.out.println("BeSin.leptet()");
        szerelvenyek++;//SinEleme
	}
	
	public void VonatBead() {
		System.out.println("BeSin.VonatBead()");
		
		System.out.print("BeSin -> ");
		Terepasztal.getInstance().AddMozdony(new Mozdony());
	}
	
	@Override
	public SinElem getKovSinElem(SinElem elozo) {//itt az elozo elvileg null
		System.out.println("BeSin.getKovSinElem()");
		return sinA;//ez jo, mert nincs sinBje a BeSinnek
	}
}

//Zotya
