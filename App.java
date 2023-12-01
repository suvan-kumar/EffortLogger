//Created by Mason Musmacker
//Used as the base of the program. Starts up at the login screen. Effectively is the "Main"
package teamproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Stage primaryStage;
    private static User currentUser;

    @Override //opens up the Login screen for the user
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        scene = new Scene(loadFXML("loginPage"), 700, 400);
        stage.setMaxWidth(750);
        stage.setMaxHeight(400);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    //sets the root scene for the application
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    //allows the main stage to be retrieved
    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    //returns the loader of the fxml page
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    //Sets the current user, called for Login. Used to verify identity
    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    //Returns the current user, used for verifying identity
    public static User getCurrentUser() {
        return currentUser;
    }

    //You.. shouldn't need an explaination on this. It's main, it's required to get everything started
    public static void main(String[] args) {
        launch();
    }
}
