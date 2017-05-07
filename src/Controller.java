import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * controller osztaly az eger kattintas kezelesere
 * MouseAdapter leszarmazottja, 
 */
public class Controller extends MouseAdapter  {
	
	/**
	 * kattintasra hivodik meg
	 * szol a terepasztalnak, hogy hanyas mezore kattintottunk
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		Terepasztal.getInstance().onInput(arg0.getX()/48, arg0.getY()/48);
	}
}
