public abstract class Szerelveny {
	protected SinElem sinElem;
	private Boolean alagutban;
	private SinElem elozoSinElem;
	protected Kocsi kovKocsi;
	public SinElem getSin() {
		return sinElem;
	}
	
	public abstract void leszallit(Allomas a);
	
	public void alagutValt() {
			alagutban = !alagutban;
			System.out.println("Szerelveny.alagutValt()");
	}
	
	public Boolean alagutbanVan() {
		return alagutban;
	}
}
