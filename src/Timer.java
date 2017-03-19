import java.util.Scanner;

public class Timer {
    public Timer(){
        System.out.println("Timer.Constructor()");
    }

	public void start() {
		System.out.println("Timer.start()");
		
		System.out.println("Tick? (igen/nem)");
		Scanner reader = new Scanner(System.in);
		boolean exit = false;
		while (!exit) {
		if (reader.next().equals("igen"))
			Terepasztal.getInstance().tick();
		else
			exit = true;
		}
	}
}
