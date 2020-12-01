package HealthSchedule.Main;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import HealthSchedule.controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AppMain extends Application{
	
public static AppMain app;
	
	
	//��Ʈ�ѷ����� �����ϱ� ���� ���� �����.
	private Map<String, MainController> controllerMap = new HashMap<>();
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("AppMain");
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
