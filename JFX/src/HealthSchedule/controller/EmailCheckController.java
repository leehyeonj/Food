package HealthSchedule.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class EmailCheckController extends SignUpController implements Initializable{
	@FXML private Label closebtn;
	@FXML private AnchorPane poppane;
	@FXML private JFXTextField codeTextField;
    @FXML private JFXButton okbtn;

	 


    	//이메일 인증번호 확인
	    @FXML
	    public void checkok(ActionEvent event) {
	    	if(codeTextField.getText().equals(String.valueOf(verificationCode))) {
	    		check = true;
	    	}
	    	if(check) {
	    		System.out.println("인증이 완료되었습니다.");
	    		codeTextField.setText("인증 완료");
//	    		Stage pop = (Stage)closebtn.getScene().getWindow();
//	    		pop.close();
	    	}
	    	if (!check) {
	    		System.out.println("인증 실패");
	    		codeTextField.setText("인증 실패");
			}
	    	
	    }
	    
	    
	    
	    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		stageDragableMoveWindow();
		
	}

	//화면 끄기
	   @FXML
	   private void actionCloseWindow(MouseEvent event) {
		 Stage pop = (Stage)closebtn.getScene().getWindow();
		 pop.close();
	   }
	   private double xOffset = 0;
	   private double yOffset = 0;
	   private Stage stage = null;

	   //화면 움직일때 투명으로 변하게 하기
	   private void stageDragableMoveWindow() {
		   poppane.setOnMousePressed((event) -> {
		   xOffset = event.getSceneX();
		   yOffset = event.getSceneY();
		   });
		   poppane.setOnMouseDragged((event) -> {
		   // Launcher.stage.setX(event.getScreenX() - xOffset);
		   // Launcher.stage.setY(event.getScreenY() - yOffset);
		   // Launcher.stage.setOpacity(0.8f); // 창 투명화
		   stage = (Stage) poppane.getScene().getWindow();
		   stage.setX(event.getScreenX() - xOffset);
		   stage.setY(event.getScreenY() - yOffset);
		   stage.setOpacity(0.8f); // 창 투명화
		   });
		   poppane.setOnDragDone((event) -> {
		   // Launcher.stage.setOpacity(1.0f);
		   stage = (Stage) poppane.getScene().getWindow();
		   stage.setOpacity(1.0f);
		   });
		   poppane.setOnMouseReleased((event) -> {
		   // Launcher.stage.setOpacity(1.0f);
		   stage = (Stage) poppane.getScene().getWindow();
		   stage.setOpacity(1.0f);
		   });
	   }
}
