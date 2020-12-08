package HealthSchedule.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXScrollPane;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.util.Duration;

public class MakeRoutineController implements Initializable{


	@FXML ScrollPane scroll;
	@FXML GridPane grid;
//	@FXML JFXButton plusbtn;
	public int column = 0;
	public int row = 1;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
//		int column = 0;
//		int row = 1;
//		
//		try {
//			for(int i = 0; i< 5; i++) {
//				FXMLLoader fxmlLoader = new FXMLLoader();
//				fxmlLoader.setLocation(getClass().getResource("/HealthSchedule/resources/routines.fxml"));
//				AnchorPane anchorPane = fxmlLoader.load();
//				
//				if(column == 1) {
//					column = 0;
//					row++;
//				}
//				
//				grid.add(anchorPane, column++, row);
//				  //set grid width
//                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
//                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
//                grid.setMaxWidth(Region.USE_PREF_SIZE);
//
//                //set grid height
//                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
//                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
//                grid.setMaxHeight(Region.USE_PREF_SIZE);
//				
//				GridPane.setMargin(anchorPane, new Insets(10));
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
		
		
		
		
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("/HealthSchedule/resources/routines.fxml"));
			AnchorPane anchorPane = fxmlLoader.load();
			
			if(this.column == 1) {
				this.column = 0;
				this.row++;
			}
			
			grid.add(anchorPane, column++, row);
			  //set grid width
            grid.setMinWidth(Region.USE_COMPUTED_SIZE);
            grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
            grid.setMaxWidth(Region.USE_PREF_SIZE);

            //set grid height
            grid.setMinHeight(Region.USE_COMPUTED_SIZE);
            grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
            grid.setMaxHeight(Region.USE_PREF_SIZE);
			
			GridPane.setMargin(anchorPane, new Insets(10));
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

	
	

}
