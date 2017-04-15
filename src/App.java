import java.util.Scanner;

public class App {
	public static void main(String[] args) {
		
		//Letrehozzuk a menut
		Menu menu = new Menu();
		
		Scanner reader = new Scanner(System.in);
		String line = null;
		while ((line = reader.nextLine()) != null) {
			String[] attrs = line.split(" ");
			switch (attrs[0]) {
			case "loadmap":
				menu.loadMap(attrs[1]);
				System.out.println(attrs[1] + " palya kivalasztva");
				break;
			case "play":
				menu.start();
				System.out.println("jatek elindult");
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
				Terepasztal.getInstance().onInput(attrs[1]);
				break;
			case "alagutszaj":
				Terepasztal.getInstance().onInput(attrs[1]);
				break;
			case "reset":
				menu.start();
				System.out.println("reset done");
				break;
			case "info":
				if (attrs.length > 2)
					Terepasztal.getInstance().GetInfo(attrs[1], attrs[2]);
				else
					Terepasztal.getInstance().GetInfo(attrs[1], null);
				break;

			default:
				break;
			}
		}
		reader.close();
	}
}
