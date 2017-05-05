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
		
		//Letrehozzuk a menut
		//menu = new Menu();
		
		menu.getGraphics().drawString("CandyCrush(féle)Game", 25, 100);
                
            try (Scanner reader = new Scanner(System.in)) {
                String line = null;
                //System.out.println("Hello app");
                //int p = -1;
                //System.exit(0);
                while (reader.hasNext()) {
                    line = reader.nextLine();
                    //System.out.println(line);
                    String[] attrs = line.toLowerCase().split(" ");
                    switch (attrs[0]) {
                        case "loadmap":
                        case "load"://mindkettot elfogadjuk
                            if(attrs.length > 1) {
                                menu.loadMap(attrs[1]);
                                System.out.println(attrs[1] + " palya kivalasztva");
                            }
                            else
                                System.out.println("nem adtal meg parametert");
                            break;
                        case "play":
                            menu.start();
                            break;
                        case "tick":
                            int n = 1;
                            if(attrs.length>1)
                                n = Integer.parseInt(attrs[1]);
                            System.out.println(n + " tick megtortent");
                            for (int i = 0; i < n; i++)
                                Terepasztal.getInstance().tick();
                            break;
                        case "valto":
                            if(attrs.length > 1)
                                Terepasztal.getInstance().onInput(attrs[1]);
                            else
                                System.out.println("nem adtal meg parametert");
                            break;
                        case "alagutszaj":
                            if(attrs.length > 1)
                                Terepasztal.getInstance().onInput(attrs[1]);
                            else
                                System.out.println("nem adtal meg parametert");
                            break;
                        case "reset":
                            menu.start();
                            System.out.println("reset done");
                            break;
                        case "info":
                            if (attrs.length > 2)
                                Terepasztal.getInstance().GetInfo(attrs[1], attrs[2]);
                            else if(attrs.length == 2)
                                Terepasztal.getInstance().GetInfo(attrs[1], null);
                            else
                                System.out.println("nem adtal meg parametert");
                            break;
                        case "exit":
                            menu.exit();
                            break;
                        default:
                            break;
                    }
                }
            }
	}
}
