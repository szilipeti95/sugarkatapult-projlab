import java.util.Scanner;

public class BeSin extends SinElem {//vonatok belelepesi pontja
	
	
	BeSin(String id) 
    {
    	super(id);
    }
	@Override //sinEleme
	public void leptet(Mozdony m, SinElem s) {
		//A mozdony ralepett a sinElemre
		System.out.println("BeSin.leptet()");
        szerelvenyek++;//SinEleme
	}
	
	public void VonatBead() {
		System.out.println("BeSin.VonatBead()");
		
		//Uj mozdony lep a palyara
		System.out.print("BeSin -> ");
		Terepasztal.getInstance().AddMozdony(new Mozdony());
	}
	
	@Override
	public SinElem getKovSinElem(SinElem elozo) {//itt az elozo elvileg null
		System.out.println("BeSin.getKovSinElem()");
		return sinA;//ez jo, mert nincs sinBje a BeSinnek
	}
	public void AddVonat(String string, int parseInt, String string2) {
		// TODO Auto-generated method stub
		
	}
}

//Zotya
