package HealthSchedule.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main_everydayRecord_controller implements Initializable{

	@FXML private AnchorPane pane;
	@FXML private Label backLabel;
	@FXML private Label breakfast;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//stage 조정
		  stageDragableMoveWindow();
		  
		  
		
	}

	  //////////////////////////
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
	   //전체 화면으로
//	   @FXML
//	   private void actionMaxWindow(MouseEvent event) {
//	   // Launcher.stage.setFullScreen(true);
//	   // if (Launcher.stage.isMaximized()) {
//	   // Launcher.stage.setMaximized(false);
//	   // } else {
//	   // Launcher.stage.setMaximized(true);
//	   // }
//	   stage = (Stage) pane.getScene().getWindow();
//	   if (stage.isMaximized()) {
//	   stage.setMaximized(false);
//	   } else {
//	   stage.setMaximized(true);
//	   }
//	   }
	   
	   //화면 끄기
	   @FXML
	   private void actionCloseWindow(MouseEvent event) {
	   System.exit(0);
	   }

	   @FXML
	   private void actionBackWindow(MouseEvent event) {
		   try {
				//뒤로 가기 버튼을 누르면 뒤로감
				Parent checkOk = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/main.fxml"));
				Scene scene = new Scene(checkOk);
				Stage primaryStage= (Stage)backLabel.getScene().getWindow();
				primaryStage.setScene(scene);
			} catch (Exception e2) {}
	   }

	   @FXML
	   private void pageMove(MouseEvent event) {
			try {
				//음식 이름을 클릭하면 넘어간다.
				Parent checkOk = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/food_addMyDiet.fxml"));
				Scene scene = new Scene(checkOk);
				Stage primaryStage= (Stage)breakfast.getScene().getWindow();
				primaryStage.setScene(scene);
			} catch (Exception e2) {}
	   }
	   
	   @FXML
	   private void currentWeight(MouseEvent event) {
			try {
				//현재 몸무게를 클릭하면 넘어간다.
				Parent checkOk = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/food_search.fxml"));
				Scene scene = new Scene(checkOk);
				Stage primaryStage= (Stage)breakfast.getScene().getWindow();
				primaryStage.setScene(scene);
			} catch (Exception e2) {}
	   }
	   @FXML
	   private void goalWeight(MouseEvent event) {
			try {
				//목표 몸무게를 클릭하면 넘어간다.
				Parent checkOk = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/food_search.fxml"));
				Scene scene = new Scene(checkOk);
				Stage primaryStage= (Stage)breakfast.getScene().getWindow();
				primaryStage.setScene(scene);
			} catch (Exception e2) {}
	   }
	
}
