import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Rajzolo extends JPanel{
	private static final long serialVersionUID = 1L;//ez kell JPanel miatt..
	
	Graphics graphics;
	
	private BufferedImage MozdonyKep;
	private BufferedImage UtasKocsiKep;
	private BufferedImage SzenesKocsiKep;
	private BufferedImage AllomasKep;
	private BufferedImage AlagutSzajKep;
	private BufferedImage SinKep;
	private BufferedImage KanyarKep;
	private BufferedImage KeresztSin;
	private BufferedImage ValtoKep;
	private BufferedImage BeSinKep;
	public void loadImages() {
		try
		{
			SinKep = ImageIO.read(new File("assets\\normalsin.png"));
			KanyarKep = ImageIO.read(new File("assets\\kanyar.png"));
		}
		catch(IOException e){System.out.println(e.getMessage());}
	}
	
	public void rajzol() {
		for(SinElem s : Terepasztal.getInstance().getSinelemek()){
			s.rajzol(this);
		}
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
	}
	
	public void rajzol(AlagutSzaj a) {
	}
	
	public void rajzol(Sin s) {
		graphics.drawImage(SinKep, s.getX()*48, s.getY()*48, 48, 48, null);
	}
	
	public void rajzol(KeresztSin k) {
	}
	
	public void rajzol(Valto v) {
	}
	
	public void rajzol(BeSin b) {
	}
}
