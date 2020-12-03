package HealthSchedule.controller;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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
//import javafx.scene.image.ImageView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Homet_fullbodyVideo_controller implements Initializable {
 
	   @FXML private Button goBack;	//�ڷΰ����ư
	   @FXML private Button full_youtube1; //����1�� ���� ��ư
	   @FXML private Button full_youtube2; //����2�� ���� ��ư
	   @FXML private Button full_youtube3; //����3�� ���� ��ư
	   //@FXML private ImageView star; //����� �̹���
	   
	   @Override
	   public void initialize(URL location, ResourceBundle resources) {
		   goBack.setOnAction(e->btngoBack(e));
		   full_youtube1.setOnAction(e->btnfull_youtube1(e));
		   full_youtube2.setOnAction(e->btnfull_youtube1(e));
		   full_youtube3.setOnAction(e->btnfull_youtube1(e));
		   
	   }
  
	   //���� ����1 �� �̵�
	   public void btnfull_youtube1(ActionEvent event) {
	
		   try {
		         Desktop.getDesktop().browse(new URI("http://www.youtube.com/"));
		         }
		      catch (IOException e) {
		         e.printStackTrace(); }
		         catch (URISyntaxException e) { 
		            e.printStackTrace();
		            }
	   }
	   
	   //���� ����2 �� �̵�
	   public void btnfull_youtube2(ActionEvent event) {
		   WebView mywebView = new WebView();
		   WebEngine engine = mywebView.getEngine();
			
		   Button full_youtube2 = new Button("���ſ2");
		   full_youtube2.setOnAction(new EventHandler<ActionEvent>() {
				
			   @Override
			   public void handle(ActionEvent event) {
				   engine.load("https://www.youtube.com/watch?v=VVn5IUM8sms");
					
				}
		   }); 
	   }
	   
	   //���� ����3 �� �̵�
	   public void btnfull_youtube3(ActionEvent event) {
		   WebView mywebView = new WebView();
		   WebEngine engine = mywebView.getEngine();
			
		   Button full_youtube3 = new Button("���ſ3");
		   full_youtube3.setOnAction(new EventHandler<ActionEvent>() {
				
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