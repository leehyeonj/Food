package HealthSchedule.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Homet_upperbodyvideo_controller implements Initializable {
 
	   @FXML private Button goBack;	//뒤로가기버튼
	   @FXML private Button upper_youtube1; //영상1로 가는 버튼
	   @FXML private Button upper_youtube2; //영상2로 가는 버튼
	   @FXML private Button upper_youtube3; //영상3로 가는 버튼
	   
	   @Override
	   public void initialize(URL location, ResourceBundle resources) {
		   goBack.setOnAction(e->btngoBack(e));
		   upper_youtube1.setOnAction(e->btnupper_youtube1(e));
		   upper_youtube2.setOnAction(e->btnupper_youtube2(e));
		   upper_youtube3.setOnAction(e->btnupper_youtube3(e));
		   
	   }
  
	   //하체 영상1 로 이동
	   public void btnupper_youtube1(ActionEvent event) {
		   WebView mywebView = new WebView();
		   WebEngine engine = mywebView.getEngine();
			
		   Button upper_youtube1 = new Button("하체운동1");
		   upper_youtube1.setOnAction(new EventHandler<ActionEvent>() {
				
			   @Override
			   public void handle(ActionEvent event) {
				   engine.load("https://www.youtube.com/watch?v=VVn5IUM8sms");
					
				}
		   }); 
	   }
	   
	   //하체 영상2 로 이동
	   public void btnupper_youtube2(ActionEvent event) {
		   WebView mywebView = new WebView();
		   WebEngine engine = mywebView.getEngine();
			
		   Button upper_youtube2 = new Button("하체운동2");
		   upper_youtube2.setOnAction(new EventHandler<ActionEvent>() {
				
			   @Override
			   public void handle(ActionEvent event) {
				   engine.load("https://www.youtube.com/watch?v=VVn5IUM8sms");
					
				}
		   }); 
	   }
	   
	   //하체 영상3 로 이동
	   public void btnfull_youtube3(ActionEvent event) {
		   WebView mywebView = new WebView();
		   WebEngine engine = mywebView.getEngine();
			
		   Button upper_youtube3 = new Button("하체운동3");
		   upper_youtube3.setOnAction(new EventHandler<ActionEvent>() {
				
			   
			   @Override
			   public void handle(ActionEvent event) {
				   engine.load("https://www.youtube.com/watch?v=VVn5IUM8sms");
					
				}
		   }); 
	   }
	   
	   
	   //뒤로가기 버튼
	   public void btngoBack(ActionEvent event) {   
		   try {
		      Parent home = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/homet_mainpage.fxml"));
		      Scene scene = new Scene(home);
		      Stage primaryStage= (Stage)goBack.getScene().getWindow();
		      primaryStage.setScene(scene);
		   } catch (Exception e) {}
	   }
	   
}