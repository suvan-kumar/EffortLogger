//Created by Mason Musmacker
//Used to power the UI that shows a anonomized list of employees to an employee
package teamproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AnonTeamBuilderController implements Initializable {

    @FXML
    private ListView<String> userList;


    //Loads the UI with the proper information
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        populateUserList();
        setListViewClickListener();
    }

    //Method called by initialize to help fill the list with useful identifier information
    private void populateUserList() {
        // Provide the path to your user database folder
        File userDatabaseFolder = new File("src\\main\\resources\\UserDatabase\\");
    
        // Filter to get only .txt files (user files)
        FilenameFilter textFileFilter = (dir, name) -> name.endsWith(".txt");
    
        // List .txt files in the folder
        String[] userFiles = userDatabaseFolder.list(textFileFilter);
        int maxNameLength = 10;

        if (userFiles != null) {
            // Extract user names from file names (remove .txt extension)
            for (String userFile : userFiles) {
                String username = userFile.replace(".txt", "");
    
                // Retrieve additional information (e.g., Role, Name, and Skills) using DatabaseInterface
                String role = DatabaseInterface.getUserInfo(username, "Company Role");
                String name = DatabaseInterface.getUserInfo(username, "ID");
                String skills = DatabaseInterface.getUserInfo(username, "Skills");

                // Display username, name, and skills in the ListView
                userList.getItems().add(role + " - " + name + " - Skills: " + skills);
            }
        }
    }
    
    //Sets up the UI's capability to detect when a specific employee on the list is clicked
    private void setListViewClickListener() {
        userList.setOnMouseClicked(event -> {
            // Handle the event when a user is clicked
            giveMoreInfo();
        });
    }

    //Creates a new window with more specific information of an employee for inspection
    private void giveMoreInfo() {
        // Retrieve the selected user from the ListView
        String selectedUser = userList.getSelectionModel().getSelectedItem();
    
        // Check if a user is selected
        if (selectedUser != null) {
            // Split the selected user string using hyphen as a delimiter
            String[] userParts = selectedUser.split(" - ");
    
            // Ensure that the array has at least 2 parts
            if (userParts.length >= 2) {
                // The second part is the name
                String name = userParts[1];
            // Check if a user is selected
        
            // Implement your logic to retrieve more information about the selected user
            // You can use your existing methods from DatabaseInterface
            String skills = DatabaseInterface.getUserDataByID(name, "Skills");
            String data = DatabaseInterface.getUserDataByID(name, "EfficiencyData");
    
            // Open a new window with teambuilderPage.fxml
            openTeambuilderPage(name, skills, data);
            }
        }
    }
    
    

    @FXML //Brings the user back to their profile
    private void returnToProfile() throws IOException {
        Stage stage = App.getPrimaryStage();
        if (stage != null) {
            stage.setTitle("Profile");
    
            // Set the size of the profile page
            stage.setWidth(800); // Set your preferred width
            stage.setHeight(600); // Set your preferred height
        }
        
            // Switch to the profile page
            App.setRoot("profilePage");
        }

        //Opens the additional info page (called by giveMoreInfo)
    private void openTeambuilderPage(String id, String skills, String data) {
        try {
            // Load the teambuilderPage FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("anonTeambuilderAdditionalInfo.fxml"));
            Parent root = loader.load();

            // Get the controller associated with the teambuilderPage.fxml
            AnonTeamBuilderAdditionalInfoController controller = loader.getController();

            // Pass the user information to the controller
            controller.initData(id, skills, data);

            // Create a new stage for the teambuilderPage
            Stage teambuilderStage = new Stage();
            teambuilderStage.setTitle("Additional Info");
            teambuilderStage.initModality(Modality.APPLICATION_MODAL); // Prevent interaction with other windows
            teambuilderStage.setScene(new Scene(root));

            // Show the teambuilderPage
            teambuilderStage.showAndWait(); // This will block until the teambuilder stage is closed
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
