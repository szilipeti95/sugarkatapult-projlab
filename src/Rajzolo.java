import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Rajzolo extends JPanel{
	private static final long serialVersionUID = 1L;//ez kell JPanel miatt..
	
	final int height=15, width=15;
	
	Graphics graphics;
	
	private BufferedImage MozdonyKep;
	private BufferedImage UtasKocsiKep;
	private BufferedImage SzenesKocsiKep;
	private BufferedImage AllomasKep;
	private BufferedImage AlagutSzajKep;
	private BufferedImage SinKep;
	//private BufferedImage KanyarKep;
	private BufferedImage KeresztSinKep;
	//private BufferedImage ValtoKep;
	private BufferedImage BeSinKep;
	private BufferedImage fuKep;
	public void loadImages() {
		try
		{
			SinKep = ImageIO.read(new File("assets\\normalsin.png"));
			AllomasKep = ImageIO.read(new File("assets\\allomas.png"));
			KeresztSinKep = ImageIO.read(new File("assets\\keresztsin.png"));
			AlagutSzajKep = ImageIO.read(new File("assets\\alagutszaj.png"));
			fuKep = ImageIO.read(new File("assets\\fu.png"));
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
	}
	
	public void rajzol(UtasKocsi u) {
	}
	
	public void rajzol(SzenesKocsi sz) {
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
