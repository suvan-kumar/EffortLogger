//Created by Mason Musmacker
//Used to power the UI that shows the user their personal information. Also acts as a bridge between program functions
package teamproject;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.stage.Stage;

public class ProfileController implements Initializable{
    @FXML
    private Label idLabel;
    @FXML
    private Label roleLabel;
    @FXML
    private Label skillsLabel;
    @FXML
    private Label efficiencyDataLabel;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label phoneLabel;

    @Override //Sets up the UI with the data of the current user
    public void initialize(URL arg0, ResourceBundle arg1) {
        // Retrieve the current user from the App class
        User currentUser = App.getCurrentUser();

        // Set labels with user information
        if (currentUser != null) {
            nameLabel.setText("Name: " + DatabaseInterface.getUserInfo(currentUser.getUsername(), "Name"));
            emailLabel.setText("Email: " + DatabaseInterface.getUserInfo(currentUser.getUsername(), "Email"));
            phoneLabel.setText("Phone Number: " + DatabaseInterface.getUserInfo(currentUser.getUsername(), "PhoneNumber"));
            idLabel.setText("ID: " + DatabaseInterface.getUserInfo(currentUser.getUsername(), "ID")); // You can modify this based on your actual ID data
            roleLabel.setText("Role: " + DatabaseInterface.getUserInfo(currentUser.getUsername(), "Company Role"));
            skillsLabel.setText("Skills: " + DatabaseInterface.getUserInfo(currentUser.getUsername(), "Skills"));
            efficiencyDataLabel.setText("Efficiency Data: " + DatabaseInterface.getUserInfo(currentUser.getUsername(), "EfficiencyData"));

            // Set private data labels
            usernameLabel.setText("Username: " + currentUser.getUsername());
            // Add similar lines for other private data
        }
    }

    @FXML //Signs out of the account and returns to login
    private void switchLoginPage() throws IOException {
        // Set the title dynamically
        Stage stage = App.getPrimaryStage();
        if (stage != null) {
            stage.setTitle("Login");
        }
        App.setCurrentUser(null);
        App.setRoot("loginPage");
    }

    @FXML //Opens either an anonomized teambuilder or a non-anonomized teambuilder depending on the role of the user. Manager or employee.
    private void switchToTeambuilder() throws IOException {
        User currentUser = App.getCurrentUser();
        // Get the role of the current user
        String currentUserRole = DatabaseInterface.getUserInfo(currentUser.getUsername(), "Company Role");
    
        // Check if the current user is a Manager
        if ("Manager".equals(currentUserRole)) {
            // Set the title dynamically
            Stage stage = App.getPrimaryStage();
            if (stage != null) {
                stage.setTitle("Teambuilder");
            }
            App.setRoot("teambuilderPage");
        } else {
            // Set the title dynamically
            Stage stage = App.getPrimaryStage();
            if (stage != null) {
                stage.setTitle("Anonymous Teambuilder");
            }
            App.setRoot("anonTeambuilder");
        }
    }
    @FXML //Opens planning poker
    private void switchToPlanningPoker() throws IOException {
//    	
//    	
//    	// Set the title dynamically
//
//        //---------FOR TEAM MEMBERS--------//
//        //This is where you hook up your FXML page. replace "planningPokerPage" with the name of your fxml file and make sure your other files are in the "teamproject" folders
//        //---------------------------------//
//
//        //Stage stage = App.getPrimaryStage();
//        //if (stage != null) {
//        //    stage.setTitle("Planning Poker");
//        //}
//        //App.setRoot("planningPokerPage");
//        System.out.print("PlaceholderFunction");
    	
    	try {
			Stage stage = App.getPrimaryStage();
			Parent root = FXMLLoader.load(getClass().getResource("/teamproject/EffortLoggerConsolePage.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Effort Logger Console");
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }


}