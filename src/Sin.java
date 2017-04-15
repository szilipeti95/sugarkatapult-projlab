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
		if(sinA.equals(elozo)){ //Ha sinA-bol jott
			return sinB;
		}else{                  //Ha sinB-bol jott
			return sinA;
		}
	}
	
	@Override
	public void GetInfo(String attr) {
		super.GetInfo(attr);
		if (attr == null)
		{
			System.out.println("sinb: " + sinB.id);
			System.out.println("alagutsin: " + AlagutSin);
		}
		else
		{
			switch (attr) {
			case "sinb":
				System.out.println(id + ":");
				System.out.println("sinb: " + sinB.id);
				break;
			case "alagutsin":
				System.out.println(id + ":");
				System.out.println("alagutsin: " + AlagutSin);
				break;
			default:
				break;
			}
		}
		
	}
}
