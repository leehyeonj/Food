package HealthSchedule.controller;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class WindowMove {

	 public double xOffset = 0;
	 public double yOffset = 0;
	 public Stage stage = null;
	@FXML
	private AnchorPane pane;
	  
	   //ȭ�� �����϶� �������� ���ϰ� �ϱ�
	 public void stageDragableMoveWindow() {
		   pane.setOnMousePressed((event) -> {
		   xOffset = event.getSceneX();
		   yOffset = event.getSceneY();
		   });
		   pane.setOnMouseDragged((event) -> {
		   // Launcher.stage.setX(event.getScreenX() - xOffset);
		   // Launcher.stage.setY(event.getScreenY() - yOffset);
		   // Launcher.stage.setOpacity(0.8f); // â ����ȭ
		   stage = (Stage) pane.getScene().getWindow();
		   stage.setX(event.getScreenX() - xOffset);
		   stage.setY(event.getScreenY() - yOffset);
		   stage.setOpacity(0.8f); // â ����ȭ
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

	   //ȭ�� �����
	   @FXML
	   public void actionMinWindow(MouseEvent event) {
	   // Launcher.stage.setIconified(true);
	   stage = (Stage) pane.getScene().getWindow();
	   stage.setIconified(true);
	   }
	   //��ü ȭ������
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
	   
	   //ȭ�� ����
	   @FXML
	   public void actionCloseWindow(MouseEvent event) {
	   System.exit(0);
	   }



}
