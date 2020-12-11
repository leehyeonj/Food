package HealthSchedule.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class Routine_SaveController extends Routine_lowerbodyController implements Initializable{
    @FXML
    private JFXTextField writeTextField;

    @FXML
    private JFXButton saveBtn;

    String todayVideoWatched;

   

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
