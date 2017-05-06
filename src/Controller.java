import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Controller extends MouseAdapter  {
	@Override
	public void mouseClicked(MouseEvent arg0) {
		Terepasztal.getInstance().onInput(arg0.getX()/48, arg0.getY()/48);
	}
}
