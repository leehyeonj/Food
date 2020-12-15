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
