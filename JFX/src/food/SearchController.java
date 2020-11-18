package food;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SearchController implements Initializable{

	@FXML private Button btnPickOk;
	@FXML private Button btnPickCancel;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnPickOk.setOnAction(e->handlebtnPickOk(e));
		btnPickCancel.setOnAction(e->handlebtnPickCancel(e));
		
	}
	
	public void handlebtnPickOk(ActionEvent event) {
		try {
//			//확인버튼을 누르면 다시 todayFood화면으로 돌아감
//			Parent checkOk = FXMLLoader.load(getClass().getResource("morning.fxml"));
//			Scene scene = new Scene(checkOk);
//			Stage primaryStage= (Stage)btnPickOk.getScene().getWindow();
//			primaryStage.setScene(scene);
//			
			// 이 팝업을 닫음
			  Stage pop = (Stage) btnPickCancel.getScene().getWindow();
		      pop.close();
		} catch (Exception e2) {}

	}
	
	public void handlebtnPickCancel(ActionEvent event) {
		try {
			//확인버튼을 누르면 다시 todayFood화면으로 돌아감
//			Parent checkOk = FXMLLoader.load(getClass().getResource("morning.fxml"));
//			Scene scene = new Scene(checkOk);
//			Stage primaryStage= (Stage)btnPickOk.getScene().getWindow();
//			primaryStage.setScene(scene);
			
			  Stage pop = (Stage) btnPickCancel.getScene().getWindow();
		      pop.close();
		} catch (Exception e2) {}
	}

	
	



}
