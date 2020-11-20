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
			//�� ��ư�� ������ onAction�� btnFoodAdd��� ��������
			//getResource�� ���� "morning.fxml"�� �Űܰ�! ��������
			//morningFood��ư�� ������ �� �������� ������ ����
			Parent foodPicker = FXMLLoader.load(getClass().getResource("morning.fxml"));
			Scene scene = new Scene(foodPicker);
			Stage primaryStage= (Stage)ateFoodPick.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (Exception e2) {}
	}

}
