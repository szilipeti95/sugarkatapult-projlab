import java.util.TimerTask;

/**
 * idozito osztaly
 */
public class Timer {
    java.util.Timer t = new java.util.Timer();
    /**
     * elindul a Timer
     * Akkor hivodik meg amikor a jatekos elinditja a jatekot
     */
	public void start() {
		t.schedule(new TimerTask()
		{
			  @Override
			  public void run() {
			    Terepasztal.getInstance().tick();//lepteti a szimulaciot
			    Terepasztal.getInstance().rajzol();//es kirajzolja
			  }
		}, 0,500);
	}
    
	/**
	 * idozito megallitasa
	 * amikor vege a jateknak, pl vesztett, nyert
	 */
    public void stop(){
        t.cancel();
    }
}