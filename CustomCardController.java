//previous/older version poker card implementation
package teamproject;

import teamproject.store; 

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.Node;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;


public class CustomCardController {

    
    private VBox checkBoxContainer; // This should be a container in your FXML for checkboxes
  
    private Label cardDisplay; // This is where you would display the average story points

    // Call this method when the page is opened to populate the checkboxes
   
    // Calculate and update the average on the card
    private void updateAverage() {
        double sum = 0;
        int count = 0;

        for (Node node : checkBoxContainer.getChildren()) {
            if (node instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) node;
                if (checkBox.isSelected()) {
                    String storyName = checkBox.getText();
                    // Assume you have a method to get the story points by name
                    int points = getStoryPoints(storyName); 
                    sum += points;
                    count++;
                }
            }
        }

        double average = (count > 0) ? sum / count : 0;
        cardDisplay.setText("Average: " + average);
        // Update the card display further based on your needs.
    }

    private int getStoryPoints(String storyName) {
        // Implement this method to return story points based on the story name
        return 0;
    }
}
