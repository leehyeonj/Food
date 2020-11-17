package food;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MorningController implements Initializable{

	@FXML private Button btnOk;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnOk.setOnAction(e->btnFoodAdd(e));
		
	}
	
	public void btnFoodAdd(ActionEvent event) {
		try {
			//확인버튼을 누르면 다시 todayFood화면으로 돌아감
			Parent checkOk = FXMLLoader.load(getClass().getResource("todayFood.fxml"));
			Scene scene = new Scene(checkOk);
			Stage primaryStage= (Stage)btnOk.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (Exception e2) {}
	}

}
