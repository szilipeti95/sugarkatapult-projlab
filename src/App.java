import java.awt.CardLayout;
import java.awt.Dimension;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author sugarkatapult
 * a szoftver fo osztalya, itt van a main fuggveny
 * JFrame leszarmazottja: ablak a grafikus megjeleneshez
 * letrehozza a menut, controllert, rajzolot
 */
public class App extends JFrame {
	private static final long serialVersionUID = 1L;//ez kell JFrame miatt..
	static JPanel kontener = new JPanel(new CardLayout());//tartalmazza a menu es rajzolo paneleket
	static Menu menu = new Menu();//ez a mi menu osztalyunk
	static Rajzolo rajzolo = new Rajzolo();
	static Controller controller = new Controller();
	
	/**
	 * konstruktor
	 * ablak es kontener init, menure valtas
	 */
	public App()
	{
		//ablak init
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("sugarkatapult");
		setResizable(false);
		setLocation(300,100);
		
		//kontener init
		kontener.setPreferredSize(new Dimension(720,720));
		this.add(kontener);
		pack();//beallitja az ablak meretet a kontener meretere (720,720)
		kontener.add(rajzolo, "rajzolo");
		kontener.add(menu, "menu");
		CardLayout cardl = (CardLayout) kontener.getLayout();
		cardl.show(kontener, "menu");//atvaltas a menure	
	}
	
	/**
	 * main fuggveny, ez hivodik az alkalmazas inditasakor
	 * inicializalas
	 * ablak letrehozasa
	 */
	public static void main(String[] args) throws IOException {
		App ablak = new App();
		ablak.setVisible(true);
		//jateknak megadjuk a letrehozott osztalyokat
		Jatek.getInstance().setRajzolo(rajzolo);
        Jatek.getInstance().setMenu(menu);
		rajzolo.addMouseListener(controller);
	}
}
