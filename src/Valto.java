
import java.util.ArrayList;

public class Valto extends SinElem {
	private SinElem aktivAg;
        private ArrayList<SinElem> kimenetek = new ArrayList<>();
        
	public void setAktualisAg(SinElem s) {
            
            System.out.println("Valto osztály: meghívott függvény - setAktualisAg()");
            
            
	}
        
        public void addSinElem(SinElem s){
            kimenetek.add(s);
             System.out.println("Valto osztály: meghívott függvény - addSinElem()");
        }
        
	
	public SinElem kovAg() {
            
                System.out.println("Valto osztály: meghívott függvény - kovAg()");
		return aktivAg;
	}
	
	public void onInput() {
                
                System.out.println("Valto osztály: meghívott függvény - onInput()");
                SinElem s = kovAg();
                valt(s);
            
	}
	
        public void valt(SinElem s){
            
           System.out.println("Valto osztály: meghívott függvény - valt()");
           if(getSzerelvenyek() == 0)
           setAktualisAg(kovAg());
            
            
            
        }
        
	public void leptet(Mozdony m, SinElem s) {
            if(sinA != s){
                valt(s);
            }
	}
	
	public Boolean getUtkozes() {
                 System.out.println("Valto osztály: meghívott függvény - getUtkozes()");
		return getSzerelvenyek() > 1;
	}

	@Override
	public SinElem getKovSinElem() {
		// TODO Auto-generated method stub
                 System.out.println("Valto osztály: meghívott függvény - getKovSinElem()");
		return aktivAg;
	}
}
