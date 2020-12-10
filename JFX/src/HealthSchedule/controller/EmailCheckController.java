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

	 


    	//�̸��� ������ȣ Ȯ��
	    @FXML
	    public void checkok(ActionEvent event) {
	    	if(codeTextField.getText().equals(String.valueOf(verificationCode))) {
	    		check = true;
	    	}
	    	if(check) {
	    		System.out.println("������ �Ϸ�Ǿ����ϴ�.");
	    		codeTextField.setText("���� �Ϸ�");
//	    		Stage pop = (Stage)closebtn.getScene().getWindow();
//	    		pop.close();
	    	}
	    	if (!check) {
	    		System.out.println("���� ����");
	    		codeTextField.setText("���� ����");
			}
	    	
	    }
	    
	    
	    
	    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		stageDragableMoveWindow();
		
	}

	//ȭ�� ����
	   @FXML
	   private void actionCloseWindow(MouseEvent event) {
		 Stage pop = (Stage)closebtn.getScene().getWindow();
		 pop.close();
	   }
	   private double xOffset = 0;
	   private double yOffset = 0;
	   private Stage stage = null;

	   //ȭ�� �����϶� �������� ���ϰ� �ϱ�
	   private void stageDragableMoveWindow() {
		   poppane.setOnMousePressed((event) -> {
		   xOffset = event.getSceneX();
		   yOffset = event.getSceneY();
		   });
		   poppane.setOnMouseDragged((event) -> {
		   // Launcher.stage.setX(event.getScreenX() - xOffset);
		   // Launcher.stage.setY(event.getScreenY() - yOffset);
		   // Launcher.stage.setOpacity(0.8f); // â ����ȭ
		   stage = (Stage) poppane.getScene().getWindow();
		   stage.setX(event.getScreenX() - xOffset);
		   stage.setY(event.getScreenY() - yOffset);
		   stage.setOpacity(0.8f); // â ����ȭ
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
