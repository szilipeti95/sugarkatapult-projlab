import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Terepasztalon valósul meg az egész játék.
 * Eltárolja a Mozdonyokat, síneket, állomásokat, alagutat
 * Nézi, hogy hány olyan vonat van még a terepasztalon ami rendelkezik utassal,
 * ezzel kezelve a játék nyerését.
 */
public class Terepasztal {
	/**
	 * Nem üres vonatok száma a terepasztalon
	 */
	private int teliVonatSzam;
	/**
	 * Terepasztal egyetlen példánya
	 */
	private static Terepasztal instance;
	/**
	 * Összes Mozdony referenciája ami a terepasztalon van
	 */
	private ArrayList<Mozdony> mozdonyok;
	/**
	 *	Összes SinElem referenciája ami a terepasztalon van
	 */
	private ArrayList<SinElem> sinelemek;
	/**
	 *	BeSinek tárolása
	 */
	private ArrayList<BeSin> besinek;
	/**
	 *	A terepasztalon levő alagút referenciája
	 */
	private Alagut alagut;
	/**
	 *	tickek számlálója
	 */
	private int tck;
        
        private int mozdonyokSzama = 0;
        
        private final String lehetsegesKocsik = "kpzsx";
        
	/**
	 * Terepasztal alapértékeit beállítja, listákat létrehozza
	 */
	private Terepasztal(){
        teliVonatSzam = 0;
        mozdonyok = new ArrayList<>();
        sinelemek = new ArrayList<>();
        besinek = new ArrayList<>();
        alagut = new Alagut("t1");
        tck = 0;
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
	 * Törli a terepasztal elemeit.
	 */
	public void reset(){
		teliVonatSzam = 0;
		mozdonyok.clear();
		sinelemek.clear();
		besinek.clear();
		alagut = new Alagut("t1");
		tck = 0;
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

	public void rajzol()
	{
		Jatek.getInstance().getRajzolo().repaint();
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
					beSin.setCoords(Integer.parseInt(attrs[2]), Integer.parseInt(attrs[3]));
					AddBeSin(beSin);
					AddSinElem(beSin);
					break;
				case "valto":
					Valto valto = new Valto(attrs[1]);
					valto.setCoords(Integer.parseInt(attrs[2]), Integer.parseInt(attrs[3]));
					AddSinElem(valto);
					break;
				case "alagutszaj":
					AlagutSzaj alagutSzaj = new AlagutSzaj(attrs[1]);
					alagutSzaj.setCoords(Integer.parseInt(attrs[2]), Integer.parseInt(attrs[3]));
					AddSinElem(alagutSzaj);
					break;
				case "allomas":
					Allomas allomas = new Allomas(attrs[1], attrs[2], Integer.parseInt(attrs[3]));
					allomas.setCoords(Integer.parseInt(attrs[4]), Integer.parseInt(attrs[5]));
					AddSinElem(allomas);
					break;
				case "normalsin":
					Sin sin = new Sin(attrs[1]);
					sin.setCoords(Integer.parseInt(attrs[2]), Integer.parseInt(attrs[3]));
					AddSinElem(sin);
					break;
				case "keresztsin":
					KeresztSin keresztSin = new KeresztSin(attrs[1]);
					keresztSin.setCoords(Integer.parseInt(attrs[2]), Integer.parseInt(attrs[3]));
					AddSinElem(keresztSin);
					break;

				default:
					break;
				}
			}
			while((line = br.readLine())!= null/* && !line.equals(".")*/)
			{
				String[] attrs = line.split(" ");
				String[] elso = attrs[0].split("-");
				String[] masodik = attrs[1].split("-");
				
				SinElem talalat1 = ListContains(sinelemek, elso[0]);
				SinElem talalat2 = ListContains(sinelemek, masodik[0]);
				
				talalat1.setSinElem(talalat2, elso[1].charAt(0));
				talalat2.setSinElem(talalat1, masodik[1].charAt(0));
			}
			/*while((line = br.readLine())!= null && !line.equals("."))
			{
				String[] attrs = line.split(" ");
				BeSin beSin = ListContains(besinek, attrs[0]);
				beSin.VonatBead(attrs[2], Integer.parseInt(attrs[1]), attrs[3]);
			}*/ //ez a vonat beolvasas
			System.out.println("jatek elindult");
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
	    	if(sinelemek.get(i).getX() == x && sinelemek.get(i).getY() == y){
				sinelemek.get(i).onInput();
	    		break;
			}
		}
		Jatek.getInstance().getRajzolo().repaint();
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
         mozdonyokSzama++;
         String s = "m" + Integer.toString(mozdonyokSzama);
         Random r = new Random();
         int db = r.nextInt(5); //max 5 kocsi
          db = Math.max(db, 1); //legyen minimum 1 db;
         StringBuilder sb = new StringBuilder();
         for(int i = 0; i < db; i++){
             int index = r.nextInt(lehetsegesKocsik.length());
             sb.append(lehetsegesKocsik.charAt(index));
         }
         
         int tick = r.nextInt(3)+1;
            System.out.println("Db: " + db + ", kocsik: " + sb.toString() + ", Tick: " + tick);
         b.VonatBead(s, tick, sb.toString());
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
	
	public ArrayList<SinElem> getSinelemek()
	{
		return sinelemek;
	}
        
        public ArrayList<Mozdony> getMozdonyok()
	{
		return mozdonyok;
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
