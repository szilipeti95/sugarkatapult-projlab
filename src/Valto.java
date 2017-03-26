
import java.util.ArrayList;
import java.util.Scanner;

public class Valto extends SinElem {
	private SinElem aktivAg;
        private ArrayList<SinElem> kimenetek = new ArrayList<>();
        
        //Beallitja az adott agat a valton
	public void setAktualisAg(SinElem s) {
            
            System.out.println("Valto.setAktualisAg()");
            
            
	}
        
        //Hozzaadja a sinelemet a kimenetekhez
        public void addSinElem(SinElem s){
            kimenetek.add(s);
             System.out.println("Valto.addSinElem()");
        }
        
	//Visszaadja a kovetkezo aggal
	public SinElem kovAg() {
            
                System.out.println("Valto.kovAg()");
		return new Sin();
	}
	
        //Valtja a valtot, akkor hivodik meg, ha ranyomnak a valtora
	public void onInput() {
                
                System.out.println("Valto.onInput()");
				System.out.print("Valto -> ");
                SinElem s = kovAg();
                valt(s);
            
	}
	
        //Valtja a valtot ha nem foglalt
        public void valt(SinElem s){
            
           System.out.println("Valto.valt()");
           
           System.out.println("Foglalt a valto? (igen/nem)");
           Scanner reader = new Scanner(System.in);
           if(reader.next().equals("nem")) {
				System.out.print("Valto -> ");
   				setAktualisAg(kovAg());
           }
        }
		
        //Akkor hivodik meg, ha ralep egy kocsi 
	public void raLep(Kocsi k) {
            System.out.println("Valto.raLep() ");
            szerelvenyek++;
	}
        
        //Atvaltja a valtot, ha nem a bemenetrol jon a vonat
	public void leptet(Mozdony m, SinElem s) {
		System.out.println("Valto.leptet()");
            //if(sinA != s){
			 System.out.println("Kapcsolodo agon jon a vonat? (igen/nem)");
	         Scanner reader = new Scanner(System.in);
	         if(reader.next().equals("nem")) {
				System.out.print("Valto -> ");
                valt(s);
	         }
	}

        //Visszater a kovetkezo sinelemmel, attol fuggoen, hogy honnan jon a vonat
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
