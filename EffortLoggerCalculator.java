//previous/older version poker card implementation
package teamproject;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class EffortLoggerCalculator extends Application {

    private double totalWeightedSum = 0;
    private double totalWeight = 0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("EffortLogger Calculator");

        // Create UI elements
        TextField valueField = new TextField();
        TextField weightField = new TextField();
        Button addButton = new Button("Add Value");
        Button removeButton = new Button("Remove Value");
        Button calculateButton = new Button("Calculate Weighted Average");
        Button clearButton = new Button("Clear");

        TextArea resultArea = new TextArea();
        resultArea.setEditable(false);

        // Layout setup
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        // Add UI elements to the grid
        grid.add(new Label("Enter Value:"), 0, 0);
        grid.add(valueField, 1, 0);
        grid.add(new Label("Enter Weight:"), 0, 1);
        grid.add(weightField, 1, 1);
        grid.add(addButton, 2, 0);
        grid.add(removeButton, 3, 0);
        grid.add(calculateButton, 1, 2);
        grid.add(clearButton, 2, 2);
        grid.add(new Label("Result:"), 0, 3);
        grid.add(resultArea, 1, 3, 3, 1);

        // Event handlers
        addButton.setOnAction(e -> addValue(valueField, weightField, resultArea));
        removeButton.setOnAction(e -> removeValue(valueField, weightField, resultArea));
        calculateButton.setOnAction(e -> calculateWeightedAverage(resultArea));
        clearButton.setOnAction(e -> clearFields(valueField, weightField, resultArea));

        // Scene setup
        Scene scene = new Scene(grid, 400, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addValue(TextField valueField, TextField weightField, TextArea resultArea) {
        try {
            double value = Double.parseDouble(valueField.getText());
            double weight = Double.parseDouble(weightField.getText());

            totalWeightedSum += value * weight;
            totalWeight += weight;

            resultArea.appendText("Added Value: " + value + " Weight: " + weight + "\n");
        } catch (NumberFormatException ex) {
            resultArea.appendText("Invalid input. Please enter numeric values for both Value and Weight.\n");
        }
        valueField.clear();
        weightField.clear();
    }

    private void removeValue(TextField valueField, TextField weightField, TextArea resultArea) {
        resultArea.appendText("Remove Value: " + valueField.getText() + " Weight: " + weightField.getText() + "\n");
        valueField.clear();
        weightField.clear();
    }

    private void calculateWeightedAverage(TextArea resultArea) {
        if (totalWeight != 0) {
            double weightedAverage = totalWeightedSum / totalWeight;
            resultArea.appendText("Weighted Average: " + weightedAverage + "\n");
        } else {
            resultArea.appendText("Cannot calculate weighted average. Total weight is zero.\n");
        }
    }

    private void clearFields(TextField valueField, TextField weightField, TextArea resultArea) {
        valueField.clear();
        weightField.clear();
        resultArea.clear();
        totalWeightedSum = 0;
        totalWeight = 0;
    }
}