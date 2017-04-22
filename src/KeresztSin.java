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
	
	/**
	 * Attributumok lekerdezesere
	 * @param attr Melyik attributumot akarjuk lekerdezni. ha null, akkor mindet
	 */
	@Override
	public void GetInfo(String attr) {
		super.GetInfo(attr);//sinelem os dolgait kiirjuk eloszor
		if (attr == null)
		{//nincs megadva hogy melyik attributumot keri, akkor mindet adjuk
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
