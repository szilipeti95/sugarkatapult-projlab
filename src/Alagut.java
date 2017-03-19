import java.util.Scanner;

public class Alagut {
	private AlagutSzaj szajA;
	private AlagutSzaj szajB;
	private AlagutAllapot allapot;
	
	public AlagutAllapot getAllapot(){
		System.out.println("Alagut.getAllapot()");
		return allapot;
	}
	
	//A paraméterben kapott AlagutSzaj-at hozzáadja az Alaguthoz
	public void AddAlagutSzaj(AlagutSzaj a) {
		System.out.println("Alagut.AddAlagutSzaj()");
		
		System.out.print("Hany alagutszaj van? (0/1): ");
		Scanner reader = new Scanner(System.in);
		String valasz = reader.next();
		switch(valasz){
		case "0": szajA = a; break;
		case "1": 
			if(szajA == null){
				szajA = a;
			}
			else{
				szajB = a;
			}
			SinElem s = new Sin();
			Terepasztal.getInstance().AddSinElem(s);
			break;
		}
		reader.close();
	}
	
	//A paraméterben kapott AlagutSzaj-at eltávolítja az Alagutból
	public void RemoveAlagutSzaj(AlagutSzaj a) {
		System.out.println("Alagut.RemoveAlagutSzaj()");
		
		if(szajA == a){
			szajA = null;
		}
		else{
			szajB = null;
		}
		if(allapot == AlagutAllapot.VanAlagut){
			Terepasztal.getInstance().RemoveSinElem(a.getKovSinElem(elozo));
		}
	}
}
