import java.util.Scanner;

public class Menu {//menupontok itt
	private int progress;//hanyadik palya
	private String palya = "";
	public void start() 
	{//uj Jatek
		System.out.println("Menu.start()");
		
		
		Scanner reader = new Scanner(System.in);
		boolean exit = false;
		//skeleton dolgok
		while(!exit) {
			System.out.println("Valasszon az alabbi lehetoegek kozul: play, select, save, load, exit");
			String valasz = reader.next();
			switch (valasz) {
			case "play":
					Jatek.getInstance().start(palya);
				break;
			case "select":
				select();
				break;
			case "save":
				save();
				break;
			case "load":
				load();
				break;
			case "exit":
				exit = true;
				break;
			default:
				System.out.println("Nincs ilyen lehetoseg!");
				break;
			}
		}
		reader.close();
		
		if (exit)
			System.exit(0);
	}
	
	public void save() 
	{//progresst menti
		System.out.println("Menu.save()");
		
		//progress-t kimenteni fajlba
		
	}
	
	public int select() 
	{//palya szamat adja vissza
		System.out.println("Menu.select()");	
		System.out.println("Adja meg a palya nevet:");
		Scanner reader = new Scanner(System.in);
		palya = reader.next();
		return 0;
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
