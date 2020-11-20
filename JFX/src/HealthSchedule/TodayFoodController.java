package HealthSchedule;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class TodayFoodController implements Initializable{

	@FXML private Button ateFoodPick;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ateFoodPick.setOnAction(e->btnFoodAdd(e));
		
	}
	
	public void btnFoodAdd(ActionEvent event) {
		try {
			//이 버튼을 누르는 onAction을 btnFoodAdd라고 설정했음
			//getResource에 들어가는 "morning.fxml"이 옮겨갈! 페이지임
			//morningFood버튼을 누르면 그 페이지가 나오는 것임
			Parent foodPicker = FXMLLoader.load(getClass().getResource("morning.fxml"));
			Scene scene = new Scene(foodPicker);
			Stage primaryStage= (Stage)ateFoodPick.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (Exception e2) {}
	}

}
