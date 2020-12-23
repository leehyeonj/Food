package HealthSchedule.controller;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.sun.mail.handlers.image_gif;

import HealthSchedule.Dao.RoutineDao;
import HealthSchedule.Interface.ControllerSettable;
import HealthSchedule.Interface.TotalListener;
import HealthSchedule.model.Routines;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class RoutineController extends Main_everydayRecord_controller implements Initializable, ControllerSettable{
		private Routines routine;
		@FXML   private AnchorPane pane;//��ü ��Ŀ����
	    @FXML  private Label partname; //���� ��Ʈ���� �󺧷� ǥ��
	    @FXML   private JFXButton timesave; //timesave��ư
	    @FXML   private JFXTextField writeTextField; //��� �Է��ϴ� ��ư
	    @FXML   private Label alarmText;//�˶� �ؽ�Ʈ
	    @FXML   private ScrollPane scroll;// ��ũ��
	    @FXML   private VBox content; //����
	    @FXML   private ImageView partImage; //��ü (��Ʈ)�̹���
	    @FXML   private Label timelabel; //��ð�
	    @FXML   private JFXButton saveBtn; //� ���� �̸� save ��ư
//	    RoutineDao routineDao = new RoutineDao();
	    String videoname; //���� ���� �����Ѱ�
	    String time; // time �����Ѱ�
	    RoutineDao routineDao = new RoutineDao();
	    String part;
	    
	    private TotalTime totalTime;
	    private TotalListener totalListener ;
	    @FXML
	    void movePage() {
	    	part = routine.getName();
	    	try {
	            Desktop.getDesktop().browse(new URI("http://www.youtube.com/results?search_query=" + part));
	            }
	         catch (IOException e) {
	            e.printStackTrace(); }
	            catch (URISyntaxException e) { 
	               e.printStackTrace();
	               }
	    }
	    public void changeColor(String color) {
	    	pane.setStyle("-fx-background-color: #" + color +";\n" +
	    			" -fx-background-radius: 30;");
	    
	    }
	    public void changebtnColor(String color) {
	    	timesave.setStyle("-fx-background-color: #" + color );
	    	saveBtn.setStyle("-fx-background-color: #" + color );
	    
	    }
	    
	    public String setsmallbtncolor() {
	    	String colorbtn = null;
	    	if (partname.getText().equals("��ü�")) {
	    		colorbtn="33539E";
			}
	    	else if (partname.getText().equals("��Ʈ��Ī")) {
	    		colorbtn="FECC35";
			}
	    	else if  (partname.getText().equals("���ٿ")) {
	    		colorbtn="D67E9B";
			}
	    	else if  (partname.getText().equals("��ü�")) {
	    		colorbtn="8B4BB6";
			}
	    	else if  (partname.getText().equals("���ſ")) {
	    		colorbtn="285942";
			}
	    	return colorbtn;
	    	
	    }
	    
	    //���ƾ ������ set
	    ArrayList<Routines_lower> list = new ArrayList<>();
	    public void setDatas() {
	    	
//				System.out.println("��ƾ ��Ʈ�ѷ� �ٳ���" + routineslist.get(i).getName());
				boolean existRoutine = routineDao.ifexistRoutine(everyday);
				if (existRoutine) {
					list = routineDao.viewDayRoutine(everyday);
				}
			
	    	System.out.println("list size" + list.size());
	    	
	    	
	    	for (int i = 0; i < list.size(); i++) {
				int bunho =i;
				//main everyday record controller���� ȣ���ҰŴϱ� partname�� �ٲ�������
				if (partname.getText().equals(list.get(i).getBodypart()) ) {
					String videoname = list.get(i).getVideoname();
					AnchorPane anchorPane = new AnchorPane();
					System.out.println("anchorpane");
					Label label = new Label(videoname);
					 label.setStyle("-fx-font-family: 'SeoulNamsan EB';");
				    AnchorPane.setLeftAnchor(label, 5.0);
		            AnchorPane.setTopAnchor(label, 5.0);
		            JFXButton button = new JFXButton("X");
		           String color = setsmallbtncolor();
		            button.setStyle("-fx-background-color: #"+ color+"; -fx-text-fill: white;");
		            
		            button.setOnAction(evt -> {
		            	content.getChildren().remove(anchorPane); //�ش� ��Ŀ���� ����
//		            	RoutineDao routineDao = new RoutineDao();
		            
		            	routineDao.deleteRoutine(everyday, list.get(bunho).getBodypart(), list.get(bunho).getVideoname());
		            });
		            AnchorPane.setRightAnchor(button, 5.0);
		            AnchorPane.setTopAnchor(button, 5.0);
		            AnchorPane.setBottomAnchor(button, 5.0);
		            anchorPane.getChildren().addAll(label, button);
		       
		            content.getChildren().add(anchorPane);
				}
	    }
	   }
	  
	    
//	    ArrayList<RoutineTime> timeArrayList = new ArrayList<>();
	    public void setTimedata() {
	    	   RoutineTime routineTime = new RoutineTime();
//				System.out.println("��ƾ ��Ʈ�ѷ� �ٳ���" + routineslist.get(i).getName());
				boolean existRoutine = routineDao.ifexistTime(everyday, partname.getText());
				if (existRoutine) {
					routineTime = routineDao.viewDayTime(everyday,partname.getText());
				}
		
					int hour = routineTime.getTimehour();
					int minute = routineTime.getTimeminute();
					int second = routineTime.getTimesecond();
					
					String timetext = timeview(hour, minute, second);
					timelabel.setText(timetext);
				}
	    
	   
	    @Override
	    public void initialize(URL arg0, ResourceBundle arg1) {
	    	System.out.println("routinecontroller initialize");
	    	//�̹����� ����
			Tooltip.install(partImage, new Tooltip("Ŭ���Ͽ� ��Ʃ�� ���� ���� ����"));
	    	
		
	    }

	    //� ��ƾ ����
	    @FXML
	    public void saveAction(ActionEvent event) {
	    	if (!writeTextField.getText().isEmpty()) {
				videoname = writeTextField.getText();
				
				AnchorPane anchorPane = new AnchorPane();
				Label label = new Label(videoname);
				label.setStyle("-fx-font-family: 'SeoulNamsan EB';");
				AnchorPane.setLeftAnchor(label, 5.0);
				AnchorPane.setTopAnchor(label, 5.0);
				
				JFXButton button = new JFXButton("x");
				String color =setsmallbtncolor();
				button.setStyle("-fx-background-color: #"+color+"; -fx-text-fill: white;");
				button.setOnAction(evt->{
					content.getChildren().remove(anchorPane);
					routineDao.deleteRoutine(everyday, partname.getText(), label.getText());
				});
				AnchorPane.setRightAnchor(button, 5.0);
				AnchorPane.setTopAnchor(button, 5.0);
				AnchorPane.setBottomAnchor(button, 5.0);
				anchorPane.getChildren().addAll(label, button);
				content.getChildren().add(anchorPane);
				routineDao.saveRoutine(everyday, partname.getText(), label.getText());
				writeTextField.clear();
			}
	    	else {
				alarmText.setText("��� �Է����� �ʾҽ��ϴ�.");
			}
	    }

	  

	    //time ����
	    @FXML
	   public void timesaveAction(ActionEvent event) {
	    	String regExp = "[0-9]{2}:[0-9]{2}:[0-9]{2}";
	    	if (!writeTextField.getText().isEmpty()) {
	    		System.out.println("time");
	    		time = writeTextField.getText();
	    		if (Pattern.matches(regExp, time)) {
	    			timelabel.setText(time); // Ÿ�� �󺧿� setText
	    			
	    			StringTokenizer st = new StringTokenizer(time, ":");
	    			ArrayList<String> list = new ArrayList<>();
	    			while (st.hasMoreTokens()) {//�ð� ������� ��
						String str = st.nextToken();
						list.add(str);
					}
	    			
	    			int hour = Integer.parseInt(list.get(0));
	    			int minute = Integer.parseInt(list.get(1));
	    			int second = Integer.parseInt(list.get(2));
	    		
//	    			RoutineTime routineTime = new RoutineTime(everyday, "lowerbody", hour, minute, second);
	    			if(!routineDao.ifexistTime(everyday, partname.getText())) { //�׳� ����� ���� ������ ����
	    				routineDao.saveTime(everyday, partname.getText(), hour, minute, second);
	    				
	    			}
	    			else {//�׳� ����� ���� ������ ����
						routineDao.updateTime(everyday, partname.getText(), hour, minute, second);
					}

	    			totalListener.onClickListener(routine);
//	    			maineveryrecord.setTotalworkoutTime(routine);
//	    			dayController.setWorkoutTimeLabel(timelabel_main);
	    			
	    			
				}
	    		else {
	    			alarmText.setText("�ð� ����� ���߾� �ּ���");
				}
			}
	    	else {
				alarmText.setText("�ð��� �Է����� �ʾҽ��ϴ�.");
			}
	    }

	    public void setData(Routines routines, TotalListener totalListener) {
	    	this.routine = routines;
	    	this.totalListener = totalListener;
	    	partname.setText(routines.getName());
	    	Image image = new Image(getClass().getResourceAsStream(routines.getImgSrc()));
	    	partImage.setImage(image);
	    	
	    }
	    
	    public void setData(Routines routines) {
	    	this.routine = routines;
	    	
	    	partname.setText(routines.getName());
	    	Image image = new Image(getClass().getResourceAsStream(routines.getImgSrc()));
	    	partImage.setImage(image);
	    	
	    }
	    Main_everydayRecord_controller maineveryrecord = new Main_everydayRecord_controller();
		@Override
		public void setController(Initializable controller) {
			maineveryrecord = (Main_everydayRecord_controller) controller;
			
		}
		
		
	
}
