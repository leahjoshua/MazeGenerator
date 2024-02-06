package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SceneController {
	@FXML
	private TextField rowsField;
	@FXML
	private TextField colsField;
	@FXML
	private Label instr;
	
	int rows;
	int cols;
	private Stage stage;

	
	public void switchToScene2(ActionEvent event) throws IOException, InterruptedException {
		try {
            String temp1 = rowsField.getText();
            String temp2 = colsField.getText();
            rows = Integer.parseInt(temp1);
            cols = Integer.parseInt(temp2);
            
            if(rows < 2 || cols < 2 || rows > 25 || cols > 25)
            	throw new NumberFormatException();
            
            Maze maze = new Maze(rows, cols);
    		stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    		stage.setY(0);
    		maze.generateMaze(stage);
        }
        catch (NumberFormatException e) {
            instr.setText("Invalid Input !");
        }
	}
}
