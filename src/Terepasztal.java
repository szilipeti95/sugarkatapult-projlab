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

	/**
	 * Terepasztal alapértékeit beállítja, listákat létrehozza
	 */
	private Terepasztal(){
        teliVonatSzam = 0;
        mozdonyok = new ArrayList<>();
        sinelemek = new ArrayList<>();
        besinek = new ArrayList<>();
        alagut = new Alagut();

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
	    for(Mozdony m: mozdonyok){
            m.mozog();
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
		ArrayList<BeSin> besinlista = new ArrayList();
		ArrayList<Sin> sinlista = new ArrayList();
		ArrayList<KeresztSin> keresztsinlista = new ArrayList();
		ArrayList<Allomas> allomaslista = new ArrayList();
		ArrayList<AlagutSzaj> alagutszajlista = new ArrayList();
		ArrayList<Valto> valtolista = new ArrayList();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("progress.txt"));
			String line = null;
			while((line = br.readLine())!= null && !line.equals("."))
			{
				String[] attrs = line.split(" ");
				switch (attrs[0]) {
				case "besin":
					BeSin beSin = new BeSin(attrs[1]);
					besinlista.add(beSin);
					AddBeSin(beSin);
					AddSinElem(beSin);
					break;
				case "valto":
					Valto valto = new Valto(attrs[1]); 
					valtolista.add(valto);
					AddSinElem(valto);
					break;
				case "alagutszaj":
					AlagutSzaj alagutSzaj = new AlagutSzaj(attrs[1]);
					alagutszajlista.add(alagutSzaj);
					AddSinElem(alagutSzaj);
					break;
				case "allomas":
					Allomas allomas = new Allomas(attrs[1], attrs[2], Integer.parseInt(attrs[3]));
					allomaslista.add(allomas);
					AddSinElem(allomas);
					break;
				case "normalsin":
					Sin sin = new Sin(attrs[1]);
					sinlista.add(sin);
					AddSinElem(sin);
					break;
				case "keresztsin":
					KeresztSin keresztSin = new KeresztSin(attrs[1]);
					keresztsinlista.add(keresztSin);
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
				
				Sin sin1 = ListContains(sinlista, elso[0]);
				if(sin1 != null) {
					SinElem s = ListContains(sinelemek, masodik[0]);
					if (elso[1].equals("a")) 
						sin1.setSinElemA(s);
					else if (elso[1].equals("b")) 
						sin1.setSinElemB(s);
				}
				Sin sin2 = ListContains(sinlista, masodik[0]);
				if(sin2 != null) {
					SinElem s = ListContains(sinelemek, elso[0]);
					if (masodik[1].equals("a")) 
						sin2.setSinElemA(s);
					else if (masodik[1].equals("b")) 
						sin2.setSinElemB(s);
				}
				KeresztSin ksin1 = ListContains(keresztsinlista, elso[0]);
				if(ksin1 != null) {
					SinElem s = ListContains(sinelemek, masodik[0]);
					if (elso[1].equals("a")) 
						ksin1.setSinElemA(s);
					else if (elso[1].equals("b")) 
						ksin1.setSinElemB(s);
					else if (elso[1].equals("c")) 
						ksin1.setSinElemB(s);
					else if (elso[1].equals("d")) 
						ksin1.setSinElemB(s);
				}
				KeresztSin ksin2 = ListContains(keresztsinlista, masodik[0]);
				if(ksin2 != null) {
					SinElem s = ListContains(sinelemek, elso[0]);
					if (masodik[1].equals("a")) 
						ksin2.setSinElemA(s);
					else if (masodik[1].equals("b")) 
						ksin2.setSinElemB(s);
					else if (masodik[1].equals("c")) 
						ksin2.setSinElemB(s);
					else if (masodik[1].equals("d")) 
						ksin2.setSinElemB(s);
				}
				KeresztSin valto1 = ListContains(keresztsinlista, elso[0]);
				if(valto1 != null) {
					SinElem s = ListContains(sinelemek, masodik[0]);
					if (elso[1].equals("a")) 
						valto1.setSinElemA(s);
					else if (elso[1].equals("b")) 
						valto1.setSinElemB(s);
					else if (elso[1].equals("c")) 
						valto1.setSinElemB(s);
					else if (elso[1].equals("d")) 
						valto1.setSinElemB(s);
				}
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

	/**
	 * bead egy új vonatot
	 * @param index besin indexe ahol jön az új vonat
	 */
	public void VonatBead(int index) {
		besinek.get(index).VonatBead();
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

}
