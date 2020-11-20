package HealthSchedule.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Food_mainpage_controller implements Initializable{

   @FXML private Button ateFoodPick;
   @FXML private Button food;	//식단관리버튼
   @FXML private Button chart;	//체중관리버튼
   @FXML private Button HT;		//홈트영상버튼
   @FXML private Button home;	//홈버튼
	
   MainController main = new MainController();
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	   ateFoodPick.setOnAction(e->btnFoodAdd(e));
		
	   food.setOnAction(e->btnfood(e));
	   chart.setOnAction(e->btnchart(e));
	   HT.setOnAction(e->btnHT(e));
	   home.setOnAction(e->btnhome(e));

	}
	
	public void btnFoodAdd(ActionEvent event) {
		try {
			//이 버튼을 누르는 onAction을 btnFoodAdd라고 설정했음
			//getResource에 들어가는 "morning.fxml"이 옮겨갈! 페이지임
			//morningFood버튼을 누르면 그 페이지가 나오는 것임
			Parent foodPicker = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/food_breakfast.fxml"));
			Scene scene = new Scene(foodPicker);
			Stage primaryStage= (Stage)ateFoodPick.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (Exception e2) {}
	}

	   //식단관리
	   public void btnfood(ActionEvent event) {
		   try {
		     Parent foodList = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/food_mainpage.fxml"));
		     Scene scene = new Scene(foodList);
		     Stage primaryStage= (Stage)food.getScene().getWindow();
		     primaryStage.setScene(scene);
		  } catch (Exception e) {}
	   }
	   
	   public void btnchart(ActionEvent event) {
		   try {
		     Parent healthChart = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/chart_mainpage.fxml"));
		     Scene scene = new Scene(healthChart);
		     Stage primaryStage= (Stage)chart.getScene().getWindow();
		     primaryStage.setScene(scene);
		  } catch (Exception e) {}
	   }
	   
	   public void btnHT(ActionEvent event) {
		   try {
		     Parent hometranning = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/homet_mainpage.fxml"));
		     Scene scene = new Scene(hometranning);
		     Stage primaryStage= (Stage)HT.getScene().getWindow();
		     primaryStage.setScene(scene);
		  } catch (Exception e) {}
	   }
	   
	   public void btnhome(ActionEvent event) {
		   try {
		     Parent Home = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/main.fxml"));
		     Scene scene = new Scene(Home);
		     Stage primaryStage= (Stage)home.getScene().getWindow();
		     primaryStage.setScene(scene);
		  } catch (Exception e) {}
	   }
}
