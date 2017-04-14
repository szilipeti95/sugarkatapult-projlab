public class Sin extends SinElem {
	private SinElem sinB;
	private boolean AlagutSin;
	
	Sin(String id) 
    {
    	super(id);
    }
	
	
	@Override
    public void setSinElem(SinElem s, char c){
        if (c == 'a')
        	sinA = s;
        else if (c == 'b')
        	sinB = s;
    }
    /**
     * Beallitja a masodik sinElemet
     * @param s A beallitando SinElem
     */
    public void setSinElemB(SinElem s){
        System.out.println("Sin.setSinElem() ");
        sinB = s; //Beallitjuk sinB-t
        
    }
    
	
	/**
     * Visszater a kovetkezo SinElemmel, attol fuggoen, merrol jon a vonat
     * @param elozo Az elozo SinElem
     * @return A kovetkezo SinElem
     */	
	@Override
	public SinElem getKovSinElem(SinElem elozo) {
        System.out.println("Sin.getKovSinElem() ");
		if(sinA.equals(elozo)){ //Ha sinA-bol jott
			return sinB;
		}else{                  //Ha sinB-bol jott
			return sinA;
		}
	}
}
