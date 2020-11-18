package food;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MorningController implements Initializable{

	@FXML private Button btnOk;
	@FXML private Button btnSearch;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnOk.setOnAction(e->btnFoodAdd(e));
		
	}
	
	public void btnFoodAdd(ActionEvent event) {
		try {
			//Ȯ�ι�ư�� ������ �ٽ� todayFoodȭ������ ���ư�
			Parent checkOk = FXMLLoader.load(getClass().getResource("todayFood.fxml"));
			Scene scene = new Scene(checkOk);
			Stage primaryStage= (Stage)btnOk.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (Exception e2) {}
	}

	public void handelBtnSearch(ActionEvent event) {
		FXMLLoader another = new FXMLLoader(getClass().getResource("search.fxml") );
		try {
		   AnchorPane PickPage = (AnchorPane) another.load();
		   // �ٸ�â ���� �۾� .... 2
		   Scene anotherScene = new Scene( PickPage );
		   Stage stage = new  Stage();
		   stage.setScene(anotherScene);
		   stage.show();
		   // �ٸ�â ���� �۾� .... 2 ��.
		} catch (IOException e) {}
	}
}
