/**
 * absztrakt osztaly
 * a kulonbozo sin fajtak szuloje
 */
public abstract class SinElem{
    protected SinElem sinA;//csatlakozo ag
    protected int x;//koordinatak a palyan
    protected int y;
    protected int szerelvenyek;//hanyan vannak rajta
    protected String id;//azonosito
    
    /**
     * setter a koordinatakhoz
     * legyen a palyan (0-14) hogy latszodjon (pl besinnel, alagutbeli sinnel nem)
     * @param xin X-koordinata
     * @param yin Y-koordinata
     */
    public void setCoords(int xin, int yin)
    {
    	x=xin;
    	y=yin;
    }
    
    /**
     * getter az X-koordinatahoz
     * @return X-koordinata
     */
    public int getX()
    {
    	return x;
    }
    
    /**
     * getter az Y-koordinatahoz
     * @return Y-koordinata
     */
    public int getY()
    {
    	return y;
    }
    
    /**
     * parameterben kapott agat visszaadja
     * absztrakt, minden gyereknek mas (pl valto, keresztsin tobb aga van mint tobbinek)
     * @param a melyik ag, pl a,b,c,d
     * @return parameterben megadott ag
     */
    public abstract SinElem getAg(char a);
        
    /**
     * konstruktor
     * @param id Azonosito
     */
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

    /**
     * Beallitja a SinElem attributumot a megfelelo attribútumra
     * @param s Beallitando attributum
     * @param c 'a' - SinA
     */
    
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
    
    public abstract void rajzol(Rajzolo r);
    
    
    /**
     * getter az azonositohoz
     * @return azonosito
     */
    String GetId()
    {
    	return id;
    }
	
	
	/**
	 * Visszaadja a sinElem-en tartozkodo szerelvenyek szamat
	 * @return a szerelvenyek szama
	 */
	public int GetSzerelvenyek()
	{
		return szerelvenyek;
	}
}
