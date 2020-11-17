package page_move;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class LoginController implements Initializable{

	@FXML private BorderPane login;
	@FXML private Button btnMain;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnMain.setOnAction(e-> handleBtnMain(e));
		
	}

	public void handleBtnMain(ActionEvent event) {
		try {
			StackPane root = (StackPane)btnMain.getScene().getRoot();
			login.setTranslateX(0);
			//시작값을 0으로 설정
			
			Timeline timeline = new Timeline();
			KeyValue keyValue = new KeyValue(login.translateXProperty(), 350);
			KeyFrame keyFrame = new KeyFrame(Duration.millis(100), new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					root.getChildren().remove(login);
					//애니메이션 종료후 로그인 화면 제거
				}
			}, keyValue);
			
			timeline.getKeyFrames().add(keyFrame);
			timeline.play();
			
		} catch (Exception e) {}
	}
}
