public abstract class SinElem {
	protected SinElem sinA;
	protected int x;
	protected int y;
	protected int szerelvenyek;
    protected boolean lathato;    
        
    
    //Tovabblepteti a sinen a vonatokat, feluldefinialjak a leszarmazottak
	public void leptet(Mozdony m, SinElem s) {
           System.out.println("SinElem.leptet() ");
            
	}
    
    //visszater a szerelvenyek szamaval
    public int getSzerelvenyek() {
        System.out.println("SinElem.getSzerelvenyek() ");
        return szerelvenyek;
    }
        
        
    //Ellep a szerelveny innen, csokkenti a szerelvenyek szamat
	public void elLep() {
            System.out.println("SinElem.elLep() ");
            if(szerelvenyek>0){
                szerelvenyek--;
            }
	}
    //Ralep egy kocsi
	public void raLep(Kocsi k) {
            System.out.println("SinElem.raLep() ");
            szerelvenyek++;
	}
	
    //Input eseten hivodik meg
	public void onInput() {
            System.out.println("SinElem.onInput() ");
	}
	
    //Visszater azzal, hogy tobb szerelveny van-e az adott sinen
	public boolean getUtkozes() {
                System.out.println("SinElem.getUtkozes() ");
		return szerelvenyek > 1;
	}
	
     //Absztrakt metodus
	public abstract SinElem getKovSinElem(SinElem elozo);
}
