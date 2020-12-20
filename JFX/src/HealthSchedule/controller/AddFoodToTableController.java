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

//////////////////////////화면 조정//////////////////
	
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
		
		
		  @FXML
		    public void save(ActionEvent event) {
			   if (!foodName.getText().isEmpty() && !foodUnit.getText().isEmpty() && !foodKcal.getText().isEmpty()) {
				FoodListDao foodlistdao = new FoodListDao();
				foodlistdao.saveFoodToTable(foodName.getText(), foodUnit.getText(), foodKcal.getText());
				alarmText.setText("저장되었습니다.");
				
//				foodTableListener.onClickListener(foodtable);
			}
			   else {
				alarmText.setText("입력하지 않았습니다.");
			}
			  
			  
		    }
	    
	    
	    
}
