package HealthSchedule.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import HealthSchedule.Dao.FoodListDao;
import HealthSchedule.model.FoodTable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AddFoodToTableController implements Initializable{

	FoodTableListener foodTableListener;
	FoodTable foodtable;
    @FXML private AnchorPane pane;
	@FXML private TextField foodName;
	@FXML private TextField foodKcal;
    @FXML private TextField foodUnit;
    @FXML private JFXButton savebtn;
    @FXML private Label alarmText;

//////////////////////////ȭ�� ����//////////////////
	
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
		
		
		  @FXML
		    public void save(ActionEvent event) {
			   if (!foodName.getText().isEmpty() && !foodUnit.getText().isEmpty() && !foodKcal.getText().isEmpty()) {
				FoodListDao foodlistdao = new FoodListDao();
				foodlistdao.saveFoodToTable(foodName.getText(), foodUnit.getText(), foodKcal.getText());
				alarmText.setText("����Ǿ����ϴ�.");
				
//				foodTableListener.onClickListener(foodtable);
			}
			   else {
				alarmText.setText("�Է����� �ʾҽ��ϴ�.");
			}
			  
			  
		    }
	    
	    
	    
}
