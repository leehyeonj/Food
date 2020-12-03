package HealthSchedule.controller;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
//import javafx.scene.image.ImageView;

import javafx.stage.Stage;

public class Homet_fullbodyVideo_controller implements Initializable {
 
	   @FXML private Button goBack;	//뒤로가기버튼
	   @FXML private Button full_youtube1; //영상1로 가는 버튼
	   @FXML private Button full_youtube2; //영상2로 가는 버튼
	   @FXML private Button full_youtube3; //영상3로 가는 버튼
	   //@FXML private ImageView star; //별모양 이미지
	   
	   @Override
	   public void initialize(URL location, ResourceBundle resources) {
		   goBack.setOnAction(e->btngoBack(e));
		   full_youtube1.setOnAction(e->btnfull_youtube1(e));
		   full_youtube2.setOnAction(e->btnfull_youtube1(e));
		   full_youtube3.setOnAction(e->btnfull_youtube1(e));
		   
	   }
  
	   //전신 영상1 로 이동
	   public void btnfull_youtube1(ActionEvent event) {
	
		   try {
		         Desktop.getDesktop().browse(new URI("http://www.youtube.com/results?search_query=전신운동"));
		         }
		      catch (IOException e) {
		         e.printStackTrace(); }
		         catch (URISyntaxException e) { 
		            e.printStackTrace();
		            }
	   }
	   
	   //전신 영상2 로 이동
	   public void btnfull_youtube2(ActionEvent event) {
		   try {
		         Desktop.getDesktop().browse(new URI("http://www.youtube.com/results?search_query=다리운동"));
		         }
		      catch (IOException e) {
		         e.printStackTrace(); }
		         catch (URISyntaxException e) { 
		            e.printStackTrace();
		            }
	   }
	   
	   //전신 영상3 로 이동
	   public void btnfull_youtube3(ActionEvent event) {
		   try {
		         Desktop.getDesktop().browse(new URI("http://www.youtube.com/results?search_query=복부운동"));
		         }
		      catch (IOException e) {
		         e.printStackTrace(); }
		         catch (URISyntaxException e) { 
		            e.printStackTrace();
		            }
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