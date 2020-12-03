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
import javafx.scene.image.ImageView;

import javafx.stage.Stage;

public class Homet_mainpage_controller implements Initializable{

	@FXML private Button fullBody; //전신운동 버튼
	@FXML private Button upperBody; //상체운동 버튼
	@FXML private Button abs; //복부운동 버튼
	@FXML private Button lowerBody; //하체운동 버튼
	@FXML private Button home; //홈 버튼
	@FXML private Button food; //식단 버튼
	@FXML private Button chart; //내 몸 버튼
	@FXML private Button HT; //홈트 버튼
	
	@FXML private ImageView human; //인체사진
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		home.setOnAction(e->btnhome(e)); 
		food.setOnAction(e->btnfood(e));
		chart.setOnAction(e->btnchart(e));
		HT.setOnAction(e->btnHT(e));
		
		fullBody.setOnAction(e->btnfullBody(e)); 
		upperBody.setOnAction(e->btnupperBody(e));
		abs.setOnAction(e->btnabs(e));
		lowerBody.setOnAction(e->btnlowerBody(e));
	}
	
	//체중으로 이동
	 public void btnchart(ActionEvent event) {
		   try {
		     Parent healthChart = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/chart_mainpage.fxml"));
		     Scene scene = new Scene(healthChart);
		     Stage primaryStage= (Stage)chart.getScene().getWindow();
		     primaryStage.setScene(scene);
		  } catch (Exception e) {}
	   }
	
	//홈트로 이동
	 public void btnHT(ActionEvent e) {
		try {
		     Parent healthChart = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/homet_mainpage.fxml"));
		     Scene scene = new Scene(healthChart);
		     Stage primaryStage= (Stage)HT.getScene().getWindow();
		     primaryStage.setScene(scene);
		  } catch (Exception e2) {}
	}
	
	//식단으로 이동
	public void btnfood(ActionEvent e) {
		
		try {
			 Parent healthChart = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/food_mainpage.fxml"));
			 Scene scene = new Scene(healthChart);
			 Stage primaryStage= (Stage)food.getScene().getWindow();
			 primaryStage.setScene(scene);
			} catch (Exception e2) {}
		}
	
	
	//홈으로 이동
	public void btnhome(ActionEvent e) {
		 try {
		     Parent Home = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/main.fxml"));
		     Scene scene = new Scene(Home);
		     Stage primaryStage= (Stage)home.getScene().getWindow();
		     primaryStage.setScene(scene);
		  } catch (Exception e2) {}
	   }	
	

	//전신운동 페이지로 넘어감
	public void btnfullBody(ActionEvent e) {
		Button fullBody = new Button("전신");
		fullBody.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent event) {
				try {
			         Desktop.getDesktop().browse(new URI("http://www.youtube.com/results?search_query=전신운동"));
			         }
			      catch (IOException e) {
			         e.printStackTrace(); }
			         catch (URISyntaxException e) { 
			            e.printStackTrace();
			            }
				}
		});
	}
		
//		try {
//			Parent part = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/homet_fullbodyvideo.fxml"));
//			Scene scene = new Scene(part);
//			Stage primaryStage = (Stage)fullBody.getScene().getWindow();
//			primaryStage.setScene(scene);
//			
//		} catch (Exception e2) {}
	
	
	//상체운동 페이지로 넘어감
	public void btnupperBody(ActionEvent upperStream) {
		 try {
	         Desktop.getDesktop().browse(new URI("http://www.youtube.com/results?search_query=상체운동"));
	         }
	      catch (IOException e) {
	         e.printStackTrace(); }
	         catch (URISyntaxException e) { 
	            e.printStackTrace();
	            }
	}
	
	//복부운동 페이지로 넘어감
	public void btnabs(ActionEvent absStream) {
		 try {
	         Desktop.getDesktop().browse(new URI("http://www.youtube.com/results?search_query=복부운동"));
	         }
	      catch (IOException e) {
	         e.printStackTrace(); }
	         catch (URISyntaxException e) { 
	            e.printStackTrace();
	            }
	}		

	//하체운동 페이지로 넘어감
	public void btnlowerBody(ActionEvent lowerStream) {
		 try {
	         Desktop.getDesktop().browse(new URI("http://www.youtube.com/results?search_query=하체운동"));
	         }
	      catch (IOException e) {
	         e.printStackTrace(); }
	         catch (URISyntaxException e) { 
	            e.printStackTrace();
	            }
	}	
	
}
