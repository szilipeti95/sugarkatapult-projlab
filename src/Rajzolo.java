import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Rajzolo extends JPanel {
	private static final long serialVersionUID = 1L;//ez kell JPanel miatt..
	
	final int height=15, width=15;
	
	Graphics graphics;
	
	private BufferedImage MozdonyKep;
	private BufferedImage KocsiKep;
	//private BufferedImage SzenesKocsiKep;
	private BufferedImage AllomasKep;
	private BufferedImage AlagutSzajKep;
	private BufferedImage SinKep;
	//private BufferedImage KanyarKep;
	private BufferedImage KeresztSinKep;
	//private BufferedImage ValtoKep;
	//private BufferedImage BeSinKep;
	private BufferedImage fuKep;
        private BufferedImage utasKep;
    private BufferedImage robbanasKep;
       private boolean end = false;
    
    JButton KilepGomb;
    
    public Rajzolo()
    {
    	KilepGomb = new JButton("Menu");
		this.add(KilepGomb);
		Rajzolo tmp = this;
		KilepGomb.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
		    	//System.out.println("jajajaj");
		        Jatek.getInstance().veszt();
		        CardLayout cardl = (CardLayout) tmp.getParent().getLayout();
		        cardl.show(tmp.getParent(), "menu");
			}});
    }
    
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
		catch(IOException e){System.out.println(e.getMessage());}
	}
	
	public void rajzol() {
		for(int i=0;i<height;i++)
			for(int ii=0;ii<width;ii++)
				graphics.drawImage(fuKep, ii*48, i*48, 48, 48, null);
				
		for(SinElem s : Terepasztal.getInstance().getSinelemek()){
			s.rajzol(this);
		}
		
                for(Mozdony m : Terepasztal.getInstance().getMozdonyok()){
                    m.rajzol(this);
                }
                
		//alagutszajakat megint kirajzoljuk, mert a vonatok fole jonnek
		Alagut al = Terepasztal.getInstance().getAlagut();
		if(al.getSzaj('a')!=null)
			rajzol(al.getSzaj('a'));
		if(al.getSzaj('b')!=null)
			rajzol(al.getSzaj('b'));
		
	}
	
	@Override
    public void paintComponent(Graphics g) {
		super.paintComponent(g);
		/*for(SinElem s : Terepasztal.getInstance().getSinelemek()){
			s.rajzol(this);
		}*/
		graphics=g;
		rajzol();
    }
	
	public void rajzol(Mozdony m) {
            int index1 = 0;
            int ferde = 0;
           
            if(m.alagutbanVan()){
                return;
            }
            
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
           
           
            graphics.drawImage(MozdonyKep, m.getSin().getX()*48, m.getSin().getY()*48,m.getSin().getX()*48+ 48,m.getSin().getY()*48+ 48,48*index1,0+ferde*48,48*index1+48,48+ferde*48,null);
            if(m.getUtkozott()){
                graphics.drawImage(robbanasKep, m.getSin().getX()*48, m.getSin().getY()*48, null);
            }
            
	}
	
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
               index1 = 5;
               
               
           }else if(u.getElozoSin().getY() < u.getSin().getKovSinElem(u.getElozoSin()).getY() && u.getElozoSin().getX() < u.getSin().getKovSinElem(u.getElozoSin()).getX()  ){
               //jobbra le
               index1 = 3;
               
           }else if(u.getElozoSin().getX() > u.getSin().getKovSinElem(u.getElozoSin()).getX() && u.getElozoSin().getY() < u.getSin().getKovSinElem(u.getElozoSin()).getY()  ){
               //balra le
               index1 = 4;
               
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
	
	public void rajzol(SzenesKocsi sz) {
                       
            int index1 = 0;
            
            //int ferde = 0;
            
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
           int  ferde = 4;
          
            graphics.drawImage(KocsiKep, sz.getSin().getX()*48, sz.getSin().getY()*48,sz.getSin().getX()*48+ 48,sz.getSin().getY()*48+ 48,48*index1,0+ferde*48,48*index1+48,48+ferde*48,null);
 
            
            
	}
	
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
	
	public void rajzol(KeresztSin k) {
		graphics.drawImage(KeresztSinKep, k.getX()*48, k.getY()*48,k.getX()*48+ 48,k.getY()*48+ 48,48*0,0,48*0+48,48,null);
	}
	
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
	
	public void rajzol(BeSin b) {
	}
}
