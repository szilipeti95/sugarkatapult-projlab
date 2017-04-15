/**
 * @author Zotya
 * KeresztSin osztaly, SinElem leszarmazottja.
 * Szemben levo agra iranyitja a vonatot
 */
public class KeresztSin extends SinElem {
	private SinElem sinB;
	private SinElem sinC;
	private SinElem sinD;
	
	
	KeresztSin(String id) 
    {
    	super(id);
    }
	
	
	@Override
    public void setSinElem(SinElem s, char c){
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
	
	
	
	
	@Override
	public void GetInfo(String attr) {
		super.GetInfo(attr);
		if (attr == null)
		{
			System.out.println("sinb: " + sinB.id);
			System.out.println("sinc: " + sinC.id);
			System.out.println("sind: " + sinD.id);
		}
		else
		{
			switch (attr) {
			case "sinb":
				System.out.println(id + ":");
				System.out.println("sinb: " + sinB.id);
				break;
			case "sinc":
				System.out.println(id + ":");
				System.out.println("sinc: " + sinC.id);
				break;
			case "sind":
				System.out.println(id + ":");
				System.out.println("sind: " + sinD.id);
				break;
			
			default:
				break;
			}
		}
		
	}

}
