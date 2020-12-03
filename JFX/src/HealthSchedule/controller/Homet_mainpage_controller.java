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

	@FXML private Button fullBody; //���ſ ��ư
	@FXML private Button upperBody; //��ü� ��ư
	@FXML private Button abs; //���ο ��ư
	@FXML private Button lowerBody; //��ü� ��ư
	@FXML private Button home; //Ȩ ��ư
	@FXML private Button food; //�Ĵ� ��ư
	@FXML private Button chart; //�� �� ��ư
	@FXML private Button HT; //ȨƮ ��ư
	
	@FXML private ImageView human; //��ü����
	
	
	
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
	
	//ü������ �̵�
	 public void btnchart(ActionEvent event) {
		   try {
		     Parent healthChart = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/chart_mainpage.fxml"));
		     Scene scene = new Scene(healthChart);
		     Stage primaryStage= (Stage)chart.getScene().getWindow();
		     primaryStage.setScene(scene);
		  } catch (Exception e) {}
	   }
	
	//ȨƮ�� �̵�
	 public void btnHT(ActionEvent e) {
		try {
		     Parent healthChart = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/homet_mainpage.fxml"));
		     Scene scene = new Scene(healthChart);
		     Stage primaryStage= (Stage)HT.getScene().getWindow();
		     primaryStage.setScene(scene);
		  } catch (Exception e2) {}
	}
	
	//�Ĵ����� �̵�
	public void btnfood(ActionEvent e) {
		
		try {
			 Parent healthChart = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/food_mainpage.fxml"));
			 Scene scene = new Scene(healthChart);
			 Stage primaryStage= (Stage)food.getScene().getWindow();
			 primaryStage.setScene(scene);
			} catch (Exception e2) {}
		}
	
	
	//Ȩ���� �̵�
	public void btnhome(ActionEvent e) {
		 try {
		     Parent Home = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/main.fxml"));
		     Scene scene = new Scene(Home);
		     Stage primaryStage= (Stage)home.getScene().getWindow();
		     primaryStage.setScene(scene);
		  } catch (Exception e2) {}
	   }	
	

	//���ſ �������� �Ѿ
	public void btnfullBody(ActionEvent e) {
		Button fullBody = new Button("����");
		fullBody.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent event) {
				try {
			         Desktop.getDesktop().browse(new URI("http://www.youtube.com/results?search_query=���ſ"));
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
	
	
	//��ü� �������� �Ѿ
	public void btnupperBody(ActionEvent upperStream) {
		 try {
	         Desktop.getDesktop().browse(new URI("http://www.youtube.com/results?search_query=��ü�"));
	         }
	      catch (IOException e) {
	         e.printStackTrace(); }
	         catch (URISyntaxException e) { 
	            e.printStackTrace();
	            }
	}
	
	//���ο �������� �Ѿ
	public void btnabs(ActionEvent absStream) {
		 try {
	         Desktop.getDesktop().browse(new URI("http://www.youtube.com/results?search_query=���ο"));
	         }
	      catch (IOException e) {
	         e.printStackTrace(); }
	         catch (URISyntaxException e) { 
	            e.printStackTrace();
	            }
	}		

	//��ü� �������� �Ѿ
	public void btnlowerBody(ActionEvent lowerStream) {
		 try {
	         Desktop.getDesktop().browse(new URI("http://www.youtube.com/results?search_query=��ü�"));
	         }
	      catch (IOException e) {
	         e.printStackTrace(); }
	         catch (URISyntaxException e) { 
	            e.printStackTrace();
	            }
	}	
	
}
