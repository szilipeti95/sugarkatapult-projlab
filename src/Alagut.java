import java.util.ArrayList;

/**
 * Alagut megepultsegenek, es az alagut szajak illetve a koztuk meno SinElemek szamontartasa.
 */
public class Alagut {
	private AlagutSzaj szajA;
	private AlagutSzaj szajB;
	private AlagutAllapot allapot;
	private ArrayList<SinElem> alagutSinek;
	
	
	
	/**
	 * Visszater az Alagut megepultsegenek allapotaval
	 * @return Alagut megepultsegenek allapota
	 */
	public AlagutAllapot getAllapot(){
		return allapot;
	}
	
	/**
	 * A paraméterben kapott AlagutSzaj-at hozzáadja az Alaguthoz
	 * @param A hozzaadando AlagutSzaj
	 */
	public void AddAlagutSzaj(AlagutSzaj a) {
		if(allapot.equals(AlagutAllapot.NincsAlagutSzaj)){
			szajA = a;
		}
		else{
			if(szajA == null){
				szajA = a;
			}
			else{
				szajB = a;
			}
			//TODO: add more
			SinElem s = new Sin("as1");
			//feleptitjuk az alagutat a sinekbol
			Terepasztal.getInstance().AddSinElem(s);
			alagutSinek.add(s);
		}
	}
	
	/**
	 * A paraméterben kapott AlagutSzaj-at eltávolítja az Alagutból
	 * @param Az eltavolitando AlagutSzaj  
	 */
	public void RemoveAlagutSzaj(AlagutSzaj a) {
		//Valamelyik alagutSzaj megsemmisult. Le kell bontani az alagutat.
		if(allapot == AlagutAllapot.VanAlagut){
			szajA = null;
			szajB = null;
			for(SinElem s : alagutSinek){
				Terepasztal.getInstance().RemoveSinElem(s);
			}
			alagutSinek.clear();
		}
		else if(szajA == a){
			szajA = null;
		}
		else{
			szajB = null;
		}
	}
}
