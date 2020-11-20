package HealthSchedule;

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

public class TodayFoodController implements Initializable{

   @FXML private Button ateFoodPick;
   @FXML private Button food;	//�Ĵܰ�����ư
   @FXML private Button chart;	//ü�߰�����ư
   @FXML private Button HT;		//ȨƮ�����ư
   @FXML private Button home;	//Ȩ��ư
	
   MaintestController main = new MaintestController();
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
			//�� ��ư�� ������ onAction�� btnFoodAdd��� ��������
			//getResource�� ���� "morning.fxml"�� �Űܰ�! ��������
			//morningFood��ư�� ������ �� �������� ������ ����
			Parent foodPicker = FXMLLoader.load(getClass().getResource("morning.fxml"));
			Scene scene = new Scene(foodPicker);
			Stage primaryStage= (Stage)ateFoodPick.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (Exception e2) {}
	}

	   //�Ĵܰ���
	   public void btnfood(ActionEvent event) {
		   try {
		     Parent foodList = FXMLLoader.load(getClass().getResource("todayFood.fxml"));
		     Scene scene = new Scene(foodList);
		     Stage primaryStage= (Stage)food.getScene().getWindow();
		     primaryStage.setScene(scene);
		  } catch (Exception e) {}
	   }
	   
	   public void btnchart(ActionEvent event) {
		   try {
		     Parent healthChart = FXMLLoader.load(getClass().getResource("chart.fxml"));
		     Scene scene = new Scene(healthChart);
		     Stage primaryStage= (Stage)chart.getScene().getWindow();
		     primaryStage.setScene(scene);
		  } catch (Exception e) {}
	   }
	   
	   public void btnHT(ActionEvent event) {
		   try {
		     Parent hometranning = FXMLLoader.load(getClass().getResource("homet.fxml"));
		     Scene scene = new Scene(hometranning);
		     Stage primaryStage= (Stage)HT.getScene().getWindow();
		     primaryStage.setScene(scene);
		  } catch (Exception e) {}
	   }
	   
	   public void btnhome(ActionEvent event) {
		   try {
		     Parent Home = FXMLLoader.load(getClass().getResource("maintest.fxml"));
		     Scene scene = new Scene(Home);
		     Stage primaryStage= (Stage)home.getScene().getWindow();
		     primaryStage.setScene(scene);
		  } catch (Exception e) {}
	   }
}
