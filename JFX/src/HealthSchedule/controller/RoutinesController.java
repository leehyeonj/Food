package HealthSchedule.controller;


import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tooltip;

public class RoutinesController  implements Initializable{

	@FXML JFXButton plusbtn;
	
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Tooltip tooltip = new Tooltip("버튼을 클릭하세요");
		plusbtn.setTooltip(tooltip);
	}

	
}
