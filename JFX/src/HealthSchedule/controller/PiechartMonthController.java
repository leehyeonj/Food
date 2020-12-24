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
    @FXML Label full, upper, abs, lower, stretching;	//ü�ߺ���(�� �ð�)
    @FXML JFXButton monthchart;	//�Ѵ���Ʈ
    
    PieChartDao piechartdao = new PieChartDao();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		setTodayDate(year, month, dayOfMonth);
		stageDragableMoveWindow();
		ShowChart();
	}
	
	//piechartŸ��Ʋ����
	public void setTodayDate(String year, String month, String dayOfMonth) {
		pieChart.setTitle(month+"�� ���");
	}  
	
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
	
	
	//ȭ�� ����
	@FXML
	private void actionCloseWindow(MouseEvent event) {
		stage.close();
	}
	
	//��Ʈǥ��
    public void ShowChart(){  	
      	PiechartMonthController mc = new PiechartMonthController();
    	//piechart������ǥ��(������)
        ObservableList<Data> list = FXCollections.observableArrayList(
                new PieChart.Data("���ſ", piechartdao.selectFullbodyAll(1)),    
                new PieChart.Data("��ü�", piechartdao.selectUpperbodyAll(1)),
                new PieChart.Data("���ٿ", piechartdao.selectAbsAll(1)),
                new PieChart.Data("��ü�", piechartdao.selectLowerAll(1)),
                new PieChart.Data("��Ʈ��Ī", piechartdao.selectStretchingAll(1))
                );
        pieChart.setData(list);
        //�󺧿� ������ �ð� ����
        for (final PieChart.Data data : pieChart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    full.setText("���ſ\n" + mc.FullBodyTime());
                    upper.setText("��ü�\n" + mc.UpperBodyTime());
                    abs.setText("���ٿ\n" + mc.AbsTime());
                    lower.setText("��ü�\n" + mc.LowerBodyTime());
                    stretching.setText("��Ʈ��Ī\n" + mc.StretchingTime());
                }
            });	//addEventHandler
        }	//for
    }	//ShowChartbtn
    
	//������Ʈ�� �̵�
	public void DayChartbtn(ActionEvent e) {
		   try {
			   Parent members = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/piechartDay.fxml"));
			   Scene scene = new Scene(members);
			   Stage primaryStage= (Stage)monthchart.getScene().getWindow();
			   primaryStage.setScene(scene);
			 } catch (Exception e2) {}	
	}
    

	//���ſ�ð�����	00�ð�00��00��
    public String FullBodyTime() {
		int second = (int)Math.round(piechartdao.selectFullbodyAll(1));
		int minute = second / 60;
		int hour = minute / 60;
		second = second % 60;
		minute = minute % 60;
		String time = null;
		if(hour == 0) {
			 time = String.format("%02d�ð�%02d��%02d��",hour, minute, second);
		}else if(1 <= hour && hour < 10) {
			time = String.format("%1d�ð�%02d��%02d��", hour, minute, second);
		}else if(hour < 10 && hour < 100) {
			time = String.format("%2d�ð�%02d��%02��", hour, minute, second);
		}else if(hour < 100 && hour < 1000) {
			time = String.format("%3d�ð�%02d��%02d��", hour, minute, second);
		}
//		System.out.println("���ſ�ð�" + time);
    	return time;
    }
	//��ü��ð�����
    public String UpperBodyTime() {
		int second = (int)Math.round(piechartdao.selectUpperbodyAll(1));
		int minute = second / 60;
		int hour = minute / 60;
		second = second % 60;
		minute = minute % 60;
		String time = null;
		if(hour == 0) {
			 time = String.format("%02d�ð�%02d��%02d��",hour, minute, second);
		}else if(1 <= hour && hour < 10) {
			time = String.format("%1d�ð�%02d��%02d��", hour, minute, second);
		}else if(hour < 10 && hour < 100) {
			time = String.format("%2d�ð�%02d��%02��", hour, minute, second);
		}else if(hour < 100 && hour < 1000) {
			time = String.format("%3d�ð�%02d��%02d��", hour, minute, second);
		}
//		System.out.println("��ü��ð�" + time);
    	return time;
    }
	//���ٿ�ð�����
    public String AbsTime() {
		int second = (int)Math.round(piechartdao.selectAbsAll(1));
		int minute = second / 60;
		int hour = minute / 60;
		second = second % 60;
		minute = minute % 60;
		String time = null;
		if(hour == 0) {
			 time = String.format("%02d�ð�%02d��%02d��",hour, minute, second);
		}else if(1 <= hour && hour < 10) {
			time = String.format("%1d�ð�%02d��%02d��", hour, minute, second);
		}else if(hour < 10 && hour < 100) {
			time = String.format("%2d�ð�%02d��%02��", hour, minute, second);
		}else if(hour < 100 && hour < 1000) {
			time = String.format("%3d�ð�%02d��%02d��", hour, minute, second);
		}
//		System.out.println("���ٿ�ð�" + time);
    	return time;
    }
	//��ü��ð�����
    public String LowerBodyTime() {
		int second = (int)Math.round(piechartdao.selectLowerAll(1));
		int minute = second / 60;
		int hour = minute / 60;
		second = second % 60;
		minute = minute % 60;

		String time = null;
//		time = String.format("%02d�ð�%02d��%02d��",hour, minute, second);
		if(hour == 0) {
			 time = String.format("%02d�ð�%02d��%02d��",hour, minute, second);
		}else if(1 <= hour && hour < 10) {
			time = String.format("%1d�ð�%02d��%02d��", hour, minute, second);
		}else if(hour < 10 && hour < 100) {
			time = String.format("%2d�ð�%02d��%02��", hour, minute, second);
		}else if(hour < 100 && hour < 1000) {
			time = String.format("%3d�ð�%02d��%02d��", hour, minute, second);
		}
//		System.out.println("��ü��ð�" + time);
    	return time;
    }  
	//��Ʈ��Ī�ð�����
    public String StretchingTime() {
		int second = (int)Math.round(piechartdao.selectStretchingAll(1));
		int minute = second / 60;
		int hour = minute / 60;
		second = second % 60;
		minute = minute % 60;
		String time = null;
		if(hour == 0) {
			 time = String.format("%02d�ð�%02d��%02d��",hour, minute, second);
		}else if(1 <= hour && hour < 10) {
			time = String.format("%1d�ð�%02d��%02d��", hour, minute, second);
		}else if(hour < 10 && hour < 100) {
			time = String.format("%2d�ð�%02d��%02��", hour, minute, second);
		}else if(hour < 100 && hour < 1000) {
			time = String.format("%3d�ð�%02d��%02d��", hour, minute, second);
		}
//		System.out.println("��Ʈ��Ī�ð�" + time);
    	return time;
    }  
    

}
