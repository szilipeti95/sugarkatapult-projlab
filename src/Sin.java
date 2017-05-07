/**
 * normal sin, ket kapcsolodo aggal
 * SinElem leszarmazottja
 * lehet egyenes, kanyar, alagutbeli..
 */
public class Sin extends SinElem {
	private SinElem sinB;
	private boolean lathato=true;
	
	/**
	 * konstruktor
	 * @param id Azonosito
	 */
	Sin(String id) 
    {
    	super(id);
    }
	
    /**
     * Beallitja a SinElemet a megfelelo helyre
     * @param s Melyik SinElem
     * @param c Melyik helyre allitsa be:
     *          a: SinA, b: SinB
     */    
	@Override
	public void setSinElem(SinElem s, char c){
	    if (c == 'a')
	    	sinA = s;
	    else if (c == 'b')
	    	sinB = s;
	}
    	
    /**
     * Visszater a kovetkezo SinElemmel, attol fuggoen, merrol jon a vonat
     * @param elozo Az elozo SinElem
     * @return A kovetkezo SinElem
     */	
	@Override
	public SinElem getKovSinElem(SinElem elozo) {
		if(sinA.equals(elozo)){ //Ha sinA-bol jott
			return sinB;
		}else{                  //Ha sinB-bol jott
			return sinA;
		}
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
	
	/**
	 * setter a lathatosaghoz
	 * pl. alagutban van akkor nem lathato
	 * @param l legyen-e lathato a sin
	 */
    public void setLathato(boolean l)
    {
    	lathato=l;
    }
    
    /**
     * getter a lathatosaghoz
     * @return lathato-e a sin
     */
    public boolean getLathato()
    {
    	return lathato;
    }
}
