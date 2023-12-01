//Author: Jayashree Adivarahan
package teamproject;

import teamproject.store; 

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
public class PokerCardController {
	private Scene scene;
	private Stage stage;
	
	@FXML
	private Label storyNums;
	
	@FXML
	private Label storyNames;
	
	@FXML
	private Label storyPoints;
	
	@FXML
	private Label averageStoryPoints;
	
//	@FXML
//	private Label passwordLength;
	private PokerCardConsole pokerconsole;
	private EffortLoggerConsole console;
	
	private String[] storyNameArray = new String[25];
	private String[] storyPointsArray = new String[25];
	private String storyNamesToDisplay;
	private String storyPointsToDisplay;
	
	public void setPoker(PokerCardConsole pokerconsole, EffortLoggerConsole console)
	{
		//defining some variables to help calculate average story point value
		int sumAll = 0;
		int numStories = 0;
		int storynumbercurrent = 0;
		
		//initializing effort logger console and poker card console (using the get methods for the effort logger console will
		//return/contain all of the data the user has entered so far in this planning poker/effort logger session
		//only certain entries are selected from 'all of the user's data' here; in other words, no new object is created or sed
		this.console = console;
		this.pokerconsole=pokerconsole;
		
		//setting the story numbers on the FXML page, displays to the users the story numbers they entered
		storyNums.setText("Story Numbers: " + pokerconsole.getStoryNums());
		//converting story numbers entered from string to array of ints
		String[] storyNumbersArray = (pokerconsole.getStoryNums()).split(",");
		int[] storynums = new int[storyNumbersArray.length];
		for (int i = 0; i < storyNumbersArray.length; i++) {
			storynums[i] = Integer.parseInt(storyNumbersArray[i]);
        }
		
		//getting story points and story names entered by the user so far (all data)
		String storyPointsSoFar = console.getStoryPoints();
		storyPointsArray = storyPointsSoFar.split("\\n");
		String storyNamesSoFar = console.getStoryName();
		storyNameArray = storyNamesSoFar.split("\\n");
		
		//initializing strings to just contain information associated with the story numbers the user entered
		storyPointsToDisplay = "Select Story Points for Poker Card: \n";
		storyNamesToDisplay = "";
		//for loop appends information relevant only to the story numbers entered, loop also updates variables needed for average story point calculation
		for(int num: storynums) {
			storyPointsToDisplay = storyPointsToDisplay + storyPointsArray[num] + "\n";
			storyNamesToDisplay = storyNamesToDisplay + storyNameArray[num] + "\n";
			numStories = numStories+1;
			storynumbercurrent = Integer.parseInt(storyPointsArray[num]);
			sumAll = sumAll + storynumbercurrent;
		}
		//display story names and points associated only with the entered story numbers
		storyNames.setText(storyNamesToDisplay);
		storyPoints.setText(storyPointsToDisplay);
		
		//calculating average to display
		double average = (double) sumAll/numStories;
		averageStoryPoints.setText("" + average);
	}
	
	//back to display page button click event handler, sets display page to original/base form
	public void backToDisplayPage(ActionEvent event) throws IOException
	{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DisplayPage.fxml"));

		console.setStoryPoints(store.getAllStoryPoints());
		console.setAdditionalInformation(store.getAllAdditionalInfo());
		console.setStoryName(store.getAllStoryNames());
		console.setAmountTime(store.getAllAmountTimes());
		console.setEffortData(store.getAllEffortData());
		console.setDefectData(store.getAllDefectData());

		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(fxmlLoader.load(), 900, 600);
		stage.setTitle("Effort Logger Info");
		
		DisplayViewController control = fxmlLoader.getController();
		control.setLog(console);
//		control.getEffortLoggerConsole(console);
		stage.setScene(scene);
		stage.show();
	}
	@FXML
	private void shareButton(ActionEvent event) {
	    Alert alert = new Alert(Alert.AlertType.INFORMATION);
	    alert.setTitle("poker session");
	    alert.setHeaderText("Waiting for team members");
	    alert.setContentText("1 out of 8 people are ready...");

	    alert.showAndWait();
	}

}