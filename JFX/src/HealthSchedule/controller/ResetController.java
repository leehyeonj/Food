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
	
		if (drawerDao.ifexistFood()) {
			drawerDao.resetFood();
		}
		if (drawerDao.ifexistFoodtest()) {
			drawerDao.resetFoodtest();
		}
		if (drawerDao.ifexistRoutine()) {
			drawerDao.resetRoutine();
        	
		}
		if (drawerDao.ifexistMemo()) {
			drawerDao.resetMemo();
		}
		if (drawerDao.ifexistPhoto()) {
			drawerDao.resetPhoto();
		}
		if (drawerDao.ifexistWeight()) {
			drawerDao.resetWeight();
		}
		if (drawerDao.ifexistgoalWeight()) {
			drawerDao.resetGoalWeight();
		}
		if (drawerDao.ifexistTime()) {
			drawerDao.resetWorkoutTime();
		}
		
    	
    		alarmText.setText("�ʱ�ȭ�� �Ϸ�Ǿ����ϴ�. �ٽ� �����մϴ�.");
    	
		
    
        Thread thread = new Thread() {
            @Override
            public void run() {
            	
                    try { Thread.sleep(1000); } catch (InterruptedException e) {}
                    System.exit(0);
            }
        };
        thread.setDaemon(true);
        thread.start();

    }

    @FXML
    public void NoReset(ActionEvent event) {
    	stage.close();
    }
    
    	private double xOffset = 0;
	   private double yOffset = 0;
	   private Stage stage = null;

	   //ȭ�� �����϶� �������� ���ϰ� �ϱ�
	   private void stageDragableMoveWindow() {
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
	   private void actionMinWindow(MouseEvent event) {
	   // Launcher.stage.setIconified(true);
	   stage = (Stage) pane.getScene().getWindow();
	   stage.setIconified(true);
	   }

	   
	   //ȭ�� ����
	   @FXML
	   private void actionCloseWindow(MouseEvent event) {
	    stage.close();
	   }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		stageDragableMoveWindow();
		
	}
}
