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

import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Homet_lowerbodyvideo_controller implements Initializable {
 
	   @FXML private Button goBack;	//�ڷΰ����ư
	   @FXML private Button lower_youtube1; //����1�� ���� ��ư
	   @FXML private Button lower_youtube2; //����2�� ���� ��ư
	   @FXML private Button lower_youtube3; //����3�� ���� ��ư
	   
	   @Override
	   public void initialize(URL location, ResourceBundle resources) {
		   goBack.setOnAction(e->btngoBack(e));
		   lower_youtube1.setOnAction(e->btnlower_youtube1(e));
		   lower_youtube2.setOnAction(e->btnlower_youtube1(e));
		   lower_youtube3.setOnAction(e->btnlower_youtube1(e));
		   
	   }
  
	   //��ü ����1 �� �̵�
	   public void btnlower_youtube1(ActionEvent event) {
		   WebView mywebView = new WebView();
		   WebEngine engine = mywebView.getEngine();
			
		   Button lower_youtube1 = new Button("��ü�1");
		   lower_youtube1.setOnAction(new EventHandler<ActionEvent>() {
				
			   @Override
			   public void handle(ActionEvent event) {
				   engine.load("https://www.youtube.com/watch?v=VVn5IUM8sms");
					
				}
		   }); 
	   }
	   
	   //��ü ����2 �� �̵�
	   public void btnlower_youtube2(ActionEvent event) {
		   WebView mywebView = new WebView();
		   WebEngine engine = mywebView.getEngine();
			
		   Button lower_youtube2 = new Button("��ü�2");
		   lower_youtube2.setOnAction(new EventHandler<ActionEvent>() {
				
			   @Override
			   public void handle(ActionEvent event) {
				   engine.load("https://www.youtube.com/watch?v=VVn5IUM8sms");
					
				}
		   }); 
	   }
	   
	   //��ü ����3 �� �̵�
	   public void btnlower_youtube3(ActionEvent event) {
		   WebView mywebView = new WebView();
		   WebEngine engine = mywebView.getEngine();
			
		   Button lower_youtube3 = new Button("��ü�3");
		   lower_youtube3.setOnAction(new EventHandler<ActionEvent>() {
				
			   
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