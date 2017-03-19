public class AlagutSzaj extends SinElem {
	private Boolean megepitve;
	private AlagutSin alagutSin;
	
	//Reag�l arra, ha a felhaszn�l� r�kattint az alagutSzaj-ra
	public void onInput() {
		System.out.println("AlagutSzaj.onInput()");
		Alagut a = Terepasztal.getAlagut();
		if(a.getAllapot() == AlagutAllapot.VanAlagut){
			a.RemoveAlagutSzaj(this);
		}
		else{
			a.AddAlagutSzaj(this);
		}
	}
	
	//
	public void leptet(Mozdony m, SinElem s) {
		System.out.println("AlagutSzaj.leptet()");
	}
	
	//Visszaadja, hogy van-e rajta �tk�z�s
	public Boolean getUtkozes() {
		System.out.println("AlagutSzaj.getUtkozes()");
		return false;
	}

	@Override
	public SinElem getKovSinElem() {
		System.out.println("AlagutSzaj.getKovSinElem()");
		return alagutSin;
	}
}
