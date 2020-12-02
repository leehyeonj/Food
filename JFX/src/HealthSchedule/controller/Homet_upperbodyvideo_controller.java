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
 
	   @FXML private Button goBack;	//�ڷΰ����ư
	   @FXML private Button upper_youtube1; //����1�� ���� ��ư
	   @FXML private Button upper_youtube2; //����2�� ���� ��ư
	   @FXML private Button upper_youtube3; //����3�� ���� ��ư
	   
	   @Override
	   public void initialize(URL location, ResourceBundle resources) {
		   goBack.setOnAction(e->btngoBack(e));
		   upper_youtube1.setOnAction(e->btnupper_youtube1(e));
		   upper_youtube2.setOnAction(e->btnupper_youtube2(e));
		   upper_youtube3.setOnAction(e->btnupper_youtube3(e));
		   
	   }
  
	   //��ü ����1 �� �̵�
	   public void btnupper_youtube1(ActionEvent event) {
		   WebView mywebView = new WebView();
		   WebEngine engine = mywebView.getEngine();
			
		   Button upper_youtube1 = new Button("��ü�1");
		   upper_youtube1.setOnAction(new EventHandler<ActionEvent>() {
				
			   @Override
			   public void handle(ActionEvent event) {
				   engine.load("https://www.youtube.com/watch?v=VVn5IUM8sms");
					
				}
		   }); 
	   }
	   
	   //��ü ����2 �� �̵�
	   public void btnupper_youtube2(ActionEvent event) {
		   WebView mywebView = new WebView();
		   WebEngine engine = mywebView.getEngine();
			
		   Button upper_youtube2 = new Button("��ü�2");
		   upper_youtube2.setOnAction(new EventHandler<ActionEvent>() {
				
			   @Override
			   public void handle(ActionEvent event) {
				   engine.load("https://www.youtube.com/watch?v=VVn5IUM8sms");
					
				}
		   }); 
	   }
	   
	   //��ü ����3 �� �̵�
	   public void btnfull_youtube3(ActionEvent event) {
		   WebView mywebView = new WebView();
		   WebEngine engine = mywebView.getEngine();
			
		   Button upper_youtube3 = new Button("��ü�3");
		   upper_youtube3.setOnAction(new EventHandler<ActionEvent>() {
				
			   
			   @Override
			   public void handle(ActionEvent event) {
				   engine.load("https://www.youtube.com/watch?v=VVn5IUM8sms");
					
				}
		   }); 
	   }
	   
	   
	   //�ڷΰ��� ��ư
	   public void btngoBack(ActionEvent event) {   
		   try {
		      Parent home = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/homet_mainpage.fxml"));
		      Scene scene = new Scene(home);
		      Stage primaryStage= (Stage)goBack.getScene().getWindow();
		      primaryStage.setScene(scene);
		   } catch (Exception e) {}
	   }
	   
}