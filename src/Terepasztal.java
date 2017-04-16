import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Terepasztal {
	private int teliVonatSzam;
	private static Terepasztal instance;
	private ArrayList<Mozdony> mozdonyok;
	private ArrayList<SinElem> sinelemek;
	private ArrayList<BeSin> besinek;
	private Alagut alagut;
	private int tck;

	/**
	 * Terepasztal alapértékeit beállítja, listákat létrehozza
	 */
	private Terepasztal(){
        teliVonatSzam = 0;
        mozdonyok = new ArrayList<>();
        sinelemek = new ArrayList<>();
        besinek = new ArrayList<>();
        alagut = new Alagut("t1");

	}
	/**
	 * Singleton osztály, lekérdezi a Terepasztalt
	 * @return Terepasztal referencia
	 */
	public static Terepasztal getInstance(){
		if(instance == null){
			instance = new Terepasztal();
		}
		return instance;
	}

	/**
	 * Timer tickre ez a függvény hívódik meg.
	 * Mozgatja a vonatokat, és vizsgálja az ütközéseket
	 */
	public void tick() {
		tck++;
	    for(Mozdony m: mozdonyok){
            m.mozog();
        }
	    for (BeSin b : besinek) {
	    	b.tick(tck);
	    }
        for(Mozdony m: mozdonyok){
            m.utkozesVizsgal();
        }
	}

	/**
	 * Inicializálja a Terepasztal elemeit
	 * @param fileName a fájl neve amiből a beolvasás történik
	 */
	public void init(String fileName) {
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String line = null;
			while((line = br.readLine())!= null && !line.equals("."))
			{
				String[] attrs = line.split(" ");
				switch (attrs[0]) {
				case "besin":
					BeSin beSin = new BeSin(attrs[1]);
					AddBeSin(beSin);
					AddSinElem(beSin);
					break;
				case "valto":
					Valto valto = new Valto(attrs[1]); 
					AddSinElem(valto);
					break;
				case "alagutszaj":
					AlagutSzaj alagutSzaj = new AlagutSzaj(attrs[1]);
					AddSinElem(alagutSzaj);
					break;
				case "allomas":
					int ures=1;
					if(attrs.length==4)//ha meg van adva hogy ures-e, akkor beallitjuk. default ures
						ures=Integer.parseInt(attrs[3]);
					Allomas allomas = new Allomas(attrs[1], attrs[2], ures);
					AddSinElem(allomas);
					break;
				case "normalsin":
					Sin sin = new Sin(attrs[1]);
					AddSinElem(sin);
					break;
				case "keresztsin":
					KeresztSin keresztSin = new KeresztSin(attrs[1]);
					AddSinElem(keresztSin);
					break;

				default:
					break;
				}
			}
			while((line = br.readLine())!= null && !line.equals("."))
			{
				String[] attrs = line.split(" ");
				String[] elso = attrs[0].split("-");
				String[] masodik = attrs[1].split("-");
				
				SinElem talalat1 = ListContains(sinelemek, elso[0]);
				SinElem talalat2 = ListContains(sinelemek, masodik[0]);
				
				talalat1.setSinElem(talalat2, elso[1].charAt(0));
				talalat2.setSinElem(talalat1, masodik[1].charAt(0));
			}
			while((line = br.readLine())!= null && !line.equals("."))
			{
				String[] attrs = line.split(" ");
				BeSin beSin = ListContains(besinek, attrs[0]);
				beSin.VonatBead(attrs[2], Integer.parseInt(attrs[1]), attrs[3]);
			}
		} catch (FileNotFoundException e) {
			System.out.println("A fajl nem talalhato!");
		} catch (IOException e) {
			System.out.println("Hiba a fajlban!");
		}
	}

	/**
	 * Kattintásra hívódik meg a függvény
	 * Meghívja annak a SinElemnek az onClickjét amire kattintott a játékos
	 * @param x a kattintás x koordinátája
	 * @param y a kattintás y koordinátája
	 */
	public void onInput(int x, int y) {
		//megkeresi a sinelemet aminek x,y koordinátája megegyezik az inputtal
	    for(int i=0; i<sinelemek.size(); i++){
	    	// ha megegyezik meghívja az onInput függvényt
	    	/*if(sinelemek.get(i).getX() == x && sinelemek.get(i).getY() == y){
				sinelemek.get(i).onInput();
	    		break;
			}*/
		}
	}
	
	public void onInput(String id) {
		for(int i = 0; i < sinelemek.size(); i++)
		{
			if(sinelemek.get(i).id.equals(id))
				sinelemek.get(i).onInput();
		}
	}

	/**
	 *
	 */
	@Deprecated
	public void AddAlagutSzaj() {
	    System.out.println("Terepasztal.AddAlagutSzaj()");
	    //TODO: implement
        //Amugy ez mi?
	}

	/**
	 * Mozdony hozzáadás a listához
	 * @param m a mozdony amit hozzá kell adni a terepasztalhoz
	 */
	public void AddMozdony(Mozdony m) {
	    mozdonyok.add(m);
	    teliVonatSzam++;
	}

	/**
	 * BeSin hozzáadás a listához
	 * @param b BeSin refrerencia ami hozzáadódik a listához
	 */
	public void AddBeSin(BeSin b) {
        besinek.add(b);
    }

	/**
	 * SinElem hozzáadás a listához
	 * @param s SinElem referencia ami hozzáadódik a listához
	 */
	public void AddSinElem(SinElem s) {
        sinelemek.add(s);
	}
	/**
	 * SinElem törlése listából.
	 * @param s SinElem referencia amit törölni kell a listából
	 * @return true ha sikerült a törlés, false ha nem
	 */
	public boolean RemoveSinElem(SinElem s) {
	    return sinelemek.remove(s);
	}

	/**
	 * Visszaadja a terepasztal alagútját
	 * @return Alagut referencia
	 */
	public Alagut getAlagut() {
		return alagut;
	}

	/**
	 * teliVonatSzam számontartja hogy hány vonat van a terepasztalon
	 * aminek még van utasa.
	 * Ha ez a szám 0 a játéknak vége és a játékos nyert.
	 */
	public void vonatKiurult() {
	    teliVonatSzam--;
	    //ha elfogytak a vonatok
	    if (teliVonatSzam == 0)
	    {
			Jatek.getInstance().nyer();
        }
	}

	
	private <T extends SinElem> T ListContains(ArrayList<T> list, String id)
	{
		for (int i = 0; i < list.size(); i++)
		{
			if (list.get(i).id.equals(id))
				return list.get(i);
		}
		return null;
	}
	public void GetInfo(String id, String attr) {
		
		switch (id.charAt(0)) {
		case 'm':
			for (Mozdony mozdony: mozdonyok)
			{
				if (mozdony.id.equals(id.split("-")[0]))
					mozdony.GetInfo(id, attr);
			}
			break;
		case 't':
			if(attr.equals("alagut"))
				alagut.GetInfo(null);
			break;
		default:
			ListContains(sinelemek, id).GetInfo(attr);
			break;
		}
	}

}
