package HealthSchedule.Main;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import HealthSchedule.controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AppMain extends Application{
	
public static AppMain app;
	
	
	//컨트롤러들을 저장하기 위한 맵을 만든다.
	private Map<String, MainController> controllerMap = new HashMap<>();
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		//폰트 안깨지게 하는 코드
		System.setProperty("prism.lcdtext", "false");
		//폰트 불러오기
		Font.loadFont(getClass().getResourceAsStream("/HealthSchedule/css/Typo_HongikinganL.ttf"), 11);
		Font.loadFont(getClass().getResourceAsStream("/HealthSchedule/css/SeoulNamsanEB.ttf"), 11);
		//폰트 패밀리 이름 알아내기
//		String fontFamily = "";
//		fontFamily = Font.loadFont(getClass().getResource("/HealthSchedule/css/Typo_HongikinganL.ttf").toString(), 16).getFamily(); 
//		System.out.println(fontFamily);
		
		Parent root = FXMLLoader.load(getClass().getResource("/HealthSchedule/resources/main.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.show();
		
	}
	
	public void setFocus(LocalDate date) {
		MainController mc = (MainController)controllerMap.get("main");
		mc.setClickData(date);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
