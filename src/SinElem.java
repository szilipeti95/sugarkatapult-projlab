public abstract class SinElem {
	protected SinElem sinA;
	protected int x;
	protected int y;
	protected int szerelvenyek;
    protected boolean lathato;    
        
	public void leptet(Mozdony m, SinElem s) {
           System.out.println("SinElem.leptet() ");
            
	}
        
    public int getX() {
        System.out.println("SinElem.getX() ");
        return x;
    }

    public int getY() {
        System.out.println("SinElem.getY() ");
        return y;
    }

    public int getSzerelvenyek() {
        System.out.println("SinElem.getSzerelvenyek() ");
        return szerelvenyek;
    }
        
        
	
	public void elLep() {
            System.out.println("SinElem.elLep() ");
            if(szerelvenyek>0){
                szerelvenyek--;
            }
	}
	
	public void raLep(Kocsi k) {
            System.out.println("SinElem.raLep() ");
            szerelvenyek++;
	}
	
	public void onInput() {
            System.out.println("SinElem.onInput() ");
	}
	
	public Boolean getUtkozes() {
                System.out.println("SinElem.getUtkozes() ");
		return szerelvenyek > 1;
	}
	
	public abstract SinElem getKovSinElem(SinElem elozo);
}
