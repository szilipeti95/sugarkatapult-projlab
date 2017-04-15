import java.util.ArrayList;
import java.util.List;

public class BeSin extends SinElem {//vonatok belelepesi pontja
	List<Vonat> beadando = new ArrayList<>();
	
	
	
	class Vonat
	{
		public Integer tck;
		public String id;
		public String szerelvenyek;
		
		Vonat(String id, Integer tck, String szerelvenyek) {
			this.id = id;
			this.szerelvenyek = szerelvenyek;
			this.tck = tck;
		}
	}
	BeSin(String id) 
    {
    	super(id);
    }
	@Override //sinEleme
	public void leptet(Mozdony m, SinElem s) {
		//A mozdony ralepett a sinElemre
        szerelvenyek++;//SinEleme
	}
	
	
	@Override
	public SinElem getKovSinElem(SinElem elozo) {//itt az elozo elvileg null
		return sinA;//ez jo, mert nincs sinBje a BeSinnek
	}
	public void VonatBead(String azon, int tck, String vonatok) {
		beadando.add(new Vonat(azon, tck, vonatok));
	}
	
	
	
	@Override
	public void GetInfo(String attr) {
		super.GetInfo(attr);
		
	}
	public void tick(int t) {
		for(Vonat v: beadando) {
			if (v.tck == t) {
				Vagon kovKocsi = null;
				List<Vagon> vonat = new ArrayList<>();
				for(int i = v.szerelvenyek.length()-1; i >= 0; i--) {
					Vagon u = null;
					switch (v.szerelvenyek.charAt(i)) {
					case 'p':
						u = new UtasKocsi(v.id+"-u"+(i+1), kovKocsi, Szin.PIROS);
						break;
					case 'k':
						u = new UtasKocsi(v.id+"-u"+(i+1), kovKocsi, Szin.KEK);
						break;
					case 'z':
						u = new UtasKocsi(v.id+"-u"+(i+1), kovKocsi, Szin.ZOLD);
						break;
					case 's':
						u = new UtasKocsi(v.id+"-u"+(i+1), kovKocsi, Szin.SARGA);
						break;
					case 'x':
						u = new SzenesKocsi(v.id+"-s"+(i+1), kovKocsi);
						break;

					default:
						break;
					}
					u.SetSinElem(this);
					vonat.add(u);
					kovKocsi = u;
				}
				Mozdony m = new Mozdony(v.id, kovKocsi, this);
				Terepasztal.getInstance().AddMozdony(m);
			}
		}
	}
}

//Zotya
