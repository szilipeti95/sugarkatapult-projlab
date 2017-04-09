
/**
 * Ugyan az a felelossege, mint az ososztalyanak, a Vagonnak. 
 * Az allomasnal nem csinal semmit.
 */
public class SzenesKocsi extends Vagon {

	public SzenesKocsi() {
	}
	
	/**
	 * Ez a meteodus allitja be, hogy az elozo kocsi ures-e
	 * Mivel a szenesKocsin nincsenek utasok, ezert ez automatikusan bealltja
	 * a kovetkezo kocsinak a megfelelo attributumat
	 * @param ures true, ha az elozo kocsi ures, kulonben false
	 */
	@Override
	public void SetElozoUres(boolean ures) {
		kovKocsi.SetElozoUres(ures);
	}

}
