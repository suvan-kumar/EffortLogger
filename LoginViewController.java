//Author: Jayashree Adivarahan & Suvan Kumar
package teamproject;

import teamproject.store; 
import teamproject.DisplayViewController; 

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.Node;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class LoginViewController {
	private Scene scene;
	private Stage stage;
//	public volatile String allstorypts;
	private EffortLoggerConsole console = new EffortLoggerConsole();
	
	@FXML
//	private TextField usernameField;
	private TextField storyPointField;
	@FXML
//	private PasswordField passwordField;
	private TextField additionalInformationField;
	@FXML
//	private PasswordField passwordField;
	private TextField storyNameField;
	
	@FXML
//	private PasswordField passwordField;
	private TextField amountTimeField;
//	private TextField storyNameField;
	
	@FXML
	private TextField effortDataField;
	
	@FXML
	private TextField defectDataField;
/*
//Older version of poker card implementation below
////////vvvvvv******* ASKERS CODE ********vvvvvv////////////

// Made by Asker Dursunov
// code below is creating a card customization Window, which will allow 
// users to create custom cards, by choosing relevant stories that will be
// displayed on the card. The average points of the chosen stories will be 
// displayed on the card.
//
// Functionalities presented will help improving user experience of Planning Poker sessions


private Button openCustomizationButton;
private final Map<Integer, Label> entryLabels = new HashMap<>();
private final Label randomNumberLabel = new Label();

public void openCustomizationMod() {
    BorderPane borderPane = new BorderPane();

    //changing background color to darck green
    borderPane.setStyle("-fx-background-color: #006400;");

    //Title at the top
    Label titleLabel = new Label("Card Customization mod");
    //making changes to the font for a better visibility
    titleLabel.setTextFill(Color.WHITE); 
    titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20)); 
    //Align the title to the top center
    BorderPane.setAlignment(titleLabel, Pos.TOP_CENTER); 
    borderPane.setTop(titleLabel);
    
    //Left side with check boxes and entries
    //checkboxes with the stories are created
    VBox leftVBox = new VBox(20); 
    CheckBox[] checkBoxes = new CheckBox[8];
    for (int i = 0; i < checkBoxes.length; i++) {
        CheckBox checkBox = new CheckBox("Story number " + (i + 1));
        checkBox.setTextFill(Color.WHITE); 
        int finalI = i;
        checkBox.setOnAction(e -> updateCard(finalI, checkBox.isSelected()));
        checkBoxes[i] = checkBox;
        leftVBox.getChildren().add(checkBox);
    }
    borderPane.setLeft(leftVBox);
    
    //Right side pane with a white rectangle (Poker card)
    Pane rightPane = setupCardPane();
    borderPane.setRight(rightPane); 

    //Styling and displaying stage
    Scene scene = new Scene(borderPane, 600, 400);
    Stage newStage = new Stage();
    newStage.setScene(scene);
    newStage.setTitle("Story Selection");
    newStage.show();
}

private Pane setupCardPane() {
    Pane rightPane = new Pane();
    rightPane.setPrefSize(200, 300);

    //positioning card
    Rectangle whiteRectangle = new Rectangle(200, 300);
    whiteRectangle.setFill(Color.WHITE);
    whiteRectangle.setArcWidth(30);
    whiteRectangle.setArcHeight(30);
    whiteRectangle.setTranslateX(-10);
    whiteRectangle.setTranslateY(10);

    //Add the rectangle first
    rightPane.getChildren().add(whiteRectangle);

    //Initialize entryLabels with labels for each entry
    for (int i = 0; i < 8; i++) {
        Label label = new Label("Story number " + (i + 1));
        label.setVisible(false); // Initially hidden
        label.setTextFill(Color.BLACK); // Ensure the label is visible on the white background
        label.setLayoutY(i * 20 + 150); // Position the labels vertically
        label.setLayoutX(50); 
        entryLabels.put(i, label);
        rightPane.getChildren().add(label);
    }

    //creating two red rhombuses  for aesthetics   
    Polygon rhombus = createRhombus(20, 40, 40, 40); //Position and size of the rhombus
    Polygon rhombus2 = createRhombus(163, 280, 40, 40); 
    rightPane.getChildren().add(rhombus);
    rightPane.getChildren().add(rhombus2);
    
    //average displayed on the card
    randomNumberLabel.setTextFill(Color.BLACK); // Set text color
    randomNumberLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18)); // Set font
    randomNumberLabel.setLayoutX(32); //Position X
    randomNumberLabel.setLayoutY(95); //Position Y
    rightPane.getChildren().add(randomNumberLabel); // Add to the pane

    return rightPane;
}

//displaying chosen stories on the card, as well as displaying average of chosen stories
private void updateCard(int entryIndex, boolean isSelected) {
    Label label = entryLabels.get(entryIndex);
    if (label != null) {
        label.setVisible(isSelected); //Show or hide the label based on the checkbox state
    }
    //average displayed on the card
    Random random = new Random();
    int randomNumber = random.nextInt(20); 
    randomNumberLabel.setText("story points:" + randomNumber); //
}

//creating red rhombuses  for aesthetics
private Polygon createRhombus(double centerX, double centerY, double width, double height) {
    Polygon rhombus = new Polygon();
    rhombus.getPoints().addAll(
            centerX, centerY - height / 2, //Top vertex
            centerX + width / 2, centerY, //Right vertex
            centerX, centerY + height / 2, //Bottom vertex
            centerX - width / 2, centerY  //Left vertex
    );
    rhombus.setFill(Color.RED);
    return rhombus;
}


////////^^^^^^******* ASKERS CODE, above **********^^^^^^////////////
*/
	
	public void submitLog(ActionEvent event) throws IOException
	{
		String inputstorypt = storyPointField.getText();
		int number;
		try {
            number = Integer.parseInt(inputstorypt);
        } catch (NumberFormatException e) {
            displayInvalidInputPopup();
            return; // Exit the method if the input is not a valid number
        }
		switch (number) {
		case 0:
		case 1:
        case 2:
        case 3:
        case 5:
        case 8:
        case 13:
        case 21:
            // The number is in the set
            break;
        default:
            displayInvalidInputPopup();
            return;
            //break;
		}
		String oneStoryName = storyNameField.getText();
		if (!(oneStoryName.length()<50)) {
			displayInvalidInputPopupStoryName();
			return;
		}
		String oneAmountTime = amountTimeField.getText();
		if(!(oneAmountTime.matches("\\d*\\.?\\d{0,2}") && Double.parseDouble(oneAmountTime)<=100)) {
			displayInvalidInputPopupAmountTime();
			return;
		}
		String data = store.getAllStoryPoints() + "\n" + storyPointField.getText();
		store.setAllStoryPoints(data);
		String addInfoAll = store.getAllAdditionalInfo() + "\n" + additionalInformationField.getText();
		store.setAllAdditionalInfo(addInfoAll);
		String storyNameAll = store.getAllStoryNames() + "\n" + storyNameField.getText();
		store.setAllStoryNames(storyNameAll);
		String amountTimeAll = store.getAllAmountTimes() + "\n" + amountTimeField.getText();
		store.setAllAmountTimes(amountTimeAll);
		String effortDataAll = store.getAllEffortData() + "\n" + effortDataField.getText();
		store.setAllEffortData(effortDataAll);
		String defectDataAll = store.getAllDefectData() + "\n" + defectDataField.getText();
		store.setAllDefectData(defectDataAll);
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DisplayPage.fxml"));
//		allstorypts = allstorypts + "\n" + storyPointField.getText();
//		console.setStoryPoints(storyPointField.getText());
		console.setStoryPoints(data);
		console.setAdditionalInformation(addInfoAll);
		console.setStoryName(storyNameAll);
		console.setAmountTime(amountTimeAll);
		console.setEffortData(effortDataAll);
		console.setDefectData(defectDataAll);
//		console.setStoryName(storyNameField.getText());

		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(fxmlLoader.load(), 900, 600);
		stage.setTitle("Effort Logger Info");
		
		DisplayViewController control = fxmlLoader.getController();
		control.setLog(console);
//		control.getEffortLoggerConsole(console);
		stage.setScene(scene);
		stage.show();
	}
	
	private void displayInvalidInputPopup() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Invalid Input for Story Point Field");
        alert.setHeaderText(null);
        alert.setContentText("Please enter a valid number from the set {0, 1, 2, 3, 5, 8, 13, 21}.");
        alert.showAndWait();
    }
	private void displayInvalidInputPopupStoryName() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Invalid Input for Story Name Field");
        alert.setHeaderText(null);
        alert.setContentText("Please enter a valid string less than 50 characters in length.");
        alert.showAndWait();
    }
	private void displayInvalidInputPopupAmountTime() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Invalid Input for Amount Time Field");
        alert.setHeaderText(null);
        alert.setContentText("Please enter a valid decimal less than 100 with a maximum of 2 decimal places.");
        alert.showAndWait();
    }

}
