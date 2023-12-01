//Created by Mason Musmacker
//Used to power the UI that shows more employee information once an employee has been clicked on the Teambuilder list

package teamproject;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class TeambuilderAdditionalInfoController implements Initializable {


    @FXML
    private Label nameSpot;

    @FXML
    private Label emailSpot;

    @FXML
    private Label efficiencyData;

    @Override //Unused, too scared to delete though lmao.
    public void initialize(URL location, ResourceBundle resources) {
        // Initialization code, if needed
    }
    //Used to push all data of the previously selected user (from the calling function) to the new window that has been opened.
    public void initData(String name, String email, String data) {
        // Set the data received from the main controller
        nameSpot.setText(name);
        emailSpot.setText(email);
        efficiencyData.setText(data);
    }
}
