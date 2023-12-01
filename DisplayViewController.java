//Author: Jayashree Adivarahan
//Edited by: Mason Musmacker & Suvan Kumar
package teamproject;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
public class DisplayViewController {
	private Scene scene;
	private Stage stage;
	
	private PokerCardConsole pokerconsole = new PokerCardConsole();
	
	@FXML
//	private Label username;
	private Label storyPoints;
	@FXML
//	private Label password;
	private Label additionalInformation;
	@FXML
//	private Label password;
	private Label storyName;
	@FXML
//	private Label password;
	private Label amountTime;
	@FXML
//	private Label password;
	private TextField searchEntryField;
	@FXML
	private TextField deleteEntryField;
	
	@FXML
	private TextField infoEntryField;
	
	@FXML
	private TextField pokerCardStoryEntryField;
	
//	@FXML
//	private Label passwordLength;
	private EffortLoggerConsole console;
	//current limit on num entries is 25 for user, can modify this later if needed
	private String[] additionalInformationArray = new String[25];
	private String[] storyNameArray = new String[25];
	private String[] amountTimeArray = new String[25];
	private String[] storyPointsArray = new String[25];
	private String[] defectDataArray = new String[25];
	private String[] effortDataArray = new String[25];
	
	
	public void setLog(EffortLoggerConsole console)
	{
		this.console=console;
		storyPoints.setText("Story Points: \n" + console.getStoryPoints());
		additionalInformation.setText("Additional Information: \n" + console.getAdditionalInformation());
		storyName.setText("Story Name: \n" + console.getStoryName());
		amountTime.setText("Amount of Time Taken: \n" + console.getAmountTime());
	}
	
	public void backToEffortLogger(ActionEvent event) throws IOException
	{
//		store1.setAllStoryPoints(storyPoints.getText());
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EffortLoggerConsolePage.fxml"));

		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(fxmlLoader.load(), 900, 600);
		stage.setTitle("Effort Logger Console");
		
//		DisplayViewController control = fxmlLoader.getController();
		stage.setScene(scene);
		stage.show();
	}
	
	//to navigate to the poker card page, goes to the poker card fxml page, base set up to link display page and poker card page
	public void customizePokerCard(ActionEvent event) throws IOException
	{
		String pokerCardStoryNums = pokerCardStoryEntryField.getText();
		
		//check if in form number, number, number... etc //TODO: add check to ensure numbers are in 'range' meaning 
		//all nums entered are less than the number of stories/logs entered by the user, no duplicates in story nums
		if(!(pokerCardStoryNums.matches("\\d+(,\\d+)*") )) {
			displayInvalidInputPopupStoryNumList();
			return;
		}
		store.setStoryNumsPokerCard(pokerCardStoryNums);
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PokerCardCustomizationPage.fxml"));
		
		pokerconsole.setStoryNums(pokerCardStoryNums);

		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(fxmlLoader.load(), 900, 600);
		stage.setTitle("Poker Card Customization Page");
		
		PokerCardController controlpoker = fxmlLoader.getController();
		try {
			controlpoker.setPoker(pokerconsole, console);
			}
			catch (ArrayIndexOutOfBoundsException e) {
		        // Create and display an alert dialog
		        Alert alert = new Alert(Alert.AlertType.ERROR);
		        alert.setTitle("Invalid Story Number");
		        alert.setHeaderText(null);
		        alert.setContentText("Error: Story number " + " is out of bounds.");
		        alert.showAndWait();
		        return;
		    }
		stage.setScene(scene);
		stage.show();
	}
	
	//input validation checks for list of numbers entered; TODO: Note, validation might need to be expanded to also
	//ensure that a story number greater than the number of stories isn't entered, that duplicates aren't entered etc
	private void displayInvalidInputPopupStoryNumList() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Invalid Input for Story Number Field");
        alert.setHeaderText(null);
        alert.setContentText("Please enter a valid input in the form: storyNumber1, storyNumber2, ...etc");
        alert.showAndWait();
    }
	
	//make function to split the string (for now integrate in the search function, then maybe make it separate?)
	public void splitToArray() {
		String additionalInformationSoFar = console.getAdditionalInformation();
		additionalInformationArray = additionalInformationSoFar.split("\\n");
		String storyPointsSoFar = console.getStoryPoints();
		storyPointsArray = storyPointsSoFar.split("\\n");
		String storyNamesSoFar = console.getStoryName();
		storyNameArray = storyNamesSoFar.split("\\n");
		String amountTimeSoFar = console.getStoryName();
		amountTimeArray = amountTimeSoFar.split("\\n");
		String defectDataSoFar = console.getDefectData();
		defectDataArray = defectDataSoFar.split("\\n");
		String effortDataSoFar = console.getEffortData();
		effortDataArray = effortDataSoFar.split("\\n");
	}
	
	public void search(ActionEvent event) throws IOException
	{
//		store1.setAllStoryPoints(storyPoints.getText());
//		storyPoints.setText("Story Points: \n TESTING");
		String searchText = searchEntryField.getText();
		splitToArray();
		String updatedAddInfo = "";
		String updatedStoryPoints = "";
		String updatedStoryNames = "";
		String updatedAmountTimeSoFar = "";
		
		for (int i = 0; i<additionalInformationArray.length; i++) {
            if(additionalInformationArray[i].contains(searchText) || storyNameArray[i].contains(searchText)) {
            	//include this entry, otherwise exclude
            	updatedAddInfo = updatedAddInfo + additionalInformationArray[i] + "\n";
            	updatedStoryPoints = updatedStoryPoints + storyPointsArray[i] + "\n";
            	updatedStoryNames = updatedStoryNames + storyNameArray[i] + "\n";
            	updatedAmountTimeSoFar = updatedAmountTimeSoFar + amountTimeArray[i] + "\n";
            }
        }
		additionalInformation.setText("Additional Information: \n" + updatedAddInfo);
		storyPoints.setText("Story Points: \n" + updatedStoryPoints);
		storyName.setText("Story Name: \n" + updatedStoryNames);
		amountTime.setText("Amount of Time Taken: \n" + updatedAmountTimeSoFar);
	}
	
	//Method to delete a log from the entry when selected
	public void delete(ActionEvent event) throws IOException {
		String delPos = deleteEntryField.getText();
		try {
		    int position = Integer.parseInt(delPos);
		    // Now 'position' is an integer value
		    // You can use 'position' in your code as an integer
			splitToArray();
			String updatedAddInfo = "";
			String updatedStoryPoints = "";
			String updatedStoryNames = "";
			String updatedAmountTimeSoFar = "";
			String updatedEffortSoFar = "";
			String updatedDefectSoFar = "";
			
			for(int i = 0; i < additionalInformationArray.length; i++) {
				if(i != position) {
	            	updatedAddInfo = updatedAddInfo + additionalInformationArray[i] + "\n";
	            	updatedStoryPoints = updatedStoryPoints + storyPointsArray[i] + "\n";
	            	updatedStoryNames = updatedStoryNames + storyNameArray[i] + "\n";
	            	updatedAmountTimeSoFar = updatedAmountTimeSoFar + amountTimeArray[i] + "\n";
	            	updatedEffortSoFar = updatedEffortSoFar + effortDataArray[i] + "\n";
	            	updatedDefectSoFar = updatedDefectSoFar + defectDataArray[i] + "\n";
				}
			}
			additionalInformation.setText("Additional Information: \n" + updatedAddInfo);
			storyPoints.setText("Story Points: \n" + updatedStoryPoints);
			storyName.setText("Story Name: \n" + updatedStoryNames);
			amountTime.setText("Amount of Time Taken: \n" + updatedAmountTimeSoFar);
		} catch (NumberFormatException e) {
		    // Handle the case where 'delPos' is not a valid integer
		    System.err.println("Input is not a valid integer");
		}
	}
	
	public void moreInfo(ActionEvent event) throws IOException {
		String infoPos = infoEntryField.getText();
		int position = Integer.parseInt(infoPos);
		splitToArray();
		String defect = defectDataArray[position];
		String effort = effortDataArray[position];
		
		Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("More Information for " + storyNameArray[position]);
        alert.setHeaderText(null);
        alert.setContentText("Defect data: " + defect + "\n" + "Effort data: " + effort);
        alert.showAndWait();
	
	}
}