package HealthSchedule.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DrawerController implements Initializable{


    @FXML
    public void Withdrawal(ActionEvent event) {
    	 FXMLLoader another = new FXMLLoader(getClass().getResource("/HealthSchedule/resources/withdraw.fxml") );

			try {

		   AnchorPane PickPage = (AnchorPane) another.load();
		   // �ٸ�â ���� �۾� .... 2
		   Scene anotherScene = new Scene( PickPage );
		   Stage stage = new  Stage();
		   stage.setScene(anotherScene);
//		   stage.initStyle(StageStyle.UNDECORATED);
		   stage.show();
		   // �ٸ�â ���� �۾� .... 2 ��.

		} catch (IOException e) {} 
    }

    @FXML
    public void reset(ActionEvent event) {
    	  FXMLLoader another = new FXMLLoader(getClass().getResource("/HealthSchedule/resources/reset.fxml") );

			try {

		   AnchorPane PickPage = (AnchorPane) another.load();
		   // �ٸ�â ���� �۾� .... 2
		   Scene anotherScene = new Scene( PickPage );
		   Stage stage = new  Stage();
		   stage.setScene(anotherScene);
//		   stage.initStyle(StageStyle.UNDECORATED);
		   stage.show();
		   // �ٸ�â ���� �۾� .... 2 ��.

		} catch (IOException e) {} 

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
}
