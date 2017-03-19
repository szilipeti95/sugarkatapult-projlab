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
		
		System.out.println("Van mar masik alagutszaj: (igen/nem)");
		Scanner reader = new Scanner(System.in);
		String valasz = reader.next();
		switch(valasz){
		case "igen": szajA = a; break;
		case "nem": 
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
		//if(allapot == AlagutAllapot.VanAlagut){
			System.out.println("Van alagut? (igen/nem)");
			Scanner reader = new Scanner(System.in);
			if(reader.next().equals("igen")) {
				Terepasztal.getInstance().RemoveSinElem(a.getKovSinElem(null));
			}
		}
}
