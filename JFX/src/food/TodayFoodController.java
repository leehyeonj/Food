package food;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class TodayFoodController implements Initializable{

	@FXML private Button morningFood;
	@FXML private Button lunchFood;
	@FXML private Button dinnerFood;
	@FXML private Button snackFood;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		morningFood.setOnAction(e->btnFoodAdd(e));
		
	}
	
	public void btnFoodAdd(ActionEvent event) {
		try {
			Parent foodPicker = FXMLLoader.load(getClass().getResource("morning.fxml"));
			StackPane root = (StackPane)morningFood.getScene().getRoot();
			root.getChildren().add(foodPicker);
		} catch (Exception e2) {}
	}

}
