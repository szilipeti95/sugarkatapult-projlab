public class Sin extends SinElem {
	private SinElem sinB;
	private boolean AlagutSin;
	
	public Boolean getUtkozes() {
                System.out.println("Sin.getUtkozes() ");
		return szerelvenyek > 1;
	}
        
        public void setSinElem(SinElem s){
            System.out.println("Sin.setSinElem() ");
            sinB = s;
        }
        
		
		
		
	@Override
	public SinElem getKovSinElem(SinElem elozo) {
		// TODO Auto-generated method stub
                System.out.println("Sin.getKovSinElem() ");
		if(sinA == elozo){
			return new Sin();
		}else{
			return new Sin();
		}
		
	}
}
