//Created by Mason Musmacker
//Used to power the UI that shows a non-anonomized list of employees to a manager

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

public class TeambuilderController implements Initializable {

    @FXML
    private ListView<String> userList;

    @Override //Calls everything necissary to make the UI usable by a user. Specifically, fills the list and sets it up to be clicked if desired
    public void initialize(URL location, ResourceBundle resources) {
        populateUserList();
        setListViewClickListener();
    }

        //Sets up the list of employees from the database
    private void populateUserList() {
        // Provide the path to your user database folder
        File userDatabaseFolder = new File("src\\main\\resources\\UserDatabase\\");
    
        // Filter to get only .txt files (user files)
        FilenameFilter textFileFilter = (dir, name) -> name.endsWith(".txt");
    
        // List .txt files in the folder
        String[] userFiles = userDatabaseFolder.list(textFileFilter);
    
        if (userFiles != null) {
            // Extract user names from file names (remove .txt extension)
            for (String userFile : userFiles) {
                String username = userFile.replace(".txt", "");
    
                // Retrieve additional information (e.g., Role, Name, and Skills) using DatabaseInterface
                String role = DatabaseInterface.getUserInfo(username, "Company Role");
                String name = DatabaseInterface.getUserInfo(username, "Name");
                String skills = DatabaseInterface.getUserInfo(username, "Skills");
    
                // Display username, name, and skills in the ListView
                userList.getItems().add(role + " - " + name + " - Skills: " + skills);
            }
        }
    }
    
    //Used to allow the list of employees to register a click and open up more specific information
    private void setListViewClickListener() {
        userList.setOnMouseClicked(event -> {
            // Handle the event when a user is clicked
            giveMoreInfo();
        });
    }

    //The initial function called when a specific employee is selected from the list, used to start opening a new window with more specific information
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
            String email = DatabaseInterface.getUserDataByName(name, "Email");
            String data = DatabaseInterface.getUserDataByName(name, "EfficiencyData");
    
            // Open a new window with teambuilderPage.fxml
            openTeambuilderPage(name, email, data);
            }
        }
    }
    
    

    @FXML //Returns the user back to their profile page
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

            //Opens new window with additional information specific to the selected user
    private void openTeambuilderPage(String name, String email, String data) {
        try {
            // Load the teambuilderPage FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("teambuilderAdditionalInfo.fxml"));
            Parent root = loader.load();

            // Get the controller associated with the teambuilderPage.fxml
            TeambuilderAdditionalInfoController controller = loader.getController();

            // Pass the user information to the controller
            controller.initData(name, email, data);

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
