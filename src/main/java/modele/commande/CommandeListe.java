package modele.commande;

import java.util.Arrays;
import java.util.List;

import javafx.scene.control.TableView;
import modele.joueur.JoueurFx;

/**
 * @author ronan
 *
 */
public abstract class CommandeListe extends CommandeTable {

	protected List<JoueurFx> listeJoueurs;

	protected CommandeListe(TableView<JoueurFx> table, List<JoueurFx> listeJoueurs) {
		super(table);
		this.listeJoueurs = listeJoueurs;
	}

	protected CommandeListe(TableView<JoueurFx> table, JoueurFx joueur) {
		super(table);
		listeJoueurs = Arrays.asList(joueur);
	}

}
