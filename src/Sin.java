import javax.swing.JComponent;

public class Sin extends SinElem {
	private SinElem sinB;
	private boolean AlagutSin;
	
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
         * Kiirja a szukseges lekerdezett infokat a Sinrol ha van attributum, akkor csak azt.
         * @param attr A lekerdezett attributum
         */
        
	@Override
	public void GetInfo(String attr) {
                //Ososztaly GetInfoja
		super.GetInfo(attr);
                //Ha nincs kulon lekert attributum
		if (attr == null)
		{
			System.out.println("sinb: " + (sinB!=null?sinB.id : "null"));
			System.out.println("alagutsin: " + AlagutSin);
		}
                //Ha van kulon lekert attributum
		else
		{
			switch (attr) {
                        //Lekert attributum a sinB
			case "sinb":
				System.out.println(id + ":");
				System.out.println("sinb: " + (sinB!=null?sinB.id : "null"));
				break;
                        //Lekert attributum az alagutSin
			case "alagutsin":
				System.out.println(id + ":");
				System.out.println("alagutsin: " + AlagutSin);
				break;
			default:
				break;
			}
		}
		
	}
	
	@Override
	public void rajzol(Rajzolo r) {
		r.rajzol(this);
	}
}
