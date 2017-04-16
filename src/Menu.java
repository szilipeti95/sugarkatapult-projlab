import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * @author Zotya
 * menupontok ebbol erhetok el: start, save, select, load, exit
 * tarolja hogy melyik palya van kivalsztva / hol tartunk a progressben
 */
public class Menu {//menupontok itt
	
	//ezeket jo ha megjegyezzuk, grafikus feluleten akkor ki lehet majd jelezni
	private int progress=0;//hanyadik palya
	private String palya = "";//palya neve
	
	/**
	 * progress alapjan eloallitja a palya nevet es elinditja a jatekot
	 */
	public void start() 
	{//uj Jatek
		//uj jatek inditasa
		if(palya!="")
		{
			Jatek.getInstance().start(palya);
		}
		else
			System.out.println("valasszon egy palyat eloszor (loadmap)");
	}
	
	/**
	 * elmenti a jelenlegi allast fajlba
	 */
	public void save() 
	{
		try
		{//fajlba ment
			BufferedWriter wr = new BufferedWriter(new FileWriter("progress.txt"));
			wr.write(progress);
			wr.close();
		}
		catch (Exception ex)
		{//valamiert nem sikerult az iras, sajnos nem tudjuk menteni az allast
			//esetleg messagebox a hibarol TODO
			System.out.println("hiba mentes");
		}
		
	}
	
	/**
	 * lepteti a progresst, ezt majd grafikus feluleten le lehetne cserelni gombokra TODO
	 * @return kovetkezo palya szama
	 */
	public int select() 
	{//palya szamat adja vissza
		progress++;
		return progress;
	}
	
	/**
	 * progresst betolti fajlbol: hanyadik palyanal tartunk. 0 ha nincs fajl vagy hibas
	 * @return hanyadik palyanal jarunk
	 */
	public int load() 
	{
		try
		{//fajlbol betolt
			BufferedReader br = new BufferedReader(new FileReader("progress.txt"));//hiba ha nincs ilyen fajl
			progress = Integer.parseInt(br.readLine());//int konverzio, itt is johet hiba
			br.close();
		}
		catch (Exception ex)
		{//meg nincs progress fajl vagy hibas, akkor eloszor jatszik
			progress=0;
			//esetleg messagebox a hibarol TODO
			System.out.println("hiba betoltes");
		}
		return progress;
	}
	
	/**
	 * kilep az egeszbol
	 */
	public void exit() 
	{
		//otlet: autosave, TODO kell?
		System.exit(0);
	}

	/**
	 * csak eltarolja a map fajl nevet, foleg protoban kell
	 * @param p Eleresi ut
	 */
	public void loadMap(String p) {
		palya = p;
	}
}
