/**
 * @author Zotya
 * Allomas osztaly, SinElem leszarmazottja.
 * utaskocsinak szol a fel-le szallasrol
 */
public class Allomas extends SinElem {
	private SinElem sinB;//masik ag
	private Szin szin;//allomas szine
	private boolean ures;//ures e vagy vannak felszallni akaro utasok
	
	/**
	 * konstruktor
	 * @param id azonositoja
	 * @param szin Szine: p,k,s,z
	 */
	Allomas(String id, String szin, int ures) 
    {
    	super(id);//szulo (sinElem) konstruktora
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
    	this.ures = (ures == 0);//int -> boolean
    	
    }
	
	/**
	 * setter a sinelemeihez
	 * @param s amit rakapcsolunk
	 * @param c amelyik agara kapcsoljuk: a, b
	 */
	@Override
    public void setSinElem(SinElem s, char c){
        if (c == 'a')
        	sinA = s;
        else if (c == 'b')
        	sinB = s;
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
     * setter az uresre
     * @param u Ures-e
     */
    public boolean getUres() {
        return ures;
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
	
	/**
	 * kirajzoltatja magat a parameterben kapott rajzoloval
	 * @param r A rajzolo ami kirajzolja
	 */
	@Override
	public void rajzol(Rajzolo r) {
		r.rajzol(this);
	}
	
	/**
	 * againak lekerese
	 * @param a melyiket. a, b
	 * @return a parameterben megadott sinElem. null, ha hibas parameter
	 */
	public SinElem getAg(char a)
	{
        if (a == 'a')
        	return sinA;
        else if (a == 'b')
        	return sinB;
        return null;
	}
}
