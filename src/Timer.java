import java.util.TimerTask;

public class Timer {
        //elindul a Timer
	//Akkor hivodik meg amikor a jatekos elinditja a jatekot.
	public void start() {
		java.util.Timer t = new java.util.Timer();
		t.schedule(new TimerTask()
		{
			  @Override
			  public void run() {
			    Terepasztal.getInstance().tick();
			    Terepasztal.getInstance().rajzol();
			  }
		}, 0,1000);
	}
}