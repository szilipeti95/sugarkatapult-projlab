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
		if(allapot == AlagutAllapot.NincsAlagutSzaj){
			szajA = a;
		}
		else{
			if(szajA == null){
				szajA = a;
			}
			else{
				szajB = a;
			}
			//TODO
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
		if(allapot == AlagutAllapot.VanAlagut){
			//TODO
		}
	}
}
