package HealthSchedule.controller;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
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

    @FXML
    public void saveAction(ActionEvent event) {
    	if (!writeTextField.getText().isEmpty()) {
    		videoname = writeTextField.getText();
    		System.out.println("good");
	      
	       	 AnchorPane anchorPane = new AnchorPane();
	       	 System.out.println("안커페인 만들었음");
            
            Label label = new Label(videoname);
            System.out.println("라벨 만들었음");
            AnchorPane.setLeftAnchor(label, 5.0);
            AnchorPane.setTopAnchor(label, 5.0);
         
            JFXButton button = new JFXButton("X");
            System.out.println("버튼 만들었음");
            button.setStyle("-fx-background-color: #33539E; -fx-text-fill: white;");
            button.setOnAction(evt -> {
            	content.getChildren().remove(anchorPane); //해당 안커페인 삭제
            	RoutineDao routineDao = new RoutineDao();
            	routineDao.deleteRoutine(everyday, "lowerbody", label.getText());
            });
            AnchorPane.setRightAnchor(button, 5.0);
            AnchorPane.setTopAnchor(button, 5.0);
            AnchorPane.setBottomAnchor(button, 5.0);
            anchorPane.getChildren().addAll(label, button);
            System.out.println("추가했음 만들었음");
            content.getChildren().add(anchorPane);
            System.out.println("vbox에 생성됨 만들었음");
            
            RoutineDao routineDao = new RoutineDao();
            routineDao.saveRoutine(everyday, "lowerbody", videoname);
		}
    	else {
			System.out.println("입력아직 안했습니다.");
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
    			timelabel.setText(time);
			}
    		else {
    			alarmText.setText("시간 양식을 맞추어 주세요");
			}
		}
    	else {
			System.out.println("시간 입력 안했습니다.");
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
		
		Tooltip.install(lowerImage, new Tooltip("클릭하여 유튜브 영상 보러 가기"));
		System.out.println("routine컨트롤러: " + year + month+ dayOfMonth);
		System.out.println(everyday);
		
	}

}
