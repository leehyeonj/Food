<<<<<<< HEAD
package HealthSchedule.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import HealthSchedule.model.Memo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MemoController extends Main_everydayRecord_controller implements Initializable{

	@FXML TextField title;
	@FXML TextField content;
	@FXML Button close;
	@FXML Button save;
	
	MemoDao memodao = new MemoDao();
	
	
    //나가기 버튼
    @FXML
     public void actionBackWindow(MouseEvent event) {
        try {
           //뒤로 가기 버튼을 누르면 뒤로감
           Parent checkOk = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/main_everydayRecord.fxml"));
           Scene scene = new Scene(checkOk);
           Stage primaryStage= (Stage)close.getScene().getWindow();
           primaryStage.setScene(scene);
        } catch (Exception e2) {}
     }

    //날짜별 메모 가져오기
    


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Memo memo = memodao.viewMemo(everyday);
		String titleinit = memo.getTitle();
		String contentinit = memo.getContent();
		title.setText(titleinit);
		content.setText(contentinit);
		
	}
	
	
	public void saveAction(ActionEvent event) {
		if(!title.getText().isEmpty()) {
			String t = title.getText();
			String c = content.getText();
			memodao.saveContent(everyday, t, c);
		
	}else {
		System.out.println("저장이 안됐습니다.");
	}
	
	
	
}	
	
	
	public void closeAction(ActionEvent event) {
	
}

}
=======
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
				alarmLabel.setText("제목을 입력하지 않았습니다.");
			}
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

	   
	   //화면 끄기
	   @FXML
	   private void actionCloseWindow(MouseEvent event) {
	    stage.close();
	   }


}
>>>>>>> branch 'master' of https://github.com/leehyeonj/Food.git
