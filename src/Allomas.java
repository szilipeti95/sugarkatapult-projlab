/**
 * @author Zotya
 * Allomas osztaly, SinElem leszarmazottja.
 * utaskocsinak szol a fel-le szallasrol
 */
public class Allomas extends SinElem {
	private SinElem sinB;//masik ag
	private Szin szin;//allomas szine
	private boolean ures;//ures e vagy vannak felszallni akaro utasok
	
	
	Allomas(String id, String szin, int ures) 
    {
    	super(id);
    	switch (szin) {
		case "p":
			this.szin = Szin.PIROS;
			break;
		case "k":
			this.szin = Szin.KEK;
			break;
		case "s":
			this.szin = Szin.SARGA;
			break;
		case "z":
			this.szin = Szin.ZOLD;
			break;
		default:
			this.szin = Szin.PIROS;
			break;
		}
    	this.ures = (ures == 0);
    	
    }
    /**
     * setter SinB-re
     * @param s A beallitando SinElem
     */
    public void setSinElemB(SinElem s) {
        sinB = s;
    }
    
    /**
     * setter a szinre
     * @param s Mi legyen a szine
     */
    public void setSzin(Szin s) {
        szin = s;
    }
    
	/**
	 * getter az allomas szinehez. Vagonnak kell, hogy eldontse a leszallitast
	 * @return Az Allomas szine
	 */
	public Szin getSzin() {
		return szin;
	}
    
    /**
     * setter az uresre
     * @param u Ures-e
     */
    public void setUres(boolean u) {
        ures = u;
    }
    
    
    /**
     * kocsirol le-fel szallas
     * @param k Kocsi ami ralepett
     */
	@Override
	public void raLep(UtasKocsi k) {
        szerelvenyek++;//szamolja hogy raerkezett vmi, ezt az ososztaly is csinalja
        k.leszallit(this);//szerelvenynek szol hogy allomasra ert, szallitson le ha akar
        
        if(k.GetUres() && !ures && k.GetSzin()==szin)
        {//ha ures a kocsi es tele az allomas es szinuk azonos
        	k.felszall();//akkor felszallnak az utasok
        	ures=true;//es az allomas kiurul
        }
	}
	
    /**
     * Lekeri a kovetkezo SinElemet
     * @param elozo Elozo SinElem
     * @return Kovetkezo SinElem
     */
	@Override
	public SinElem getKovSinElem(SinElem elozo) {
		if(elozo==sinA)
			return sinB;
		return sinA;//default, elvileg itt elozo==sinB
	}
}
