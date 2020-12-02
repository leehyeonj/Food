package HealthSchedule.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Food_addMyDiet_controller implements Initializable{

	@FXML private JFXButton record;
	@FXML private AnchorPane pane;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public void recordDiet(MouseEvent event) {
		try {
			//목표 몸무게를 클릭하면 넘어간다.
			Parent checkOk = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/main_everydayRecord.fxml"));
			Scene scene = new Scene(checkOk);
			Stage primaryStage= (Stage)record.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (Exception e2) {}
	}
	

}
