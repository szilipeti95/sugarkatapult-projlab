import java.util.ArrayList;

/**
 * Alagut megepultsegenek, es az alagut szajak illetve a koztuk meno SinElemek szamontartasa.
 */
public class Alagut {
	private AlagutSzaj szajA;
	private AlagutSzaj szajB;
	private AlagutAllapot allapot = AlagutAllapot.NincsAlagutSzaj;
	private ArrayList<SinElem> alagutSinek = new ArrayList<>();
	private String id;
	
	
	
	Alagut(String id)
	{
		this.id = id;
	}
	
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
			allapot = AlagutAllapot.EgyAlagutSzaj;
		}
		else if (allapot.equals(AlagutAllapot.EgyAlagutSzaj)){
			if(szajA == null){
				szajA = a;				
			}
			else{
				szajB = a;
			}
			allapot = AlagutAllapot.VanAlagut;
			//TODO: add more
			SinElem s = new Sin("as1");
			s.setSinElem(szajA, 'a');
			s.setSinElem(szajB, 'b');
			szajA.setSinElem(s, 'b');
			szajB.setSinElem(s, 'b');
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

			for(SinElem s : alagutSinek){
				Terepasztal.getInstance().RemoveSinElem(s);
			}
			alagutSinek.clear();
			allapot = AlagutAllapot.EgyAlagutSzaj;
		}
		else
		{
			allapot = AlagutAllapot.NincsAlagutSzaj;
		}
		if(szajA == a){
			szajA = null;
		}
		else{
			szajB = null;
		}
	}
	
	public void GetInfo(String attr) {
		if (attr == null)
		{
			System.out.println(id + ":");
			System.out.println("szajA: " + ((szajA== null)? "null" : szajA.id));
			System.out.println("szajB: " + ((szajB== null)? "null" : szajB.id));
			System.out.println("Allapot: " + allapot);
		}
		else
		{
			switch (attr) {
			case "szaja":
				System.out.println(id + ":");
				System.out.println("szajA: " + ((szajA== null)? "null" : szajA.id));
				break;
			case "szajb":
				System.out.println(id + ":");
				System.out.println("szajB: " + ((szajB== null)? "null" : szajB.id));
				break;
			case "allapot":
				System.out.println(id + ":");
				System.out.println("allapot: " + allapot);
				break;
		
			default:
				break;
			}
		}
		
	}
}
