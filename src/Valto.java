import java.util.ArrayList;

/**
 * @author Sillye (+Zotya hibajavitas,formazas)
 * Valto sin
 * tobb aggal, valasztható aktiv aggal
 */
public class Valto extends SinElem {
	private SinElem aktivAg;
    private ArrayList<SinElem> kimenetek = new ArrayList<>();
    
    /**
     * kostruktor
     * @param id Azonosito
     */
    Valto(String id) 
    {
    	super(id);
    }
    
    /**
     * Beallitja az adott agat a parameterkent atadott SinElemre
     * @param s Beallitando SinElem
     * @param c Melyik agra
     */
    @Override
    public void setSinElem(SinElem s, char c) {
        if (c == 'a')
        	sinA = s;
        else if (c == 'b') {
        	aktivAg = s;
        	if(kimenetek.size()>0)
        		kimenetek.set(0, s);
        	else
        		kimenetek.add(s);//ez azert kell, mert ez hivodik meg a palya feltoltesekor, es ez meg akkor ures
        }
        else {
        	if(kimenetek.size()>c-'a'-1)
        		kimenetek.set(c-'a'-1, s);
        	else
        		kimenetek.add(s);//szinten^
        }
    }
    
    /**
     * Beallitja az adott agat a parameterkent kapott SinElemre
     * @param s Melyik SinElemre akarjuk allitani
     */
	public void setAktualisAg(SinElem s) {
        if(kimenetek.contains(s)){
            aktivAg = s;
        }
        //else: nincs ilyen kimeno ag!
	}
        
    /**
     * Hozzaadja a parameterkent kapott SinElemet a kimenetekhez
     * @param s Hozzaadni kivant SinElem
     */
    public void addSinElem(SinElem s) {
        if(!kimenetek.contains(s)){ //Ellenorzi, hogy benne van-e
           kimenetek.add(s); 
        }
        //else: mar benne van
    }
        
	/**
     * Visszaadja a kovetkezo agat a valton.
     * @return Kovetkezo SinElem
     */
	public SinElem kovAg() {
        if(!kimenetek.isEmpty() && kimenetek.contains(aktivAg)){
            int i = kimenetek.lastIndexOf(aktivAg); //Kell az index, hogy megnezzuk az utolso-e
            if(i < kimenetek.size()-1){
                return kimenetek.get(i+1); //Ha nem utolso, akk a kovetkezo
            }else{
                return kimenetek.get(0); //Ha utolso, akkor az elso
            }
        }
		return null; //Ha nincs kimenet vagy valami mas baj van
	}
	
    /**
     * Valtja a valtot, a ranyomast szimulalja.
     */
	public void onInput() {
        SinElem s = kovAg(); //Kovetkezore valtunk
        valt(s);
	}
	
    /**
     * Valtja a valtot, ha nem foglalt.
     * @param s Melyik SinElemre valtson 
     */
    public void valt(SinElem s) {
        if(getSzerelvenyek() < 1) { //Ha nem foglalt
            aktivAg = s;
    		System.out.println(id+" valto atvaltott a "+ (char)(kimenetek.indexOf(s)+'a'+1) +" agba");//egyik teszt miatt ezt kiirjuk
        }
        //else: van rajta vonat, igy nem lehet valtani
    }
	
    /**
     * Atvaltja a valtot, ha nem a bemenetrol jon, noveli a szerelvenyek szamat.
     * @param m Mozdony, ami ralep
     * @param s A SinElem, amirol erkezik a Mozdony
     */
	public void leptet(Mozdony m, SinElem s) {
        if(!sinA.equals(s)){
            setAktualisAg(s);
        }
        szerelvenyek++;
	}

    /**
     * Visszater a kovetkezo SinElemmel, attol fuggoen, hogy honnan jon a vonat
     * @param elozo Az elozo SinElem
     * @return A kovetkezo SinElem
     */
	@Override
	public SinElem getKovSinElem(SinElem elozo) {
		if(sinA.equals(elozo)) { //Ha az elozo a SinA volt
			return aktivAg;
		}
		else { //Ha nem
			return sinA;
		}
	}
	
	/**
     * Kiirja a szukseges lekerdezett infokat a Valtorol, ha van attributum, akkor csak azt.
     * @param attr A lekerdezett attributum
     */
	@Override
	public void GetInfo(String attr) {
        //Ososztaly GetInfoja
		super.GetInfo(attr);
        //Ha nem kertuk le kulon, hogy melyik attributum:
		if (attr == null)
		{
			System.out.println("aktivag: " + ((aktivAg!=null)? aktivAg.id : "null"));
		}
        //Ha csak egy attributum erdekel:
		else
		{
			switch (attr) {
            //Aktivag attributum
			case "aktivag":
				System.out.println(id + ":");
				System.out.println("aktivag: " + ((aktivAg!=null)? aktivAg.id : "null"));
				break;
			default:
				break;
			}
		}
	}
	
	@Override
	public void rajzol(Rajzolo r) {
		r.rajzol(this);
	}
}
