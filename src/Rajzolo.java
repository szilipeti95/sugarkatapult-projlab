import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * grafikus megjelenesert felelos osztaly
 * kepek tarolasa
 * JPanel leszarmazottja: kirajzolas
 */
public class Rajzolo extends JPanel {
	private static final long serialVersionUID = 1L;//ez kell JPanel miatt..
	
	final int height=15, width=15;//palya felbontasa
	
	Graphics graphics;
	//kepek tarolasa
	private BufferedImage MozdonyKep;
	private BufferedImage KocsiKep;
	private BufferedImage AllomasKep;
	private BufferedImage AlagutSzajKep;
	private BufferedImage SinKep;
	private BufferedImage KeresztSinKep;
	private BufferedImage fuKep;
    private BufferedImage utasKep;
    private BufferedImage robbanasKep;
    
    JButton KilepGomb;//gomb: vissza a menube
    
    /**
     * konstruktor
     * rajta levo gomb initje
     */
    public Rajzolo()
    {
    	KilepGomb = new JButton("Menu");
		this.add(KilepGomb);
		Rajzolo tmp = this;
		KilepGomb.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {//gomb kattintasara visszalepunk a menube
		        Jatek.getInstance().veszt();
		        CardLayout cardl = (CardLayout) tmp.getParent().getLayout();
		        cardl.show(tmp.getParent(), "menu");
			}});
    }
    
    /**
     * kepek beolvasasa
     * assests mappabol
     */
	public void loadImages() {
		try
		{
			SinKep = ImageIO.read(new File("assets\\normalsin.png"));
			AllomasKep = ImageIO.read(new File("assets\\allomas.png"));
			KeresztSinKep = ImageIO.read(new File("assets\\keresztsin.png"));
			AlagutSzajKep = ImageIO.read(new File("assets\\alagutszaj.png"));
			fuKep = ImageIO.read(new File("assets\\fu.png"));
            MozdonyKep = ImageIO.read(new File("assets\\mozdonyok.png"));
            KocsiKep = ImageIO.read(new File("assets\\utasok.png"));
            robbanasKep = ImageIO.read(new File("assets\\kabumm.png"));
            utasKep = ImageIO.read(new File("assets\\MyNameIsSmileyFace.png"));
                        
		}
		catch(IOException e){System.out.println(e.getMessage());}//TODO itt lehetne vmi elegansabb dolgot, pl szolni hogy igy nem nagyon fog mukodni
	}
	
	/**
	 * 1. fu kirajzolasa
	 * 2. terepasztal osszes sinenek kirajzolasa
	 * 3. terepasztal osszes vonatjanak kirajzolasa (a mozdony kirajzoltatja a kocsijait)
	 * 4. megepult alagutszajak kirajzolasa, hogy a vonatokat eltakarja
	 */
	public void rajzol() {
		//1. fu kirajzolasa
		for(int i=0;i<height;i++)
			for(int ii=0;ii<width;ii++)
				graphics.drawImage(fuKep, ii*48, i*48, 48, 48, null);
				
		//2. terepasztal osszes sinenek kirajzolasa
		for(SinElem s : Terepasztal.getInstance().getSinelemek()){
			s.rajzol(this);
		}
		
		//3. terepasztal osszes vonatjanak kirajzolasa (a mozdony kirajzoltatja a kocsijait)
        for(Mozdony m : Terepasztal.getInstance().getMozdonyok()){
            m.rajzol(this);
        }
                
		//4. megepult alagutszajak kirajzolasa, hogy a vonatokat eltakarja
		Alagut al = Terepasztal.getInstance().getAlagut();
		if(al.getSzaj('a')!=null)
			rajzol(al.getSzaj('a'));
		if(al.getSzaj('b')!=null)
			rajzol(al.getSzaj('b'));
		
	}
	
	/**
	 * a panel kirajzolasakor hivodik meg
	 * megszerezzuk a graphics osztalyt, amivel rajzolni tudunk
	 * parent fuggvenyet is meghivjuk
	 */
	@Override
    public void paintComponent(Graphics g) {
		super.paintComponent(g);
		graphics=g;
		rajzol();
    }
	
	/**
	 * Mozdony kirajzolasa
	 * @param m melyik mozdonyt
	 */
	public void rajzol(Mozdony m) {
            int index1 = 0;
            int ferde = 0;
           
            if(m.alagutbanVan()){
                return;
            }
            
            if(m.getSin().getKovSinElem(m.getElozoSin()) != null)
            {
	           if(m.getElozoSin().getY() == m.getSin().getKovSinElem(m.getElozoSin()).getY() && m.getElozoSin().getX() > m.getSin().getKovSinElem(m.getElozoSin()).getX()  ){
	               //balra megy
	               index1 = 3;
	           }else if(m.getElozoSin().getY() == m.getSin().getKovSinElem(m.getElozoSin()).getY() && m.getElozoSin().getX() < m.getSin().getKovSinElem(m.getElozoSin()).getX()  ){
	               //jobbra megy
	               index1 = 1;
	           }else if(m.getElozoSin().getX() == m.getSin().getKovSinElem(m.getElozoSin()).getX() && m.getElozoSin().getY() > m.getSin().getKovSinElem(m.getElozoSin()).getY()  ){
	               //felfele megy
	               index1 = 0;
	           }else if(m.getElozoSin().getX() == m.getSin().getKovSinElem(m.getElozoSin()).getX() && m.getElozoSin().getY() < m.getSin().getKovSinElem(m.getElozoSin()).getY()  ){
	               //lefele megy
	               index1 = 2;
	           }else if(m.getElozoSin().getY() > m.getSin().getKovSinElem(m.getElozoSin()).getY() && m.getElozoSin().getX() > m.getSin().getKovSinElem(m.getElozoSin()).getX()  ){
	               //balra fel
	               index1 = 3;
	               ferde = 1;
	           }else if(m.getElozoSin().getY() < m.getSin().getKovSinElem(m.getElozoSin()).getY() && m.getElozoSin().getX() < m.getSin().getKovSinElem(m.getElozoSin()).getX()  ){
	               //jobbra le
	               index1 = 1;
	               ferde = 1;
	           }else if(m.getElozoSin().getX() > m.getSin().getKovSinElem(m.getElozoSin()).getX() && m.getElozoSin().getY() < m.getSin().getKovSinElem(m.getElozoSin()).getY()  ){
	               //balra le
	               index1 = 2;
	               ferde = 1;
	           }else if(m.getElozoSin().getX() < m.getSin().getKovSinElem(m.getElozoSin()).getX() && m.getElozoSin().getY() > m.getSin().getKovSinElem(m.getElozoSin()).getY()  ){
	               //jobbra fel
	               index1 = 0;
	               ferde = 1;
	           }
            }
           
           
            graphics.drawImage(MozdonyKep, m.getSin().getX()*48, m.getSin().getY()*48,m.getSin().getX()*48+ 48,m.getSin().getY()*48+ 48,48*index1,0+ferde*48,48*index1+48,48+ferde*48,null);
            if(m.getUtkozott()){
                graphics.drawImage(robbanasKep, m.getSin().getX()*48, m.getSin().getY()*48, null);
            }
            
	}
	
	/**
	 * UtasKocsi kirajzolasa
	 * @param u melyik UtasKocsit
	 */
	public void rajzol(UtasKocsi u) {
		int index1 = 0;
		int ferde = 0;
		
		if(u.alagutbanVan()){
		    return;
		}
		
		if(u.getElozoSin().getY() == u.getSin().getKovSinElem(u.getElozoSin()).getY() && u.getElozoSin().getX() > u.getSin().getKovSinElem(u.getElozoSin()).getX()  ){
	       //balra megy
	       index1 = 1;
		}else if(u.getElozoSin().getY() == u.getSin().getKovSinElem(u.getElozoSin()).getY() && u.getElozoSin().getX() < u.getSin().getKovSinElem(u.getElozoSin()).getX()  ){
	       //jobbra megy
	       index1 = 1;
		}else if(u.getElozoSin().getX() == u.getSin().getKovSinElem(u.getElozoSin()).getX() && u.getElozoSin().getY() > u.getSin().getKovSinElem(u.getElozoSin()).getY()  ){
	       //felfele megy
	       index1 = 0;
		}else if(u.getElozoSin().getX() == u.getSin().getKovSinElem(u.getElozoSin()).getX() && u.getElozoSin().getY() < u.getSin().getKovSinElem(u.getElozoSin()).getY()  ){
	       //lefele megy
	       index1 = 0;
		}else if(u.getElozoSin().getY() > u.getSin().getKovSinElem(u.getElozoSin()).getY() && u.getElozoSin().getX() > u.getSin().getKovSinElem(u.getElozoSin()).getX()  ){
	       //balra fel
	       index1 = 3;
		}else if(u.getElozoSin().getY() < u.getSin().getKovSinElem(u.getElozoSin()).getY() && u.getElozoSin().getX() < u.getSin().getKovSinElem(u.getElozoSin()).getX()  ){
	       //jobbra le
	       index1 = 5;
		}else if(u.getElozoSin().getX() > u.getSin().getKovSinElem(u.getElozoSin()).getX() && u.getElozoSin().getY() < u.getSin().getKovSinElem(u.getElozoSin()).getY()  ){
	       //balra le
			index1=4;
		}else if(u.getElozoSin().getX() < u.getSin().getKovSinElem(u.getElozoSin()).getX() && u.getElozoSin().getY() > u.getSin().getKovSinElem(u.getElozoSin()).getY()  ){
	       //jobbra fel
	       index1 = 2;
		}
		
		ferde = u.GetSzin().ordinal();
	   
		graphics.drawImage(KocsiKep, u.getSin().getX()*48, u.getSin().getY()*48,u.getSin().getX()*48+ 48,u.getSin().getY()*48+ 48,48*index1,0+ferde*48,48*index1+48,48+ferde*48,null);
		if(!u.GetUres()){
	       graphics.drawImage(utasKep, u.getSin().getX()*48, u.getSin().getY()*48, null);
		}  
	}
	
	/**
	 * SzenesKocsi kirajzolasa
	 * @param sz melyik SzenesKocsit
	 */
	public void rajzol(SzenesKocsi sz) {
		int index1 = 0;
		
		if(sz.alagutbanVan()){
		    return;
		}
	    
		if(sz.getElozoSin().getY() == sz.getSin().getKovSinElem(sz.getElozoSin()).getY() && sz.getElozoSin().getX() > sz.getSin().getKovSinElem(sz.getElozoSin()).getX()  ){
		   	//balra megy
		   	index1 = 1;
		}else if(sz.getElozoSin().getY() == sz.getSin().getKovSinElem(sz.getElozoSin()).getY() && sz.getElozoSin().getX() < sz.getSin().getKovSinElem(sz.getElozoSin()).getX()  ){
		   	//jobbra megy
		   	index1 = 1;
		}else if(sz.getElozoSin().getX() == sz.getSin().getKovSinElem(sz.getElozoSin()).getX() && sz.getElozoSin().getY() > sz.getSin().getKovSinElem(sz.getElozoSin()).getY()  ){
		   	//felfele megy
		   	index1 = 0;
		}else if(sz.getElozoSin().getX() == sz.getSin().getKovSinElem(sz.getElozoSin()).getX() && sz.getElozoSin().getY() < sz.getSin().getKovSinElem(sz.getElozoSin()).getY()  ){
		   	//lefele megy
		   	index1 = 0;
		}else if(sz.getElozoSin().getY() > sz.getSin().getKovSinElem(sz.getElozoSin()).getY() && sz.getElozoSin().getX() > sz.getSin().getKovSinElem(sz.getElozoSin()).getX()  ){
		   	//balra fel
		   	index1 = 5;
		}else if(sz.getElozoSin().getY() < sz.getSin().getKovSinElem(sz.getElozoSin()).getY() && sz.getElozoSin().getX() < sz.getSin().getKovSinElem(sz.getElozoSin()).getX()  ){
	       	//jobbra le
	       	index1 = 3;
		}else if(sz.getElozoSin().getX() > sz.getSin().getKovSinElem(sz.getElozoSin()).getX() && sz.getElozoSin().getY() < sz.getSin().getKovSinElem(sz.getElozoSin()).getY()  ){
			//balra le
			index1 = 4;
		}else if(sz.getElozoSin().getX() < sz.getSin().getKovSinElem(sz.getElozoSin()).getX() && sz.getElozoSin().getY() > sz.getSin().getKovSinElem(sz.getElozoSin()).getY()  ){
			//jobbra fel
			index1 = 2;  
		}   
		int ferde = 4;
		graphics.drawImage(KocsiKep, sz.getSin().getX()*48, sz.getSin().getY()*48,sz.getSin().getX()*48+ 48,sz.getSin().getY()*48+ 48,48*index1,0+ferde*48,48*index1+48,48+ferde*48,null);
	}
	
	/**
	 * Allomas kirajzolasa
	 * @param a melyik Allomast
	 */
	public void rajzol(Allomas a) {
		int index = 0;
		if(a.getX() == a.getAg('a').getX() && a.getX() == a.getAg('b').getX()){//egyenes
			index = 0;
		}
		else if(a.getY() == a.getAg('a').getY() && a.getY() == a.getAg('b').getY()){
			index = 1;
		}
		
		if(a.getUres())
		{
			index+=2;
		}
		
		int row = a.getSzin().ordinal();
		graphics.drawImage(AllomasKep, a.getX()*48, a.getY()*48,a.getX()*48+ 48,a.getY()*48+ 48,48*index,0+row*48,48*index+48,48+row*48,null);
	}
	
	/**
	 * AlagutSzaj kirajzolasa
	 * @param a melyik AlagutSzajat
	 */
	public void rajzol(AlagutSzaj a) {
		int index = 0;
		if(a.getY() == a.getAg('a').getY()-1){//egyenes
			index = 0;
		}
		else if(a.getX() == a.getAg('a').getX()+1){
			index = 1;
		}
		else if(a.getY() == a.getAg('a').getY()+1){
			index = 2;
		}
		else if(a.getX() == a.getAg('a').getX()-1){
			index = 3;
		}
		
		int row = a.getMegeptive() ? 1 : 0;
		graphics.drawImage(AlagutSzajKep, a.getX()*48, a.getY()*48,a.getX()*48+ 48,a.getY()*48+ 48,48*index,0+row*48,48*index+48,48+row*48,null);
	}
	
	/**
	 * Sin kirajzolasa
	 * @param s melyik Sint
	 */
	public void rajzol(Sin s) {
		if(!s.getLathato())
			return;
		int index = 0;
		if(s.getX() == s.getAg('a').getX() && s.getX() == s.getAg('b').getX()){//egyenes
			index = 0;
		}
		else if(s.getY() == s.getAg('a').getY() && s.getY() == s.getAg('b').getY()){
			index = 1;
		}
		else if((s.getY() == s.getAg('a').getY()-1 && s.getX() == s.getAg('b').getX()-1) ||
				(s.getY() == s.getAg('b').getY()-1 && s.getX() == s.getAg('a').getX()-1)){//kanyar
			index = 2;
		}
		else if((s.getY() == s.getAg('b').getY()-1 && s.getX() == s.getAg('a').getX()+1) ||
				(s.getY() == s.getAg('a').getY()-1 && s.getX() == s.getAg('b').getX()+1)){
			index = 3;
		}
		else if((s.getY() == s.getAg('a').getY()+1 && s.getX() == s.getAg('b').getX()+1) ||
				(s.getY() == s.getAg('b').getY()+1 && s.getX() == s.getAg('a').getX()+1)){
			index = 4;
		}
		else if((s.getY() == s.getAg('a').getY()+1 && s.getX() == s.getAg('b').getX()-1) ||
				(s.getY() == s.getAg('b').getY()+1 && s.getX() == s.getAg('a').getX()-1)){
			index = 5;
		}
		graphics.drawImage(SinKep, s.getX()*48, s.getY()*48,s.getX()*48+ 48,s.getY()*48+ 48,48*index,0,48*index+48,48,null);
	}
	
	/**
	 * KeresztSin kirajzolasa
	 * @param k melyik KeresztSint
	 */
	public void rajzol(KeresztSin k) {
		graphics.drawImage(KeresztSinKep, k.getX()*48, k.getY()*48,k.getX()*48+ 48,k.getY()*48+ 48,48*0,0,48*0+48,48,null);
	}
	
	/**
	 * Valto kirajzolasa
	 * @param v melyik Valtot
	 */
	public void rajzol(Valto v) {
		graphics.setColor(Color.GRAY);
		graphics.fillRect(v.getX()*48, v.getY()*48, 48, 48);
		
		int index = 0;
		if(v.getX() == v.getAg('a').getX() && v.getX() == v.getAktivAg().getX()){//egyenes
			index = 0;
		}
		else if(v.getY() == v.getAg('a').getY() && v.getY() == v.getAktivAg().getY()){
			index = 1;
		}
		else if((v.getX() == v.getAktivAg().getX()-1 && v.getY() == v.getAg('a').getY()-1) ||
				(v.getX() == v.getAg('a').getX()-1 && v.getY() == v.getAktivAg().getY()-1)){
			index = 2;
		}
		else if((v.getX() == v.getAg('a').getX()+1 && v.getY() == v.getAktivAg().getY()-1) ||
				(v.getX() == v.getAktivAg().getX()+1 && v.getY() == v.getAg('a').getY()-1)){
			index=3;
		}
		else if((v.getX() == v.getAg('a').getX()+1 && v.getY() == v.getAktivAg().getY()+1) ||
				(v.getX() == v.getAktivAg().getX()+1 && v.getY() == v.getAg('a').getY()+1)){
			index=4;
		}
		else if((v.getX() == v.getAktivAg().getX()-1 && v.getY() == v.getAg('a').getY()+1) ||
				(v.getX() == v.getAg('a').getX()-1 && v.getY() == v.getAktivAg().getY()+1)){
			index=5;
		}
		graphics.drawImage(SinKep, v.getX()*48, v.getY()*48,v.getX()*48+ 48,v.getY()*48+ 48,48*index,0,48*index+48,48,null);
	}
	
	/**
	 * besint nem kell kirajzolni
	 * @param b melyik BeSint
	 */
	public void rajzol(BeSin b) {
	}
}
