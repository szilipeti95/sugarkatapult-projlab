import java.util.TimerTask;

public class Timer {
        //elindul a Timer
	//Akkor hivodik meg amikor a jatekos elinditja a jatekot.
        java.util.Timer t = new java.util.Timer();
	public void start() {
		
		t.schedule(new TimerTask()
		{
			  @Override
			  public void run() {
			    Terepasztal.getInstance().tick();
			    Terepasztal.getInstance().rajzol();
			  }
		}, 0,500);
	}
        
        public void stop(){
            t.cancel();
        }
}