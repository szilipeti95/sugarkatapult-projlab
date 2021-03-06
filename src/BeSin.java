import java.util.ArrayList;
import java.util.List;

/**
 * BeSin osztaly, SinElem leszarmazottja.
 * Vonatok belepesi pontja
 * csak egy kimeno aga van, mert a palya szelen van
 * nincs kirajzolva
 */
public class BeSin extends SinElem {
	List<Vonat> beadando = new ArrayList<>();//taroljuk a vonatokat amig varnak
	
	/**
	 * @author Jana (+Zotya kommentek)
	 * Beadando vonatokat ezekben az osztalyokban taroljuk amig varnak
	 */
	class Vonat
	{
		public Integer tck;
		public String id;
		public String vagonok;
		
		/**
		 * konstruktor
		 * @param id Azonosito
		 * @param tck hanyadik ticknel lep a palyara
		 * @param vagonok vagonok tipusai, pl.: x-szenes, p-piros utas
		 */
		Vonat(String id, Integer tck, String vagonok) {
			this.id = id;
			this.vagonok = vagonok;
			this.tck = tck;
		}
	}
	
	/**
	 * konstruktor
	 * @param id Azonosito
	 */
	BeSin(String id) 
    {
    	super(id);
    }
	
	/**
	 * szamolja csak hogy raleptek
	 * @param m melyik mozdony
	 * @param s honnan
	 */
	@Override
	public void leptet(Mozdony m, SinElem s) {
		//A mozdony ralepett a besinre akkor vesztunk. nem mehet ki a palyarol
        szerelvenyek++;
        Jatek.getInstance().veszt();
	}
	
	/**
	 * Visszaadja a kovetkezo SinElemet az elozo alapjan
	 * @param elozo Honnan jott a vonat. Mivel ez besin, elvileg null a param
	 */
	@Override
	public SinElem getKovSinElem(SinElem elozo) {
		return sinA;//ez jo, mert nincs sinBje a BeSinnek
	}
	
	/**
	 * Uj vonat hozzaadasa, terepasztal hivja meg
	 * @param azon Azonositoja a vonatnak
	 * @param tck Hanyadik ticknel indul
	 * @param vagonok Vagonok tipusai, pl.: x-szenes, p-piros utas
	 */
	public void VonatBead(String azon, int tck, String vagonok) {
		beadando.add(new Vonat(azon, tck, vagonok));
	}
	
	/**
	 * Terepasztal hivja meg, bead vonatot ha indul ebben a tickben
	 * @param t Hanyadik ticknel jarunk
	 */
	public void tick(int t) {
		for(Vonat v: beadando) {
			if (v.tck == t) {
				Vagon kovKocsi = null;
				List<Vagon> vonat = new ArrayList<>();
				for(int i = v.vagonok.length()-1; i >= 0; i--) {//vonat felepitese hatulrol kezdve
					Vagon u = null;
					switch (v.vagonok.charAt(i)) {//mi a tipusa a vagonnak
					case 'p':
						u = new UtasKocsi(v.id+"-u"+(i+1), kovKocsi, Szin.PIROS, false);
						break;
					case 'k':
						u = new UtasKocsi(v.id+"-u"+(i+1), kovKocsi, Szin.KEK, false);
						break;
					case 'z':
						u = new UtasKocsi(v.id+"-u"+(i+1), kovKocsi, Szin.ZOLD, false);
						break;
					case 's':
						u = new UtasKocsi(v.id+"-u"+(i+1), kovKocsi, Szin.SARGA, false);
						break;
					case 'x':
						u = new SzenesKocsi(v.id+"-s"+(i+1), kovKocsi);
						break;
					default:
						break;
					}
					u.SetSinElem(this);
                    u.SetElozoElem(this);
					vonat.add(u);
					kovKocsi = u;
				}
				Mozdony m = new Mozdony(v.id, kovKocsi, sinA);//vegen az egeszet egy mozdonyra kapcsoljuk
                m.SetElozoElem(this);
				Terepasztal.getInstance().AddMozdony(m);//elinditjuk itt
			}
		}
	}
	
	/**
	 * kirajzoltatja magat a parameterben kapott rajzoloval
	 * @param r A rajzolo ami kirajzolja
	 */
	@Override
	public void rajzol(Rajzolo r) {
		r.rajzol(this);
	}
    
	/**
	 * againak lekerese
	 * @param a melyiket. a, b ugyanazt adja vissza
	 * @return a parameterben megadott sinElem. null, ha hibas parameter
	 */
    @Override
    public SinElem getAg(char c){
        if(c == 'a' || c == 'b'){
            return sinA;
        }
        return null;
    }
        
}
