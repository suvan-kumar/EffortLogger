//Author: Jayashree Adivarahan
package teamproject;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
//			store store1 = new store();
			Parent root = FXMLLoader.load(getClass().getResource("EffortLoggerConsolePage.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Effort Logger Console");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		store.setAllStoryPoints("");
		store.setAllAdditionalInfo("");
		store.setAllStoryNames("");
		store.setAllAmountTimes("");
		launch(args);
	}
}