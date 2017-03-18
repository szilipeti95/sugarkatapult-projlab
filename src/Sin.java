public class Sin extends SinElem {
	private SinElem sinB;
	public Boolean getUtkozes() {
                System.out.println("Sin osztály: meghívott függvény - getUtkozes() ");
		return getSzerelvenyek() > 0;
	}
        
        public void setSinElem(SinElem s){
            System.out.println("Sin osztály: meghívott függvény - setSinElem() ");
            sinB = s;
        }
        
	@Override
	public SinElem getKovSinElem() {
		// TODO Auto-generated method stub
                System.out.println("Sin osztály: meghívott függvény - getKovSinElem() ");
		return sinB;
	}
}
