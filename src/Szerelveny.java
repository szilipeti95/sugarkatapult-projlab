public abstract class Szerelveny {
	protected SinElem sinElem;
	private boolean alagutban;
	protected SinElem elozoSinElem;
	protected Kocsi kovKocsi;
	
	public Szerelveny()
	{
		sinElem = new Sin();
	}
	
	public SinElem getSin() {
		return sinElem;
	}
		
	//Ezt a függvény hívja meg az alagtSzaj, mikor a szerelvény belép az alagúttba.
	public void alagutValt() {
		System.out.println("Szerelveny.alagutValt()");
		alagutban = !alagutban;
	}
	
	public boolean alagutbanVan() {
		return alagutban;
	}
}
