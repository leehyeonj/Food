package HealthSchedule.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;

import HealthSchedule.model.Memo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

 

public class MemoController extends Main_everydayRecord_controller implements Initializable{
	@FXML private Label alarmLabel;
    @FXML private JFXButton save;
    @FXML private JFXTextArea title;
    @FXML private TextField content;
    @FXML private AnchorPane pane;

	MemoDao memodao = new MemoDao();

    

	@Override

	public void initialize(URL location, ResourceBundle resources) {
		stageDragableMoveWindow();
		Memo memo = memodao.viewMemo(everyday);
		String titleinit = memo.getTitle();
		String contentinit = memo.getContent();
		title.setText(titleinit);
		content.setText(contentinit);
	}

	
	public void saveAction(ActionEvent event) {

			if(!title.getText().isEmpty()) {
		
					String saveTitle = title.getText();
					String saveContent = content.getText();
					
					if (!memodao.ifexistMemo(everyday)) {
						memodao.saveContent(everyday, saveTitle, saveContent);
					}else {
						memodao.updateMemo(everyday, saveTitle, saveContent);
					}
					
		
			}else {
				alarmLabel.setText("������ �Է����� �ʾҽ��ϴ�.");
			}
	}	
	  //////////////////////////
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


}