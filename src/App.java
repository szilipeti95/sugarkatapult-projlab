import java.awt.CardLayout;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class App extends JFrame {
	private static final long serialVersionUID = 1L;//ez kell JFrame miatt..
	
	static JPanel kontener = new JPanel(new CardLayout());
	static Menu menu = new Menu();//ez a mi menu osztalyunk
	static Rajzolo rajzolo = new Rajzolo();
	static Controller controller = new Controller();
	
	public App()
	{//indulaskor
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
        setSize(720,720);
        setTitle("sugarkatapult");
		setResizable(false);
		setLocation(300,100);
		this.add(kontener);
		kontener.add(rajzolo, "rajzolo");
		kontener.add(menu, "menu");
		
		CardLayout cardl = (CardLayout) kontener.getLayout();
		cardl.show(kontener, "menu");
		
		
	}
	
	public static void main(String[] args) throws IOException {
		App ablak = new App();
		ablak.setVisible(true);
		
		Jatek.getInstance().setController(controller);
		Jatek.getInstance().setRajzolo(rajzolo);
                Jatek.getInstance().setMenu(menu);
		
		rajzolo.addMouseListener(controller);
	}
}
