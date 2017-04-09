/**
 * @author Zotya
 * KeresztSin osztaly, SinElem leszarmazottja.
 * Szemben levo agra iranyitja a vonatot
 */
public class KeresztSin extends SinElem {
	private SinElem sinB;
	private SinElem sinC;
	private SinElem sinD;
	
    /**
     * setter SinB-re
     * @param s A beallitando SinElem
     */
    public void setSinElemB(SinElem s) {
        sinB = s;
    }
    
    /**
     * setter SinC-re
     * @param s A beallitando SinElem
     */
    public void setSinElemC(SinElem s) {
        sinC = s;
    }
    
    /**
     * setter SinD-re
     * @param s A beallitando SinElem
     */
    public void setSinElemD(SinElem s) {
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

}
