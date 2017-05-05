public class Sin extends SinElem {
	private SinElem sinB;
	private boolean AlagutSin;
	private boolean lathato=true;
	
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
	
	@Override
	public void rajzol(Rajzolo r) {
		r.rajzol(this);
	}
	
	
	public SinElem getAg(char a)
	{
        if (a == 'a')
        	return sinA;
        else if (a == 'b')
        	return sinB;
        return null;
	}
	
    public void setLathato(boolean l)
    {
    	lathato=l;
    }
    
    public boolean getLathato()
    {
    	return lathato;
    }
}
