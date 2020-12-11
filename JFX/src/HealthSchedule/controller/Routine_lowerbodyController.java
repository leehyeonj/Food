package HealthSchedule.controller;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Routine_lowerbodyController  implements Initializable{
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
            
            Label label = new Label(videoname);
            AnchorPane.setLeftAnchor(label, 5.0);
            AnchorPane.setTopAnchor(label, 5.0);
         
            JFXButton button = new JFXButton("X");
            button.setStyle("-fx-background-color: #33539E; -fx-text-fill: white;");
            button.setOnAction(evt -> content.getChildren().remove(anchorPane));
            AnchorPane.setRightAnchor(button, 5.0);
            AnchorPane.setTopAnchor(button, 5.0);
            AnchorPane.setBottomAnchor(button, 5.0);
            anchorPane.getChildren().addAll(label, button);
            content.getChildren().add(anchorPane);
		}
    	else {
			System.out.println("�Է¾��� ���߽��ϴ�.");
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
    			timelabel.setText(time);
			}
    		else {
    			alarmText.setText("�ð� ����� ���߾� �ּ���");
			}
		}
    	else {
			System.out.println("�ð� �Է� ���߽��ϴ�.");
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
		
	
		
	}

}
