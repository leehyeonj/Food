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
	
	
	//��Ʈ�ѷ����� �����ϱ� ���� ���� �����.
	private Map<String, MainController> controllerMap = new HashMap<>();
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		//��Ʈ �ȱ����� �ϴ� �ڵ�
		System.setProperty("prism.lcdtext", "false");
		//��Ʈ �ҷ�����
		Font.loadFont(getClass().getResourceAsStream("/HealthSchedule/css/Typo_HongikinganL.ttf"), 11);
		Font.loadFont(getClass().getResourceAsStream("/HealthSchedule/css/SeoulNamsanEB.ttf"), 11);
		//��Ʈ �йи� �̸� �˾Ƴ���
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
