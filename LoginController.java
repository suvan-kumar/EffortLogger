//Created by Mason Musmacker
//Powers the login screen UI. Allows a user to create a new profile if they don't have one already or to login if they do.
package teamproject;

import java.io.File;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoginController {
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    private static final String DATABASE_FOLDER = "src\\main\\resources\\UserDatabase\\";
    
    @FXML  //This is used to allow a user to actually log into the system and be recognized as the current user. Has some security features built in
    private void switchToProfile() throws IOException {
        // Set the title dynamically
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        if(DatabaseInterface.searchDatabaseForUser(username)){
            if(password.equals(DatabaseInterface.getUserInfo(username, "Password"))){
                try {
                    User currentUser = new User(username);
                    App.setCurrentUser(currentUser);
                    Stage stage = App.getPrimaryStage();
                    if (stage != null) {
                        stage.setTitle("Profile");
                
                        // Set the size of the profile page
                        stage.setWidth(800); // Set preferred width
                        stage.setHeight(600); // Set preferred height
                    }
                    // Switch to the profile page
                    App.setRoot("profilePage");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                showAlert("Incorrect Password", "The password entered is not the password for this account. Double check if you entered it right. Passwords are case-sensitive.");
                return;
            }
        }else{
            showAlert("User Doesn't Exist", "The username you entered isn't in our files. Double check if you entered it right. Usernames are case-sensitive.");
            return;
        }
    }
    
    @FXML //This opens the page used to add a new user to the database
    private void openNewUserPage() {
        try {
            // Load the new user page FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("NewUserPage.fxml"));
            Parent root = loader.load();
            // Create a new stage for the new user page
            Stage newUserStage = new Stage();
            newUserStage.setTitle("New User Page");
            newUserStage.initModality(Modality.APPLICATION_MODAL); // Prevent interaction with other windows
            newUserStage.setScene(new Scene(root));
            // Show the new user page
            newUserStage.showAndWait(); // This will block until the new stage is closed
            // Code here will continue after the new user stage is closed
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        //checks the database to see if a user exists
    private boolean userExists(String userName) {
        // Construct the full path including the folder and filename
        String fullPath = DATABASE_FOLDER + userName + ".txt";

        // Check if the file already exists
        File file = new File(fullPath);
        return file.exists();
    }

    //Used to generate a warning message of any kind
    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
