import java.util.Scanner;

public class Timer {
        //elindul a Timer
	//Akkor hívódik meg amikor a játékos elindítja a játékot.
	public void start() {
		System.out.println("Timer.start()");
		
		Scanner reader = new Scanner(System.in);
		boolean exit = false;
		while (!exit) {
			System.out.println("Valasszon az alabbi lehetosegek kozul:? (tick, input, exit, vonatbead)");
			switch (reader.next()) {
			case "tick":
				Terepasztal.getInstance().tick();
				break;
			case "input":
				Terepasztal.getInstance().onInput(0, 0);			
				break;
			case "vonatbead":
				Terepasztal.getInstance().VonatBead();			
				break;
			case "exit":
				exit = true;
				break;
			default:
				break;
			}
		if (exit)
			System.exit(0);

		}
	}
}
