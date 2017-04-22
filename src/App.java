import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class App {
	public static void main(String[] args) throws IOException {
		
		//Letrehozzuk a menut
		Menu menu = new Menu();
                
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
