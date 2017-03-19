public class Sin extends SinElem {
	private SinElem sinB;
	public Boolean getUtkozes() {
                System.out.println("Sin osztály: meghívott függvény - getUtkozes() ");
		return szerelvenyek > 1;
	}
        
        public void setSinElem(SinElem s){
            System.out.println("Sin osztály: meghívott függvény - setSinElem() ");
            sinB = s;
        }
        
	public Sin(SinElem kov, int x, int y, boolean lathato){
		super(kov, x, y, lathato);
	}	
		
	@Override
	public SinElem getKovSinElem(SinElem elozo) {
		// TODO Auto-generated method stub
                System.out.println("Sin osztály: meghívott függvény - getKovSinElem() ");
		if(sinA == elozo){
			return sinB;
		}else{
			return sinA;
		}
		
	}
}
