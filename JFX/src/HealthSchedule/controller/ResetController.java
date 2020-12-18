package HealthSchedule.controller;

import java.net.URL;
import java.util.ResourceBundle;

import HealthSchedule.Dao.DrawerDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ResetController implements Initializable{

    @FXML private AnchorPane pane;
    @FXML private Label alarmText;
	

    @FXML
    public void yesReset(ActionEvent event) {
    	DrawerDao drawerDao = new DrawerDao();
    	drawerDao.reset1();
    	drawerDao.reset2();
    	drawerDao.reset3();
    	drawerDao.reset4();
    	if (drawerDao.reset4) {
			alarmText.setText("초기화가 완료되었습니다.");
		}
    }

    @FXML
    public void NoReset(ActionEvent event) {
    	stage.close();
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		stageDragableMoveWindow();
		
	}
}
