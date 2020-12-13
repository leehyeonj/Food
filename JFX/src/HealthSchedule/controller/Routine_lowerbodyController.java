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
            	content.getChildren().remove(anchorPane); //해당 안커페인 삭제
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
			alarmText.setText("운동을 입력하지 않았습니다.");
		}
    	
    }

    @FXML
    public void timesaveAction(ActionEvent event) {
    	String regExp = "[0-9]{2}:[0-9]{2}:[0-9]{2}";
    	if (!writeTextField.getText().isEmpty()) {
    		System.out.println("time");
    		time = writeTextField.getText();
    		if (Pattern.matches(regExp, time)) {
    			timelabel.setText(time); // 타임 라벨에 setText
    			
    			StringTokenizer st = new StringTokenizer(time, ":");
    			ArrayList<String> list = new ArrayList<>();
    			while (st.hasMoreTokens()) {//시간 순서대로 들어감
					String str = st.nextToken();
					list.add(str);
				}
    			
    			int hour = Integer.parseInt(list.get(0));
    			int minute = Integer.parseInt(list.get(1));
    			int second = Integer.parseInt(list.get(2));
//    			RoutineTime routineTime = new RoutineTime(everyday, "lowerbody", hour, minute, second);
    			if(!routineDao.ifexistTime(everyday, part)) { //그날 저장된 값이 없으면 저장
    				routineDao.saveTime(everyday, part, hour, minute, second);
    			}
    			else {//그날 저장된 값이 있으면 수정
					routineDao.updateTime(everyday, part, hour, minute, second);
				}
    			
    			
			}
    		else {
    			alarmText.setText("시간 양식을 맞추어 주세요");
			}
		}
    	else {
			alarmText.setText("시간을 입력하지 않았습니다.");
		}
    }

    

    
	//매개변수 actionevent 가 아님
	//imageview누르면 넘어가게
    public void movePage() {
    	try {
            Desktop.getDesktop().browse(new URI("http://www.youtube.com/results?search_query=하체운동"));
            }
         catch (IOException e) {
            e.printStackTrace(); }
            catch (URISyntaxException e) { 
               e.printStackTrace();
               }
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//이미지뷰 툴팁
		Tooltip.install(lowerImage, new Tooltip("클릭하여 유튜브 영상 보러 가기"));
		//언제를 눌렀는지 출력해보자
		System.out.println("routine컨트롤러: " + year + month+ dayOfMonth);
		System.out.println(everyday);
	
		//그날에 저장된 운동 데이터가 있냐
		boolean existRoutine = routineDao.ifexistRoutine(everyday);
		//그날에 저장된 시간이 있냐
		boolean existTime = routineDao.ifexistTime(everyday, part);
		//있으면 라벨을 원래부터 만들어라
		if (existRoutine) {
			
			ArrayList<Routines_lower> list = new ArrayList<>();
			list = routineDao.viewDayRoutine(everyday,part);
//			for (int i = 0; i < liststst.size(); i++) {
//				System.out.println(liststst.get(i).getEveryday() + "  "  + liststst.get(i).getBodypart() + " " + liststst.get(i).getVideoname());
//			}
			
//			 //저장되어있는 루틴을 list에 넣어서 가져와라
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
		            	content.getChildren().remove(anchorPane); //해당 안커페인 삭제
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
		
		//time이 저장되어있다면 원래부터 표시해라
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
			timelabel.setText(timehour+":"+timeminute+":"+timesecond);//맨 마지막꺼를 표시한다.
			
		}
		
		
	}

}
