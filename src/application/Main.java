package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Main extends Application {
	
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("Scene1.fxml"));
		Scene scene1 = new Scene(root);
		scene1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene1);
		
		stage.setResizable(false);
		stage.show();
	}
}
