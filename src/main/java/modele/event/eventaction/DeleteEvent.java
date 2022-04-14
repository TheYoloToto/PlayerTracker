package modele.event.eventaction;

import javafx.scene.control.TableView;
import modele.commande.CommandeSuppression;
import modele.joueur.JoueurFx;
import service.GestionnaireCommandeService;
import service.ServiceManager;

public class DeleteEvent extends RunnableEvent {

	private GestionnaireCommandeService gestionnaireCommandeService = ServiceManager.getInstance(GestionnaireCommandeService.class);

	public DeleteEvent(TableView<JoueurFx> table) {
		super(table);
	}

	@Override
	public Void execute() {
		int selectedIndex = table.getSelectionModel().getSelectedIndex();
		if (selectedIndex < 0) {
			return null;
		}
		JoueurFx joueur = table.getItems().get(selectedIndex);
		gestionnaireCommandeService.addCommande(new CommandeSuppression(table, joueur)).executer();
		return null;
	}

}