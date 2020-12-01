package HealthSchedule.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//��ҿ� �ϴ� ��ó�� Initializable�� ��ӹ��� �ʴ´�
public class MainController extends MasterController implements Initializable{
	@FXML
	private AnchorPane pane;
	  
	   @FXML private Button food;	//�Ĵܰ�����ư
	   @FXML private Button chart;	//ü�߰�����ư
	   @FXML private Button HT;		//ȨƮ�����ư
	   @FXML private Button home;	//Ȩ��ư
	   @FXML private Button btnPrev;	//������
	   @FXML private Button btnNext;	//�̹���
	   @FXML private Label lblDate;
	   @FXML private Label lblDay;
	   @FXML private Label lblMonth;
	   @FXML private Label monthText;
		
	   @FXML private JFXHamburger hamburger;
	   @FXML private JFXDrawer drawer;
	   
	   @FXML private GridPane gridCalendar;	//�޷�ǥ
	   
	   
	   
		
	   private YearMonth currentYM; //������ ����� �����ϴ� ����
	   LocalDate date;
	   
	   //GridPane�� ��� ���� ����ֱ� ���� ����
	   private List<DayController> dayList;
	   private Map<String, String> dayOfWeek = new HashMap<>();
	   
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			//stage ����
			  stageDragableMoveWindow();
			   
		   //�ܹ��� ��ư
			try {
				VBox vbox = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/mainDrawer.fxml"));
				drawer.setSidePane(vbox);
				  
				   HamburgerSlideCloseTransition burger = new HamburgerSlideCloseTransition(hamburger);
//					   HamburgerBackArrowBasicTransition burger = new HamburgerBackArrowBasicTransition(hamburger);
				   System.out.println("1");
				   burger.setRate(-1);
				   hamburger.addEventHandler(MouseEvent.MOUSE_CLICKED, (e)->{
					   burger.setRate(burger.getRate()*-1);
					   burger.play();
					   
					   if(drawer.isOpened()) {
						   drawer.close();
					   }
					   else {
						   drawer.open();
					   }
				   });
			} catch (IOException e1) {}
			
			   
			 
			   //��ư�׼�
//			   food.setOnAction(e->btnfood(e));
//			   chart.setOnAction(e->btnchart(e));
//			   HT.setOnAction(e->btnHT(e));
//			   home.setOnAction(e->btnhome(e));
			   
			   
			  
			   //�޷�
			   dayList = new ArrayList<>();
			   //�޷�(GridPane)�� �ݺ����� ����� ��� ������ ��¥�� �Է�
			   for(int i = 0; i < 5; i++) { //�޷��� ��
				   for(int j = 0; j < 7; j++) { //�޷��� ��
//					  if(lblDay.getText().equals(date.getDayOfMonth().toString())) {
//						  
//					  }
					   FXMLLoader loader = new FXMLLoader();
					   loader.setLocation(getClass().getResource("/HealthSchedule/resources/calendarDayLayout.fxml"));
//					   System.out.printf("j : %d��° �׸��� ����\n", j);
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
			   String[] engDay = {"JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY","AUGUST","SEPTEMBER","OCTOBER","NOVEMBER","DECEMBER"};
//			   String[] korDay = {"1��", "2��", "3��", "4��", "5��", "6��", "7��", "8��", "9��", "10��", "11��", "12��"};
			   
			   for(int i = 0; i < engDay.length; i++) {
				   dayOfWeek.put(engDay[i], engDay[i]); 	//put(String key, String value)
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
		   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy");
		   DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("MM");
		   lblDate.setText(date.format(dtf));
		   lblMonth.setText(dayOfWeek.get(date.getMonth().toString()));
		   monthText.setText(date.format(dtf2));
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
//	   public void btnfood(ActionEvent event) {
//		   try {
//		     Parent foodList = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/food_mainpage.fxml"));
//		     Scene scene = new Scene(foodList);
//		     Stage primaryStage= (Stage)food.getScene().getWindow();
//		     primaryStage.setScene(scene);
//		  } catch (Exception e) {}
//	   }
//	   
//	   public void btnchart(ActionEvent event) {
//		   try {
//		     Parent healthChart = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/chart_mainpage.fxml"));
//		     Scene scene = new Scene(healthChart);
//		     Stage primaryStage= (Stage)chart.getScene().getWindow();
//		     primaryStage.setScene(scene);
//		  } catch (Exception e) {}
//	   }
//	   
//	   public void btnHT(ActionEvent event) {
//		   try {
//		     Parent hometranning = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/homet_mainpage.fxml"));
//		     Scene scene = new Scene(hometranning);
//		     Stage primaryStage= (Stage)HT.getScene().getWindow();
//		     primaryStage.setScene(scene);
//		  } catch (Exception e) {}
//	   }
//	   
//	   public void btnhome(ActionEvent event) {
//		   try {
//		     Parent Home = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/main.fxml"));
//		     Scene scene = new Scene(Home);
//		     Stage primaryStage= (Stage)home.getScene().getWindow();
//		     primaryStage.setScene(scene);
//		  } catch (Exception e) {}
//	   }
	   
	   //////////////////////////
	   private double xOffset = 0;
	   private double yOffset = 0;
	   private Stage stage = null;

	   //ȭ�� �����϶� �������� ���ϰ� �ϱ�
	   private void stageDragableMoveWindow() {
		   pane.setOnMousePressed((event) -> {
		   xOffset = event.getSceneX();
		   yOffset = event.getSceneY();
		   });
		   pane.setOnMouseDragged((event) -> {
		   // Launcher.stage.setX(event.getScreenX() - xOffset);
		   // Launcher.stage.setY(event.getScreenY() - yOffset);
		   // Launcher.stage.setOpacity(0.8f); // â ����ȭ
		   stage = (Stage) pane.getScene().getWindow();
		   stage.setX(event.getScreenX() - xOffset);
		   stage.setY(event.getScreenY() - yOffset);
		   stage.setOpacity(0.8f); // â ����ȭ
		   });
		   pane.setOnDragDone((event) -> {
		   // Launcher.stage.setOpacity(1.0f);
		   stage = (Stage) pane.getScene().getWindow();
		   stage.setOpacity(1.0f);
		   });
		   pane.setOnMouseReleased((event) -> {
		   // Launcher.stage.setOpacity(1.0f);
		   stage = (Stage) pane.getScene().getWindow();
		   stage.setOpacity(1.0f);
		   });
	   }

	   //ȭ�� �����
	   @FXML
	   private void actionMinWindow(MouseEvent event) {
	   // Launcher.stage.setIconified(true);
	   stage = (Stage) pane.getScene().getWindow();
	   stage.setIconified(true);
	   }
	   //��ü ȭ������
//	   @FXML
//	   private void actionMaxWindow(MouseEvent event) {
//	   // Launcher.stage.setFullScreen(true);
//	   // if (Launcher.stage.isMaximized()) {
//	   // Launcher.stage.setMaximized(false);
//	   // } else {
//	   // Launcher.stage.setMaximized(true);
//	   // }
//	   stage = (Stage) pane.getScene().getWindow();
//	   if (stage.isMaximized()) {
//	   stage.setMaximized(false);
//	   } else {
//	   stage.setMaximized(true);
//	   }
//	   }
	   
	   //ȭ�� ����
	   @FXML
	   private void actionCloseWindow(MouseEvent event) {
	   System.exit(0);
	   }



	
	  
		
	 
}
