import java.util.ArrayList;

/**
 * Alagut megepultsegenek, es az alagut szajak illetve a koztuk meno SinElemek szamontartasa.
 */
public class Alagut {
	/**
	 * Alagut egyik szaja
	 */
	private AlagutSzaj szajA;
	/**
	 * Alagut masik szaja
	 */
	private AlagutSzaj szajB;
	/**
	 * Alagut megepultsegenek allapota
	 */
	private AlagutAllapot allapot = AlagutAllapot.NincsAlagutSzaj;
	/**
	 * Az alagut belsejeben levo sinek tarolasa
	 */
	private ArrayList<SinElem> alagutSinek = new ArrayList<>();
	
	/**
	 * Visszater az Alagut megepultsegenek allapotaval
	 * @return megepultsegenek allapota
	 */
	public AlagutAllapot getAllapot(){
		return allapot;
	}
	
	/**
	 * A parameterben kapott AlagutSzaj-at hozzaadja az Alaguthoz
	 * @param A hozzaadando AlagutSzaj
	 */
	public void AddAlagutSzaj(AlagutSzaj a) {
		if(allapot.equals(AlagutAllapot.NincsAlagutSzaj)){ 
			//ha meg nincs megepult alagutszaj
			szajA = a;
			allapot = AlagutAllapot.EgyAlagutSzaj;
		}
		else if (allapot.equals(AlagutAllapot.EgyAlagutSzaj)){ 
			//ha egy alagutszaj van megepulve
			if(szajA == null){
				szajA = a;				
			}
			else{
				szajB = a;
			}
			allapot = AlagutAllapot.VanAlagut;
			int length = Math.abs(szajA.getX()-szajB.getX())+Math.abs(szajA.getY()-szajB.getY());
			for (int i = 0; i < length; i++) {
				//letrehozzuk a sineket es berakjuk az alagutba
				Sin s = new Sin(("as" + (i+1)));
				s.setLathato(false);
				alagutSinek.add(s);
			}
			for(int i = 0; i < length; i++){
				//sineket osszekotogetjuk egymassal
				if(i == 0){
					//az elso sint be kell kotni az alagutSzajA-ba
					alagutSinek.get(i).setSinElem(szajA, 'a');
					alagutSinek.get(i).setSinElem(alagutSinek.get(i+1), 'b');
					szajA.setSinElem(alagutSinek.get(i), 'b');
				}
				else if(i == length-1){
					//az utolso sint be kell kotni az alagutSzajB-be
					alagutSinek.get(i).setSinElem(szajB, 'b');
					alagutSinek.get(i).setSinElem(alagutSinek.get(i-1), 'a');
					szajB.setSinElem(alagutSinek.get(i), 'b');
				}
				else{
					alagutSinek.get(i).setSinElem(alagutSinek.get(i-1), 'a');
					alagutSinek.get(i).setSinElem(alagutSinek.get(i+1), 'b');
				}
			}
			for(SinElem s : alagutSinek){
				//alagutsinek hozzaadasa a terepasztalhoz
				Terepasztal.getInstance().AddSinElem(s);
			}
		}
	}
	
	/**
	 * A parameterben kapott AlagutSzaj-at eltavolitja az Alagutbol
	 * @param Az eltavolitando AlagutSzaj  
	 */
	public void RemoveAlagutSzaj(AlagutSzaj a) {
		//Valamelyik alagutSzaj megsemmisult. Le kell bontani az alagutat.
		if(allapot == AlagutAllapot.VanAlagut){
			//ha van alagut, akkor toroljuk az alagutsineket
			for(SinElem s : alagutSinek){
				Terepasztal.getInstance().RemoveSinElem(s);
			}
			alagutSinek.clear();
			allapot = AlagutAllapot.EgyAlagutSzaj;
		}
		else{
			//ha csak egy alagut szaj van akkor csak azt toroljuk
			allapot = AlagutAllapot.NincsAlagutSzaj;
                        
		}
		//kinullazzuk a megfelelo alagutSzaj-at
		if(szajA == a){
			szajA = null;
		}
		else{
			szajB = null;
		}
	}
	
	/**
	 * Megadja, hogy az alagutban van-e szerelveny
	 * @return <code>true</code> az alagutban van szerelveny, <code>false</code> az alagutban nincs szerelvény
	 */
	public boolean isFoglalt(){
		for(SinElem sinElem : alagutSinek){
			if (sinElem.GetSzerelvenyek() > 0){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * getter az alagut szajaihoz
	 * @param c a vagy b szaj
	 * @return az alagutszaj, vagy null ha ervenytelen
	 */
	public AlagutSzaj getSzaj(char c)
	{
		if(c=='a')
			return szajA;
		else if(c=='b')
			return szajB;
		return null;
	}
}
