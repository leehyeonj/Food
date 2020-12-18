package HealthSchedule.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import HealthSchedule.model.Memo;
import HealthSchedule.model.Weight;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class WeightController extends Main_everydayRecord_controller implements Initializable{
	 
	@FXML private JFXTextField weight;
	@FXML private JFXButton saveWeight;
    @FXML private JFXTextField goalweight;
    @FXML private JFXButton savegoalWeightBtn;
	
	
	//String weight; //몸무게 저장한거
	
	WeightDao weightdao = new WeightDao();
	
	public void initialize(URL location, ResourceBundle resources) {
		
		if (weightdao.ifexistWeight(everyday)) {
			Weight weights = weightdao.viewWeight(everyday);
			int weightinit = weights.getWeight();
			weight.setText(weightinit + "");
		} 
		
		if (weightdao.ifexistgoalWeight(everyday)) {
			Weight weights = weightdao.viewgoalWeight(everyday);
			int goalweightinit = weights.getWeight();			
			goalweight.setText(goalweightinit + "");
		} 
		
	}

	
	public void saveWeight(ActionEvent event) {

		if(!weight.getText().isEmpty()) {
	
				String saveWeight = weight.getText();			
				
				if (!weightdao.ifexistWeight(everyday)) {
					weightdao.saveWeight(everyday, Integer.parseInt(saveWeight));				
	
				}else {
					weightdao.updateWeight(everyday, Integer.parseInt(saveWeight));
					
				}				
	
		}else {
			System.out.println("몸무게를 입력하지 않았습니다.");
		}
				
	}	
	
	public void savegoalWeight(ActionEvent event) {

		if(!weight.getText().isEmpty()) {
	
				String savegoalWeight = goalweight.getText();			
				
				if (!weightdao.ifexistgoalWeight(everyday)) {
					weightdao.savegoalWeight(everyday, Integer.parseInt(savegoalWeight));				
	
				}else {
					weightdao.updategoalWeight(everyday, Integer.parseInt(savegoalWeight));
					
				}				
	
		}else {
			System.out.println("몸무게를 입력하지 않았습니다.");
		}
				
	}
	
	
}
