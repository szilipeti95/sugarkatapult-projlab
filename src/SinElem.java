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
        return szerelvenyek;
    }
        
    /**
     * Ellep a szerelveny innen, csokkenti a szerelvenyek szamat
     */    
	public void elLep() {
        szerelvenyek--;
        if(szerelvenyek <0)
        	szerelvenyek = 0;
	}
    /**
     * Ralep egy Utaskocsi a SinElemre
     * @param utaskocsi Vagon ami ralepett
     */
	public void raLep(UtasKocsi utaskocsi) {
            szerelvenyek++;
	}
         /**
     * Ralep egy Szeneskocsi a SinElemre
     * @param szeneskocsi Szeneskocsi ami ralepett
     */
	public void raLep(SzenesKocsi szeneskocsi) {
            szerelvenyek++;
	}
    /**
     * Input tortent a SinElemen.
     */

    public void onInput() {
    }

    /**
     * Megmondja, hogy tobb szerelveny van-e a SinElemen egyszerre
     * @return true - tobb mint 1 szerelveny van rajta, false - nincs tobb mint 1
     */
    public boolean getUtkozes() {
            return szerelvenyek > 1;
    }

    /**
     * Lekeri a kovetkezo SinElemet, Absztrakt
     * @param elozo Elozo SinElem
     * @return Kovetkezo SinElem
     */
    public abstract SinElem getKovSinElem(SinElem elozo);
    
    
    String GetId()
    {
    	return id;
    }
    
	public void GetInfo(String attr) {
		if (attr == null)
		{
			System.out.println(id + ":");
			System.out.println("sina: " + sinA.id);
			System.out.println("szerelvenyek: " + szerelvenyek);
			System.out.println("lathato: " + lathato);
		}
		else
		{
			switch (attr) {
			case "sina":
				System.out.println(id + ":");
				System.out.println("sina: " + sinA.id);
				break;
			case "szerelvenyek":
				System.out.println(id + ":");
				System.out.println("szerelvenyek: " + szerelvenyek);
				break;
			case "lathato":
				System.out.println(id + ":");
				System.out.println("lathato: " + lathato);
				break;
			default:
				break;
			}
		}
		
	}
}
