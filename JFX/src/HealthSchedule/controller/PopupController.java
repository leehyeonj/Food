package HealthSchedule.controller;

import java.awt.Label;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PopupController implements Initializable{

	@FXML public Label popupmessage;
	 private Stage primaryStage ;
	 
	public PopupController(String message) {
		popupmessage.setText(message);
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
			try {
				System.out.println("�˾� ������ ����");
				Parent parent = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/popup.fxml"));
				Scene scene = new Scene(parent);
				primaryStage.setScene(scene);
				primaryStage.show();
			}catch (Exception e) {
				System.out.println("�˾� ��Ʈ�ѷ� initialize ����");
			}
		
	}

	//ȭ�� ����
	   @FXML
	   private void actionCloseWindow(MouseEvent event) {
	   System.exit(0);
	   }

	   
}
