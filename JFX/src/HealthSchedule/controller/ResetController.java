package HealthSchedule.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ResetController implements Initializable{

    @FXML private AnchorPane pane;
    @FXML private AnchorPane pane2;
	public void yesReset(ActionEvent event) {
		DrawerDao drawerDao = new DrawerDao();
		drawerDao.reset();
    }

//	  private Stage stage = null;
    public void NoReset(ActionEvent event) {
//    	 stage1.close();
    }

    @FXML
    public void yesWithdraw(ActionEvent event) {
    	DrawerDao drawerDao = new DrawerDao();
    	drawerDao.resetAccount();
    }

    @FXML
    public void noWithdraw(ActionEvent event) {
//    	stage.close();
    }
    
//    	private double xOffset = 0;
//	   private double yOffset = 0;
//	   private Stage stage = null;
//
//	   //화면 움직일때 투명으로 변하게 하기
//	   private void stageDragableMoveWindow() {
//		   pane.setOnMousePressed((event) -> {
//		   xOffset = event.getSceneX();
//		   yOffset = event.getSceneY();
//		   });
//		   pane.setOnMouseDragged((event) -> {
//		   // Launcher.stage.setX(event.getScreenX() - xOffset);
//		   // Launcher.stage.setY(event.getScreenY() - yOffset);
//		   // Launcher.stage.setOpacity(0.8f); // 창 투명화
//		   stage = (Stage) pane.getScene().getWindow();
//		   stage.setX(event.getScreenX() - xOffset);
//		   stage.setY(event.getScreenY() - yOffset);
//		   stage.setOpacity(0.8f); // 창 투명화
//		   });
//		   pane.setOnDragDone((event) -> {
//		   // Launcher.stage.setOpacity(1.0f);
//		   stage = (Stage) pane.getScene().getWindow();
//		   stage.setOpacity(1.0f);
//		   });
//		   pane.setOnMouseReleased((event) -> {
//		   // Launcher.stage.setOpacity(1.0f);
//		   stage = (Stage) pane.getScene().getWindow();
//		   stage.setOpacity(1.0f);
//		   });
//	   }
	   
//		private double xOffset1 = 0;
//		   private double yOffset1 = 0;
//		   private Stage stage1 = null;
//
//		   //화면 움직일때 투명으로 변하게 하기
//		   private void stageDragableMoveWindow1() {
//			   pane2.setOnMousePressed((event) -> {
//			   xOffset1 = event.getSceneX();
//			   yOffset1 = event.getSceneY();
//			   });
//			   pane.setOnMouseDragged((event) -> {
//			   // Launcher.stage.setX(event.getScreenX() - xOffset);
//			   // Launcher.stage.setY(event.getScreenY() - yOffset);
//			   // Launcher.stage.setOpacity(0.8f); // 창 투명화
//			   stage1 = (Stage) pane2.getScene().getWindow();
//			   stage1.setX(event.getScreenX() - xOffset1);
//			   stage1.setY(event.getScreenY() - yOffset1);
//			   stage1.setOpacity(0.8f); // 창 투명화
//			   });
//			   pane2.setOnDragDone((event) -> {
//			   // Launcher.stage.setOpacity(1.0f);
//			   stage1 = (Stage) pane2.getScene().getWindow();
//			   stage1.setOpacity(1.0f);
//			   });
//			   pane2.setOnMouseReleased((event) -> {
//			   // Launcher.stage.setOpacity(1.0f);
//			   stage1 = (Stage) pane2.getScene().getWindow();
//			   stage1.setOpacity(1.0f);
//			   });
//		   }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
//		stageDragableMoveWindow();
//		stageDragableMoveWindow1();
		
	}
}
