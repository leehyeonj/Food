package HealthSchedule.controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Chart_mainpage_controller implements Initializable {

	   @FXML private Button healthLog;	//��Ϲ�ư
	   @FXML private Button food;	//�Ĵܰ�����ư
	   @FXML private Button chart;	//ü�߰�����ư
	   @FXML private Button HT;		//ȨƮ�����ư
	   @FXML private Button home;	//Ȩ��ư
	   @FXML private TextField challenge2;	//��ǥü�� �ؽ�Ʈ�ʵ�
	   @FXML LineChart<String, Integer> linechart;
	   XYChart.Series<String, Integer> series = null;
	   
	   @Override
	   public void initialize(URL location, ResourceBundle resources) {
		   //��ư�׼��߰�
		   healthLog.setOnAction(e->btnhealthlog(e));
		   food.setOnAction(e->btnfood(e));
		   chart.setOnAction(e->btnchart(e));
		   HT.setOnAction(e->btnHT(e));
		   home.setOnAction(e->btnhome(e));
		   drawChart();
		   
	   }
	   
	   // ��Ʈ �׸���
	   public void drawChart() {
		   SimpleDateFormat sdf = new SimpleDateFormat("MM�� dd��");
		   Calendar time = Calendar.getInstance();
		   String ft = sdf.format(time.getTime());
		   
		   series = new XYChart.Series<String, Integer>();
		   series.getData().add(new XYChart.Data<String, Integer>("������", 75)); // Ư¡���� ������ �� ������ getData
		   series.getData().add(new XYChart.Data<String, Integer>("ȭ����", 73));
		   series.getData().add(new XYChart.Data<String, Integer>("������", 74));
		   series.getData().add(new XYChart.Data<String, Integer>("�����", 75));
		   series.getData().add(new XYChart.Data<String, Integer>("�ݿ���", 73));
		   series.getData().add(new XYChart.Data<String, Integer>("�����", 70));
		   series.getData().add(new XYChart.Data<String, Integer>("�Ͽ���", 72));
	        
		   series.setName(ft);	//StringŸ�Ը� �޴´�
		   linechart.getData().add(series);  // �ٸ� ������ �߰��ϰ� ������ �ٸ� series ����� �ȴ�.
  
	   }
	   
	   //�ڷΰ���
	   public void btnhealthlog(ActionEvent event) {
	      try {
	    	 //������� �ּҴ� �������� ��ȯ�� fxml��
	    	 //fxml���� ��Ʈ�ѷ��� �ִ� �� ��Ʈ�ѷ����� ���� fxml�� ������ ����
	         Parent Log = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/chart_myWeightList.fxml"));
	         Scene scene = new Scene(Log);
	         Stage primaryStage= (Stage)healthLog.getScene().getWindow();
	         primaryStage.setScene(scene);
	      } catch (Exception e) {}
	   }
	   
	   //�Ĵܰ���
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
//	   
	   public void btnhome(ActionEvent event) {
		   try {
		     Parent Home = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/main.fxml"));
		     Scene scene = new Scene(Home);
		     Stage primaryStage= (Stage)home.getScene().getWindow();
		     primaryStage.setScene(scene);
		  } catch (Exception e) {}
	   }
	   
}