package app.xutil.observeur;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractModeleEcoutable implements ModeleEcoutable{
	List<EcouteurModele> ecouteurs;
	
	public AbstractModeleEcoutable() {
		ecouteurs = new ArrayList<EcouteurModele>();
	}
	
	@Override
	public void ajoutEcouteur(EcouteurModele e) {
		this.ecouteurs.add(e);
		
	}

	@Override
	public void retraitEcouteur(EcouteurModele e) {
		this.ecouteurs.remove(e);
	}
	
	protected void fireChangement() {
		for(EcouteurModele e : this.ecouteurs) {
			e.modeleMisAJour(e);
		}
	}
	
}
