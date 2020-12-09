package HealthSchedule.controller;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

public class RoutinesController extends MakeRoutineController  implements Initializable{

	@FXML JFXButton plusbtn;
	@FXML AnchorPane home;
	
	public RoutinesController() {
		this.column = super.column;
		this.row = super.row;
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	public void plusaction(ActionEvent event) {
		
		try {
			
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/HealthSchedule/resources/routines.fxml"));
				AnchorPane anchorPane = fxmlLoader.load();
				
				if(column == 1) {
					column = 0;
					row++;
				}
				
				super.grid.add(anchorPane, column++, row);
				  //set grid width
				super.grid.setMinWidth(Region.USE_COMPUTED_SIZE);
				super. grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
				super.grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
				super.grid.setMinHeight(Region.USE_COMPUTED_SIZE);
				super.grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
				super.grid.setMaxHeight(Region.USE_PREF_SIZE);
				
				GridPane.setMargin(anchorPane, new Insets(10));
			
		} catch (Exception e) {
			// TODO: handle exception
		}

		
	
	
	}

	
}
