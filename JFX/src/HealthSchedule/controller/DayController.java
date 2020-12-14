package HealthSchedule.controller;


import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.ResourceBundle;

import HealthSchedule.Main.AppMain;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class DayController extends MainController   implements Initializable{
	@FXML private Label lblDay;		//��(day)
//	@FXML private Label lblCount;	//����ī��Ʈ
	@FXML private AnchorPane calendarDay ;
	
	static String dayOfMonth; //��¥ ��ĥ����
	 static final String[] DAY_OF_WEEK = {"", "SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY","THURSDAY","FRIDAY","SATURDAY"};
	//��¥ 
	static String everyday = year + month +  dayOfMonth;
	static String dayofWeek; //����
	private LocalDate date;
	private boolean isFocused = false;
	
	private Pane root; //���� ������ ��Ʈ ����

	public Pane getRoot() {
		return root;
	}

	public void setRoot(Pane root) {
		this.root = root;
	}
	
	MemoDao memodao = new MemoDao();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
//		System.out.println("dayController initialize����");
		System.out.println("dayController initialize����");
//		memodao.ifexistMemo(everyday);
		Tooltip tooltip = new Tooltip("�޸� �߰��ϱ�");
		lblDay.setTooltip(tooltip);
	}
	
	
	public void setDayLabel(LocalDate date) {
		this.date = date;
		lblDay.setText(String.valueOf(date.getDayOfMonth()));
//		dayOfMonth = lblDay.getText();
	}
	
	public void setCountLabel(Integer count) {
//		lblCount.setText(count.toString());
	}
	
	public void setFocus() {
		AppMain.app.setFocus(date);
		isFocused = true;
		getRoot().getStyleClass().add("active");
	}
	
	public void outFocus() {
		isFocused = false;
		getRoot().getStyleClass().remove("active");
	}
	
	//������ �̵�
	 @FXML
	   private void pageMove(MouseEvent event) {
		 //��¥�� ���� �������־����.
//			 System.out.println("dayController pagemove����");
			 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd");
			 dayOfMonth = date.format(dtf);
			
			 Calendar date1 = Calendar.getInstance();
			 date1.set(Integer.parseInt(year), Integer.parseInt(month)-1,Integer.parseInt(dayOfMonth));
			dayofWeek = DAY_OF_WEEK[date1.get(Calendar.DAY_OF_WEEK)];
//			System.out.println(dayofWeek);
//			 
//			System.out.println("daycontroller:"+year + month + dayOfMonth);
			everyday = year + month + dayOfMonth;
			try {
				//�޷��� �� ��¥�� Ŭ���ϸ� main_everydayRecord�������� �Ѿ
				Parent checkOk = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/main_everydayRecord.fxml"));
				Scene scene = new Scene(checkOk);
				Stage primaryStage= (Stage)calendarDay.getScene().getWindow();
				primaryStage.setScene(scene);
				System.out.println("������ Ŭ��"+ everyday);
				
			} catch (Exception e2) {}
	   }
	
	
}