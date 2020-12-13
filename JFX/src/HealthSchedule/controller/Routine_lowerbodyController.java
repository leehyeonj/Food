package HealthSchedule.controller;

import java.awt.Desktop;
import java.awt.List;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Routine_lowerbodyController extends Main_everydayRecord_controller implements Initializable{
	@FXML private ImageView lowerImage;
	@FXML private JFXTextField writeTextField;
    @FXML private JFXButton saveBtn;
    @FXML private ScrollPane scroll;
    @FXML private VBox content;
    @FXML private Label timelabel;
    @FXML private Label alarmText;
    
    @FXML private JFXButton timesave;
    String videoname;
    Stage primaryStage;
    String time;
    static final String part = "lowerbody";

   RoutineDao routineDao = new RoutineDao();
    @FXML
    public void saveAction(ActionEvent event) {
    	if (!writeTextField.getText().isEmpty()) {
    		videoname = writeTextField.getText();
    		System.out.println("good");
	      
	       	 AnchorPane anchorPane = new AnchorPane();
	         Label label = new Label(videoname);
	         label.setStyle("-fx-font-family: 'SeoulNamsan EB';");
            AnchorPane.setLeftAnchor(label, 5.0);
            AnchorPane.setTopAnchor(label, 5.0);
         
            JFXButton button = new JFXButton("X");
            button.setStyle("-fx-background-color: #33539E; -fx-text-fill: white;");
            
            button.setOnAction(evt -> {
            	content.getChildren().remove(anchorPane); //�ش� ��Ŀ���� ����
//            	RoutineDao routineDao = new RoutineDao();
            	routineDao.deleteRoutine(everyday, part, label.getText());
            });
            AnchorPane.setRightAnchor(button, 5.0);
            AnchorPane.setTopAnchor(button, 5.0);
            AnchorPane.setBottomAnchor(button, 5.0);
            anchorPane.getChildren().addAll(label, button);
            content.getChildren().add(anchorPane); 
//            RoutineDao routineDao = new RoutineDao();
            routineDao.saveRoutine(everyday, part, videoname);
            writeTextField.clear();
		}
    	else {
			alarmText.setText("��� �Է����� �ʾҽ��ϴ�.");
		}
    	
    }

    @FXML
    public void timesaveAction(ActionEvent event) {
    	String regExp = "[0-9]{2}:[0-9]{2}:[0-9]{2}";
    	if (!writeTextField.getText().isEmpty()) {
    		System.out.println("time");
    		time = writeTextField.getText();
    		if (Pattern.matches(regExp, time)) {
    			timelabel.setText(time); // Ÿ�� �󺧿� setText
    			
    			StringTokenizer st = new StringTokenizer(time, ":");
    			ArrayList<String> list = new ArrayList<>();
    			while (st.hasMoreTokens()) {//�ð� ������� ��
					String str = st.nextToken();
					list.add(str);
				}
    			
    			int hour = Integer.parseInt(list.get(0));
    			int minute = Integer.parseInt(list.get(1));
    			int second = Integer.parseInt(list.get(2));
//    			RoutineTime routineTime = new RoutineTime(everyday, "lowerbody", hour, minute, second);
    			if(!routineDao.ifexistTime(everyday, part)) { //�׳� ����� ���� ������ ����
    				routineDao.saveTime(everyday, part, hour, minute, second);
    			}
    			else {//�׳� ����� ���� ������ ����
					routineDao.updateTime(everyday, part, hour, minute, second);
				}
    			
    			
			}
    		else {
    			alarmText.setText("�ð� ����� ���߾� �ּ���");
			}
		}
    	else {
			alarmText.setText("�ð��� �Է����� �ʾҽ��ϴ�.");
		}
    }

    

    
	//�Ű����� actionevent �� �ƴ�
	//imageview������ �Ѿ��
    public void movePage() {
    	try {
            Desktop.getDesktop().browse(new URI("http://www.youtube.com/results?search_query=��ü�"));
            }
         catch (IOException e) {
            e.printStackTrace(); }
            catch (URISyntaxException e) { 
               e.printStackTrace();
               }
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//�̹����� ����
		Tooltip.install(lowerImage, new Tooltip("Ŭ���Ͽ� ��Ʃ�� ���� ���� ����"));
		//������ �������� ����غ���
		System.out.println("routine��Ʈ�ѷ�: " + year + month+ dayOfMonth);
		System.out.println(everyday);
	
		//�׳��� ����� � �����Ͱ� �ֳ�
		boolean existRoutine = routineDao.ifexistRoutine(everyday);
		//�׳��� ����� �ð��� �ֳ�
		boolean existTime = routineDao.ifexistTime(everyday, part);
		//������ ���� �������� ������
		if (existRoutine) {
			
			ArrayList<Routines_lower> list = new ArrayList<>();
			list = routineDao.viewDayRoutine(everyday,part);
//			for (int i = 0; i < liststst.size(); i++) {
//				System.out.println(liststst.get(i).getEveryday() + "  "  + liststst.get(i).getBodypart() + " " + liststst.get(i).getVideoname());
//			}
			
//			 //����Ǿ��ִ� ��ƾ�� list�� �־ �����Ͷ�
			for (int i = 0; i < list.size(); i++) {
			
				int bunho =i;
				
					String videoname = routineDao.list.get(i).getVideoname();
					AnchorPane anchorPane = new AnchorPane();
					Label label = new Label(videoname);
					 label.setStyle("-fx-font-family: 'SeoulNamsan EB';");
				    AnchorPane.setLeftAnchor(label, 5.0);
		            AnchorPane.setTopAnchor(label, 5.0);
		            JFXButton button = new JFXButton("X");
		            button.setStyle("-fx-background-color: #33539E; -fx-text-fill: white;");
		            
		            button.setOnAction(evt -> {
		            	content.getChildren().remove(anchorPane); //�ش� ��Ŀ���� ����
//		            	RoutineDao routineDao = new RoutineDao();
		            	routineDao.deleteRoutine(everyday, routineDao.list.get(bunho).getBodypart(), routineDao.list.get(bunho).getVideoname());
		            });
		            AnchorPane.setRightAnchor(button, 5.0);
		            AnchorPane.setTopAnchor(button, 5.0);
		            AnchorPane.setBottomAnchor(button, 5.0);
		            anchorPane.getChildren().addAll(label, button);
		       
		            content.getChildren().add(anchorPane);
			}
			
		
		}
		
		//time�� ����Ǿ��ִٸ� �������� ǥ���ض�
		if(existTime) {
			ArrayList<RoutineTime> Timelist = new ArrayList<>();
			Timelist = routineDao.viewDayTime(everyday, part);
			
			int hour = Timelist.get(0).getTimehour();
			int minute = Timelist.get(0).getTimeminute();
			int second = Timelist.get(0).getTimesecond();
			
			totalHour += hour;
			totalMinute += minute;
			totalSecond += second;
			String timehour= "";
			if (hour<10) {
				timehour = "0"+hour;
			}
			else {
				timehour = Integer.toString(hour);
			}
			String timeminute= "";
			if (minute<10) {
				timeminute = "0"+minute;
			}
			else {
				timeminute = Integer.toString(minute);
			}
			String timesecond= "";
			if (second<10) {
				timesecond = "0"+second;
			}
			else {
				timesecond = Integer.toString(second);
			}
			timelabel.setText(timehour+":"+timeminute+":"+timesecond);//�� ���������� ǥ���Ѵ�.
			
		}
		
		
	}

}
