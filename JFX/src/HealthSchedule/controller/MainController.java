package HealthSchedule.controller;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

//��ҿ� �ϴ� ��ó�� Initializable�� ��ӹ��� �ʴ´�
public class MainController extends MasterController{

	  
	   @FXML private Button food;	//�Ĵܰ�����ư
	   @FXML private Button chart;	//ü�߰�����ư
	   @FXML private Button HT;		//ȨƮ�����ư
	   @FXML private Button home;	//Ȩ��ư
	   @FXML private Button btnPrev;	//������
	   @FXML private Button btnNext;	//�̹���
	   @FXML private Label lblDate;
	   @FXML private Label lblDay;
		
	   @FXML private GridPane gridCalendar;	//�޷�ǥ
		
	   private YearMonth currentYM; //������ ����� �����ϴ� ����
	   
	   //GridPane�� ��� ���� ����ֱ� ���� ����
	   private List<DayController> dayList;
	   private Map<String, String> dayOfWeek = new HashMap<>();
	   
	   @FXML 
	   public void initialize() {
		   
		   //��ư�׼�
		   food.setOnAction(e->btnfood(e));
		   chart.setOnAction(e->btnchart(e));
		   HT.setOnAction(e->btnHT(e));
		   home.setOnAction(e->btnhome(e));
		   
		   //�޷�
		   dayList = new ArrayList<>();
		   //�޷�(GridPane)�� �ݺ����� ����� ��� ������ ��¥�� �Է�
		   for(int i = 0; i < 5; i++) { //�޷��� ��
			   for(int j = 0; j < 7; j++) { //�޷��� ��
				   FXMLLoader loader = new FXMLLoader();
				   loader.setLocation(getClass().getResource("/HealthSchedule/resources/calendarDayLayout.fxml"));
				   System.out.printf("j : %d��° �׸��� ����\n", j);
				   try {
					   AnchorPane ap = loader.load();
					   gridCalendar.add(ap, j, i);
					   DayController dc = loader.getController();
					   dc.setRoot(ap);
					   dayList.add(dc);
					   //System.out.println("1");
				   } catch (Exception e) {
					   e.printStackTrace();
					   System.out.printf("j : %d, i : %d ��° �׸��� �� ���� �߻�\n", j, i);
				   }
			   }	//����for
		   }	//for
		   
		   //�߾ӻ�� ���� ���� ǥ��
		   String[] engDay = {"SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY"};
		   String[] korDay = {"�Ͽ���", "������", "ȭ����", "������", "�����", "�ݿ���", "�����"};
		   
		   for(int i = 0; i < engDay.length; i++) {
			   dayOfWeek.put(engDay[i], korDay[i]); 	//put(String key, String value)
		   }
			
		   loadMonthData(YearMonth.now());
		   setToday(LocalDate.now());
	   }
	   
	   //�Ѵ� �� �޷��� �ε�
	   public void prevMonth() {
		   loadMonthData(currentYM.minusMonths(1));
		   //LocalDate.of(int year, int month, int dayOfMonth)
		   LocalDate firstDay = LocalDate.of(currentYM.getYear(), currentYM.getMonthValue(), 1);
		   setToday(firstDay);	//�޷��� �ѱ�� 1�Ϸ� ����
	   }

	   //�Ѵ� �� �޷��� �ε�
	   public void nextMonth() {
		   loadMonthData(currentYM.plusMonths(1));
		   LocalDate firstDay = LocalDate.of(currentYM.getYear(), currentYM.getMonthValue(), 1);
		   setToday(firstDay);	//�޷��� �ѱ�� 1�Ϸ� ����
	   }
	   
	   //�߾ӻ�� ���� ��¥
	   public void setToday(LocalDate date) {
		   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd");
		   lblDate.setText(date.format(dtf));
		   lblDay.setText(dayOfWeek.get(date.getDayOfWeek().toString()));
	   }
		
	   //�߾ӻ�� ���� ����
	   public void loadMonthData(YearMonth ym) {
		   LocalDate calendarDate = LocalDate.of(ym.getYear(), ym.getMonthValue(), 1); //�ش� ����� 1���� �����´�.
		   while(!calendarDate.getDayOfWeek().toString().equals("SUNDAY")) { //�Ͽ����� �ƴҶ����� �Ϸ羿 ������.
			   calendarDate = calendarDate.minusDays(1); //�Ϸ羿 ����
		   }
		   //������� ���� �ش��ְ��� ù°���� ���Եȴ�.
		   for(DayController day : dayList) {
			   day.setDayLabel(calendarDate);
			   calendarDate = calendarDate.plusDays(1); //�Ϸ羿 ����
			   day.setCountLabel(0); //ó���� ������ 0���� �����Ѵ�.
		   }
		   currentYM = ym;
		}
		
	   public void setClickData(LocalDate date) {
		   setToday(date); //��¥ �����ϰ�
		   for(DayController dc : dayList) {
			   dc.outFocus(); //��� DayController���� "active"�� �����Ѵ�.
		   }
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
	   
	   public void btnhome(ActionEvent event) {
		   try {
		     Parent Home = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/main.fxml"));
		     Scene scene = new Scene(Home);
		     Stage primaryStage= (Stage)home.getScene().getWindow();
		     primaryStage.setScene(scene);
		  } catch (Exception e) {}
	   }
}
