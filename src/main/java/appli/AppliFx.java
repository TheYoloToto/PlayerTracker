package appli;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import service.AlertFxService;
import service.FileManager;
import service.ServiceManager;
import service.StageManager;
import service.TrayIconService;

public class AppliFx extends Application {

	private FileManager fm;
	private TrayIconService trayIconService;
	private AlertFxService alertService;

	public static void start(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws IOException {
		try {
			checkAlreadyRunning();
		} catch (ApplicationDejaEnCoursException e) {
			alertService.alert(e);
			return;
		}
		initService(stage);
		stage.getIcons().add(fm.getImageFromResource("images/loupe.PNG"));
		stage.setTitle("Player tracker");
		var file = fm.getFileFromResources("fxml/page_principale.fxml");
		var loader = new FXMLLoader(file.toURI().toURL());
		Parent sceneVideo = loader.load();
		var scene = new Scene(sceneVideo);
		stage.setScene(scene);
		trayIconService.createFXTrayIcon(stage);
		stage.show();
	}

	private void initService(Stage stage) {
		fm = ServiceManager.getInstance(FileManager.class);
		trayIconService = ServiceManager.getInstance(TrayIconService.class);
		alertService = ServiceManager.getInstance(AlertFxService.class);
		ServiceManager.getInstance(StageManager.class).setCurrentStage(stage);
	}

	private void checkAlreadyRunning() throws ApplicationDejaEnCoursException {
		try(var s = new ServerSocket(1044, 0, InetAddress.getByName("localhost"));) {
			// teste juste la possibilité d'ouvrir un serveur à cette adresse
		} catch (IOException e1) {
			throw new ApplicationDejaEnCoursException();
		}
		var t = new Thread(() -> {
			try(var server = new ServerSocket(1044, 0, InetAddress.getByName("localhost"));) {
				server.accept();
			} catch (IOException e) {
				alertService.alert(e);
			}
		});
		t.setDaemon(true);
		t.start();
	}

}