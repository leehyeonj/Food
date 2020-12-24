package HealthSchedule.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import HealthSchedule.Dao.PieChartDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
 
public class PiechartMonthController extends DayController implements Initializable{
 
	@FXML AnchorPane pane;
    @FXML PieChart pieChart;
    @FXML Label full, upper, abs, lower, stretching;	//체중별라벨(본 시간)
    @FXML JFXButton monthchart;	//한달차트
    
    PieChartDao piechartdao = new PieChartDao();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		setTodayDate(year, month, dayOfMonth);
		stageDragableMoveWindow();
		ShowChart();
	}
	
	//piechart타이틀설정
	public void setTodayDate(String year, String month, String dayOfMonth) {
		pieChart.setTitle(month+"월 운동량");
	}  
	
	private double xOffset = 0;
	private double yOffset = 0;
	private Stage stage = null;
	
	//화면 움직일때 투명으로 변하게 하기
	private void stageDragableMoveWindow() {
			pane.setOnMousePressed((event) -> {
			xOffset = event.getSceneX();
			yOffset = event.getSceneY();
			});
			pane.setOnMouseDragged((event) -> {
			// Launcher.stage.setX(event.getScreenX() - xOffset);
			// Launcher.stage.setY(event.getScreenY() - yOffset);
			// Launcher.stage.setOpacity(0.8f); // 창 투명화
			stage = (Stage) pane.getScene().getWindow();
			stage.setX(event.getScreenX() - xOffset);
			stage.setY(event.getScreenY() - yOffset);
			stage.setOpacity(0.8f); // 창 투명화
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
	
	//화면 숨기기
	@FXML
	private void actionMinWindow(MouseEvent event) {
		// Launcher.stage.setIconified(true);
		stage = (Stage) pane.getScene().getWindow();
		stage.setIconified(true);
	}
	
	
	//화면 끄기
	@FXML
	private void actionCloseWindow(MouseEvent event) {
		stage.close();
	}
	
	//차트표시
    public void ShowChart(){  	
      	PiechartMonthController mc = new PiechartMonthController();
    	//piechart데이터표시(월단위)
        ObservableList<Data> list = FXCollections.observableArrayList(
                new PieChart.Data("전신운동", piechartdao.selectFullbodyAll(1)),    
                new PieChart.Data("상체운동", piechartdao.selectUpperbodyAll(1)),
                new PieChart.Data("복근운동", piechartdao.selectAbsAll(1)),
                new PieChart.Data("하체운동", piechartdao.selectLowerAll(1)),
                new PieChart.Data("스트레칭", piechartdao.selectStretchingAll(1))
                );
        pieChart.setData(list);
        //라벨에 부위별 시간 띄우기
        for (final PieChart.Data data : pieChart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    full.setText("전신운동\n" + mc.FullBodyTime());
                    upper.setText("상체운동\n" + mc.UpperBodyTime());
                    abs.setText("복근운동\n" + mc.AbsTime());
                    lower.setText("하체운동\n" + mc.LowerBodyTime());
                    stretching.setText("스트레칭\n" + mc.StretchingTime());
                }
            });	//addEventHandler
        }	//for
    }	//ShowChartbtn
    
	//일일차트로 이동
	public void DayChartbtn(ActionEvent e) {
		   try {
			   Parent members = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/piechartDay.fxml"));
			   Scene scene = new Scene(members);
			   Stage primaryStage= (Stage)monthchart.getScene().getWindow();
			   primaryStage.setScene(scene);
			 } catch (Exception e2) {}	
	}
    

	//전신운동시간설정	00시간00분00초
    public String FullBodyTime() {
		int second = (int)Math.round(piechartdao.selectFullbodyAll(1));
		int minute = second / 60;
		int hour = minute / 60;
		second = second % 60;
		minute = minute % 60;
		String time = null;
		if(hour == 0) {
			 time = String.format("%02d시간%02d분%02d초",hour, minute, second);
		}else if(1 <= hour && hour < 10) {
			time = String.format("%1d시간%02d분%02d초", hour, minute, second);
		}else if(hour < 10 && hour < 100) {
			time = String.format("%2d시간%02d분%02초", hour, minute, second);
		}else if(hour < 100 && hour < 1000) {
			time = String.format("%3d시간%02d분%02d초", hour, minute, second);
		}
//		System.out.println("전신운동시간" + time);
    	return time;
    }
	//상체운동시간설정
    public String UpperBodyTime() {
		int second = (int)Math.round(piechartdao.selectUpperbodyAll(1));
		int minute = second / 60;
		int hour = minute / 60;
		second = second % 60;
		minute = minute % 60;
		String time = null;
		if(hour == 0) {
			 time = String.format("%02d시간%02d분%02d초",hour, minute, second);
		}else if(1 <= hour && hour < 10) {
			time = String.format("%1d시간%02d분%02d초", hour, minute, second);
		}else if(hour < 10 && hour < 100) {
			time = String.format("%2d시간%02d분%02초", hour, minute, second);
		}else if(hour < 100 && hour < 1000) {
			time = String.format("%3d시간%02d분%02d초", hour, minute, second);
		}
//		System.out.println("상체운동시간" + time);
    	return time;
    }
	//복근운동시간설정
    public String AbsTime() {
		int second = (int)Math.round(piechartdao.selectAbsAll(1));
		int minute = second / 60;
		int hour = minute / 60;
		second = second % 60;
		minute = minute % 60;
		String time = null;
		if(hour == 0) {
			 time = String.format("%02d시간%02d분%02d초",hour, minute, second);
		}else if(1 <= hour && hour < 10) {
			time = String.format("%1d시간%02d분%02d초", hour, minute, second);
		}else if(hour < 10 && hour < 100) {
			time = String.format("%2d시간%02d분%02초", hour, minute, second);
		}else if(hour < 100 && hour < 1000) {
			time = String.format("%3d시간%02d분%02d초", hour, minute, second);
		}
//		System.out.println("복근운동시간" + time);
    	return time;
    }
	//하체운동시간설정
    public String LowerBodyTime() {
		int second = (int)Math.round(piechartdao.selectLowerAll(1));
		int minute = second / 60;
		int hour = minute / 60;
		second = second % 60;
		minute = minute % 60;

		String time = null;
//		time = String.format("%02d시간%02d분%02d초",hour, minute, second);
		if(hour == 0) {
			 time = String.format("%02d시간%02d분%02d초",hour, minute, second);
		}else if(1 <= hour && hour < 10) {
			time = String.format("%1d시간%02d분%02d초", hour, minute, second);
		}else if(hour < 10 && hour < 100) {
			time = String.format("%2d시간%02d분%02초", hour, minute, second);
		}else if(hour < 100 && hour < 1000) {
			time = String.format("%3d시간%02d분%02d초", hour, minute, second);
		}
//		System.out.println("하체운동시간" + time);
    	return time;
    }  
	//스트레칭시간설정
    public String StretchingTime() {
		int second = (int)Math.round(piechartdao.selectStretchingAll(1));
		int minute = second / 60;
		int hour = minute / 60;
		second = second % 60;
		minute = minute % 60;
		String time = null;
		if(hour == 0) {
			 time = String.format("%02d시간%02d분%02d초",hour, minute, second);
		}else if(1 <= hour && hour < 10) {
			time = String.format("%1d시간%02d분%02d초", hour, minute, second);
		}else if(hour < 10 && hour < 100) {
			time = String.format("%2d시간%02d분%02초", hour, minute, second);
		}else if(hour < 100 && hour < 1000) {
			time = String.format("%3d시간%02d분%02d초", hour, minute, second);
		}
//		System.out.println("스트레칭시간" + time);
    	return time;
    }  
    

}
