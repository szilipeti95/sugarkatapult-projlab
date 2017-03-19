
import java.util.ArrayList;

public class Valto extends SinElem {
	private SinElem aktivAg;
        private ArrayList<SinElem> kimenetek = new ArrayList<>();
        
	public void setAktualisAg(SinElem s) {
            
            System.out.println("Valto.setAktualisAg()");
            
            
	}
        
        public void addSinElem(SinElem s){
            kimenetek.add(s);
             System.out.println("Valto.addSinElem()");
        }
        
	
	public SinElem kovAg() {
            
                System.out.println("Valto.kovAg()");
		return new Sin();
	}
	
	public void onInput() {
                
                System.out.println("Valto.onInput()");
                SinElem s = kovAg();
                valt(s);
            
	}
	
        public void valt(SinElem s){
            
           System.out.println("Valto.valt()");
           if(getSzerelvenyek() == 0)
           setAktualisAg(kovAg());
            
            
            
        }
		
	public void raLep(Kocsi k) {
            System.out.println("Valto.raLep() ");
            szerelvenyek++;
	}
        
	public void leptet(Mozdony m, SinElem s) {
		System.out.println("Valto.leptet()");
            if(sinA != s){
                valt(s);
            }
	}
	
	public Boolean getUtkozes() {
                 System.out.println("Valto.getUtkozes()");
		return szerelvenyek > 1;
	}

	@Override
	public SinElem getKovSinElem(SinElem elozo) {
                 System.out.println("Valto.getKovSinElem()");
				 
		if(aktivAg == elozo){
			return new Sin();
		}else{
			return new Sin();
		}
		
	}
}
