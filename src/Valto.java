
import java.util.ArrayList;

public class Valto extends SinElem {
	private SinElem aktivAg;
    private ArrayList<SinElem> kimenetek = new ArrayList<>();
        
    Valto(String id) 
    {
    	super(id);
    }
    
    @Override
    public void setSinElem(SinElem s, char c){
        if (c == 'a')
        	sinA = s;
        else if (c == 'b') {
        	aktivAg = s;
        	kimenetek.set(0, s);
        }
        else {
        	kimenetek.set(c-'a'-1, s);
        }
    }
    
    /**
     * Beallitja az adott agat a parameterkent kapott SinElemre
     * @param s Melyik SinElemre akarjuk allitani
     */
	public void setAktualisAg(SinElem s) {
            
            if(kimenetek.contains(s)){
                aktivAg = s;
            }else{
                System.out.println("Nincs benne a kimenetek kozott az ag!");
            }
                  
            System.out.println("Valto.setAktualisAg()");
            
            
	}
        
        /**
         * Hozzaadja a parameterkent kapott SinElemet a kimenetekhez
         * @param s Hozzaadni kivant SinElem
         */
        public void addSinElem(SinElem s){
            if(!kimenetek.contains(s)){ //Ellenorzi, hogy benne van-e
               kimenetek.add(s); 
            }else{
                System.out.println("Mar tartalmazzak a kimenetek ezt a SinElemet");
            }
            
             System.out.println("Valto.addSinElem()");
        }
        
	/**
         * Visszaadja a kovetkezo agat a valton.
         * @return Kovetkezo SinElem
         */
	public SinElem kovAg() {
                if(!kimenetek.isEmpty() && kimenetek.contains(aktivAg)){
                    int i = kimenetek.lastIndexOf(aktivAg); //Kell az index, hogy megnezzuk az utolso-e
                    if(i < kimenetek.size()-1){
                        return kimenetek.get(i+1); //Ha nem utolso, akk a kovetkezo
                    }else{
                        return kimenetek.get(0); //Ha utolso, akkor az elso
                    }
                }
                System.out.println("Valto.kovAg()");
		return null; //Ha nincs kimenet vagy valami mas baj van
	}
	
        /**
         * Valtja a valtot, a ranyomast szimulalja.
         */
	public void onInput() {
                
                System.out.println("Valto.onInput()");
				System.out.print("Valto -> ");
                SinElem s = kovAg(); //Kovetkezore valtunk
                valt(s);
            
	}
	
        /**
         * Valtja a valtot, ha nem foglalt.
         * @param s Melyik SinElemre valtson 
         */
        public void valt(SinElem s){
            
            if(getSzerelvenyek() < 1){ //Ha nem foglalt
                aktivAg = s;
            }else{
                System.out.println("Ugy probaljuk valtani, hogy vannak rajta!");
            }
            
           System.out.println("Valto.valt()");
        }
	
        /**
         * Atvaltja a valtot, ha nem a bemenetrol jon, noveli a szerelvenyek szamat.
         * @param m Mozdony, ami ralep
         * @param s A SinElem, amirol erkezik a Mozdony
         */
	public void leptet(Mozdony m, SinElem s) {
		System.out.println("Valto.leptet()");
                if(!sinA.equals(s)){
                    setAktualisAg(s);
                }
                szerelvenyek++;
                
	}

        /**
         * Visszater a kovetkezo SinElemmel, attol fuggoen, hogy honnan jon a vonat
         * @param elozo Az elozo SinElem
         * @return A kovetkezo SinElem
         */
	@Override
	public SinElem getKovSinElem(SinElem elozo) {
                 System.out.println("Valto.getKovSinElem()");
				 
		if(sinA.equals(elozo)){ //Ha az elozo a SinA volt
			return aktivAg;
		}else{                     //Ha nem
			return sinA;
		}
		
	}
}
