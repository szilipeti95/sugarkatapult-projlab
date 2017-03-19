import java.util.Scanner;

public class Menu {//menupontok itt
	private int progress;//hanyadik palya
	private String palya;
	public void start(String ujpalya) 
	{//uj Jatek
		System.out.println("Menu.start()");
		
		//skeleton dolgok
		System.out.println("Adja meg a palya nevet: ");
		Scanner reader = new Scanner(System.in);
		String valasz = reader.next();
		reader.close();
		palya=valasz;//ujpalya
		
		Jatek.getInstance().start(palya);//uj jatek ezzel a palyaval
	}
	
	public void save() 
	{//progresst menti
		System.out.println("Menu.save()");
		
		//progress-t kimenteni fajlba
		
	}
	
	public int select() 
	{//palya szamat adja vissza
		System.out.println("Menu.select()");		
		return progress;
	}
	
	public int load() 
	{//meddig jutottunk el
		System.out.println("Menu.load()");
		
		//progress-t betolti fajlbol
		
		return progress;
	}
	
	public void exit() 
	{//kilep
		System.out.println("Menu.exit()");
		
	}
}
