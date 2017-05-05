/**
 * @author Zotya
 * KeresztSin osztaly, SinElem leszarmazottja.
 * Szemben levo agra iranyitja a vonatot
 */
public class KeresztSin extends SinElem {
	private SinElem sinB;//agak
	private SinElem sinC;
	private SinElem sinD;
	
	/**
	 * konstruktor
	 * @param id Azonosito
	 */
	KeresztSin(String id) 
    {
    	super(id);
    }
	
	/**
	 * Agak osszekotesere
	 * @param s SinElem
	 * @param c karakter (a,b,c,d)
	 */
	@Override
    public void setSinElem(SinElem s, char c) {
        if (c == 'a')
        	sinA = s;
        else if (c == 'b')
        	sinB = s;
        else if (c == 'c')
        	sinC = s;
        else if (c == 'd')
        	sinD = s;
    }
    
    /**
     * visszaadja a szemben levo agat
     * @param Az elozo sinElem, ahonnan jon a vonat
     * @return Kovetkezo sinElem, amerre menjen a vonat
     */
	@Override
	public SinElem getKovSinElem(SinElem elozo) {
		if(elozo==sinA)
			return sinC;
		if(elozo==sinC)
			return sinA;
		if(elozo==sinB)
			return sinD;
		if(elozo==sinD)
			return sinB;
		return sinA;//ez a default, ha rossz az elozo, de elv ilyen ne legyen
	}
	
	@Override
	public void rajzol(Rajzolo r) {
		r.rajzol(this);
	}
}
