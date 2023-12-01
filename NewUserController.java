//Created by Mason Musmacker
//Used to power the UI that adds a new user to the database

package teamproject;

import teamproject.DatabaseInterface;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewUserController implements Initializable{
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private TextField confirmPasswordTextField;
    @FXML
    private ChoiceBox<String> roleChoice;
    private String[] roles = {"Employee", "Manager"};
    private static final String DATABASE_FOLDER = "src\\main\\resources\\UserDatabase\\";
    

    @Override //Sets up the UI to have all the necissary information for the user to pick from
    public void initialize(URL arg0, ResourceBundle arg1){
        roleChoice.getItems().addAll(roles);
    }

    //Closes the window when its called
    private void closeStage() {
        Stage stage = (Stage) usernameTextField.getScene().getWindow();
        stage.close();
    }
    
    @FXML //Has some security features. If passed, takes the information put into the text boxes by the user and generates a new employee
    private void finalizeNewUser() {
        String username = usernameTextField.getText();
        String email = emailTextField.getText();
        String password = passwordTextField.getText();
        String confirmPassword = confirmPasswordTextField.getText();
        String companyRole = roleChoice.getValue();
    
        if (userExists(username)) {
            showAlert("User Exists", "That user already exists. If you've forgotten your password, contact HR (Or, have a system for it).");
            return;
        }
        if (!password.equals(confirmPassword)) {
            showAlert("Different Passwords", "Your password and confirmation don't match. Please double-check to make sure you entered everything correctly.");
            return;
        }
        if (username.matches(".*[^a-zA-Z0-9].*") || 
                password.matches(".*[^a-zA-Z0-9].*") || 
                email.matches(".*[^a-zA-Z0-9.@].*")) {
                showAlert("Invalid Characters", "Please use only digits and characters for your username and password");
                return;
            }
        String newID = generateID(username);
        DatabaseInterface.addUserToDatabase(username, password, email, companyRole, newID);
        closeStage();
        
    }
    
    //Generates a personalized ID based on the username
    public static String generateID(String username) {
        try {
            // Use the username and some random components to create a unique identifier
            String combinedString = username + System.currentTimeMillis() + generateRandomString();
            byte[] bytes = combinedString.getBytes("UTF-8");

            // Use SHA-256 for hashing
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(bytes);

            // Convert the hash to a hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

        //Generates a random string (used for ID creation)
    private static String generateRandomString() {
        // Generate a random string (you can customize this based on your requirements)
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[8];
        random.nextBytes(bytes);
        return bytesToHex(bytes);
    }

    //Another part of the randomID maker
    private static String bytesToHex(byte[] bytes) {
        // Convert bytes to hexadecimal string
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    //Checks to see if the user with the given username exists in the database
    private boolean userExists(String userName) {
        // Construct the full path including the folder and filename
        String fullPath = DATABASE_FOLDER + userName + ".txt";

        // Check if the file already exists
        File file = new File(fullPath);
        return file.exists();
    }

    //Allows for the generation of any warning, pops up a new window
    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
