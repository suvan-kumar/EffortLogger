//Created by Mason Musmacker
//Used to power the UI that shows more employee information once an employee has been clicked on the Anonomized Teambuilder list
package teamproject;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class AnonTeamBuilderAdditionalInfoController implements Initializable {


    @FXML
    private Label idSpot;

    @FXML
    private Label skillSpot;

    @FXML
    private Label efficiencyDataSpot;

    //Sets up the UI with the information needed (Not actually used, but I'm lowkey scared to delete it with how many hours I spent coding this)
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialization code, if needed
    }

    //Sets up the UI with the information needed
    public void initData(String id, String skills, String data) {
        // Set the data received from the main controller
        idSpot.setText(id);
        skillSpot.setText(skills);
        efficiencyDataSpot.setText(data);
    }
}