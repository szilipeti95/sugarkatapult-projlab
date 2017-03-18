public abstract class SinElem {
	protected SinElem sinA;
	private int x;
	private int y;
	private int szerelvenyek;
        
        
	public void leptet(Mozdony m, SinElem s) {
           System.out.println("SinElem osztály: meghívott függvény - leptet() ");
            
	}

    public SinElem(SinElem kov, int xi, int yi){
        sinA = kov;
        x = xi;
        y = yi;
        szerelvenyek = 0;
    }
    
    public SinElem(){
        this(null, 0,0);
    }
        
    public int getX() {
        System.out.println("SinElem osztály: meghívott függvény - getX() ");
        return x;
    }

    public int getY() {
        System.out.println("SinElem osztály: meghívott függvény - getY() ");
        return y;
    }

    public int getSzerelvenyek() {
        System.out.println("SinElem osztály: meghívott függvény - getSzerelvenyek() ");
        return szerelvenyek;
    }
        
        
	
	public void elLep() {
            System.out.println("SinElem osztály: meghívott függvény - elLep() ");
            if(szerelvenyek>0){
                szerelvenyek--;
            }
	}
	
	public void raLep() {
            System.out.println("SinElem osztály: meghívott függvény - raLep() ");
            szerelvenyek++;
	}
	
	public void onInput() {
            System.out.println("SinElem osztály: meghívott függvény - onInput() ");
	}
	
	public Boolean getUtkozes() {
                System.out.println("SinElem osztály: meghívott függvény - getUtkozes() ");
		return szerelvenyek > 1;
	}
	
	public abstract SinElem getKovSinElem();
}
