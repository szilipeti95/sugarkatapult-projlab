public class Sin extends SinElem {
	private SinElem sinB;
	private boolean AlagutSin;
	
        
        //Visszater azzal, hogy tobb mint egy szerelveny van-e a Sinen
	public Boolean getUtkozes() {
                System.out.println("Sin.getUtkozes() ");
		return szerelvenyek > 1;
	}
        
        //Beallitja a masik SinElemet
        public void setSinElem(SinElem s){
            System.out.println("Sin.setSinElem() ");
            sinB = s;
        }
        
		
		
        //Visszater a kovetkezo SinElemmel, attol fuggoen, merrol jon a vonat
	@Override
	public SinElem getKovSinElem(SinElem elozo) {
                System.out.println("Sin.getKovSinElem() ");
		if(sinA == elozo){
			return new Sin();
		}else{
			return new Sin();
		}
		
	}
}
