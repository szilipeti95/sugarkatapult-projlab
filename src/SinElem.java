public abstract class SinElem {
    protected SinElem sinA;
    protected int x;
    protected int y;
    protected int szerelvenyek;
    protected boolean lathato;
    protected String id;
        
    
    SinElem(String id) 
    {
    	this.id = id;
    }
    /**
     * Tovabblepteti a sinen a vonatokat, feluldefinialjak a leszarmazottak
     * @param m Mozdony
     * @param s Elozo SinElem
     */
    public void leptet(Mozdony m, SinElem s) {
       System.out.println("SinElem.leptet() ");
       szerelvenyek++;
    }

    
    
    public void setSinElem(SinElem s, char c){
        if (c == 'a')
        	sinA = s;
    }
    
    /**
     * Visszater a szerelvenyek szamaval, amik a SinElemen vannak.
     * @return Szerelvenyek szama
     */
    public int getSzerelvenyek() {
        System.out.println("SinElem.getSzerelvenyek() ");
        return szerelvenyek;
    }
        
    /**
     * Ellep a szerelveny innen, csokkenti a szerelvenyek szamat
     */    
	public void elLep() {
            System.out.println("SinElem.elLep() ");
            if(szerelvenyek>0){
                szerelvenyek--;
            }else{
                System.out.println("Hiba van az Ellepesnel!");
            }
	}
    /**
     * Ralep egy Utaskocsi a SinElemre
     * @param utaskocsi Vagon ami ralepett
     */
	public void raLep(UtasKocsi utaskocsi) {
            System.out.println("SinElem.raLep() ");
            szerelvenyek++;
	}
         /**
     * Ralep egy Szeneskocsi a SinElemre
     * @param szeneskocsi Szeneskocsi ami ralepett
     */
	public void raLep(SzenesKocsi szeneskocsi) {
            System.out.println("SinElem.raLep() ");
            szerelvenyek++;
	}
    /**
     * Input tortent a SinElemen.
     */

    public void onInput() {
        System.out.println("SinElem.onInput() ");
    }

    /**
     * Megmondja, hogy tobb szerelveny van-e a SinElemen egyszerre
     * @return true - tobb mint 1 szerelveny van rajta, false - nincs tobb mint 1
     */
    public boolean getUtkozes() {
            System.out.println("SinElem.getUtkozes() ");
            return szerelvenyek > 1;
    }

    /**
     * Lekeri a kovetkezo SinElemet, Absztrakt
     * @param elozo Elozo SinElem
     * @return Kovetkezo SinElem
     */
    public abstract SinElem getKovSinElem(SinElem elozo);
}
