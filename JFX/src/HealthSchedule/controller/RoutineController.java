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
		@FXML   private AnchorPane pane;//전체 안커페인
	    @FXML  private Label partname; //무슨 파트인지 라벨로 표시
	    @FXML   private JFXButton timesave; //timesave버튼
	    @FXML   private JFXTextField writeTextField; //운동을 입력하는 버튼
	    @FXML   private Label alarmText;//알람 텍스트
	    @FXML   private ScrollPane scroll;// 스크롤
	    @FXML   private VBox content; //운동목록
	    @FXML   private ImageView partImage; //하체 (파트)이미지
	    @FXML   private Label timelabel; //운동시간
	    @FXML   private JFXButton saveBtn; //운동 비디오 이름 save 버튼
//	    RoutineDao routineDao = new RoutineDao();
	    String videoname; //비디오 네임 저장한거
	    String time; // time 저장한거
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
	    	if (partname.getText().equals("하체운동")) {
	    		colorbtn="33539E";
			}
	    	else if (partname.getText().equals("스트레칭")) {
	    		colorbtn="FECC35";
			}
	    	else if  (partname.getText().equals("복근운동")) {
	    		colorbtn="D67E9B";
			}
	    	else if  (partname.getText().equals("상체운동")) {
	    		colorbtn="8B4BB6";
			}
	    	else if  (partname.getText().equals("전신운동")) {
	    		colorbtn="285942";
			}
	    	return colorbtn;
	    	
	    }
	    
	    //운동루틴 데이터 set
	    ArrayList<Routines_lower> list = new ArrayList<>();
	    public void setDatas() {
	    	
//				System.out.println("루틴 컨트롤러 겟네임" + routineslist.get(i).getName());
				boolean existRoutine = routineDao.ifexistRoutine(everyday);
				if (existRoutine) {
					list = routineDao.viewDayRoutine(everyday);
				}
			
	    	System.out.println("list size" + list.size());
	    	
	    	
	    	for (int i = 0; i < list.size(); i++) {
				int bunho =i;
				//main everyday record controller에서 호출할거니까 partname이 바껴져있음
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
		            	content.getChildren().remove(anchorPane); //해당 안커페인 삭제
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
//				System.out.println("루틴 컨트롤러 겟네임" + routineslist.get(i).getName());
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
	    	//이미지뷰 툴팁
			Tooltip.install(partImage, new Tooltip("클릭하여 유튜브 영상 보러 가기"));
	    	
		
	    }

	    //운동 루틴 저장
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
				alarmText.setText("운동을 입력하지 않았습니다.");
			}
	    }

	  

	    //time 저장
	    @FXML
	   public void timesaveAction(ActionEvent event) {
	    	String regExp = "[0-9]{2}:[0-9]{2}:[0-9]{2}";
	    	if (!writeTextField.getText().isEmpty()) {
	    		System.out.println("time");
	    		time = writeTextField.getText();
	    		if (Pattern.matches(regExp, time)) {
	    			timelabel.setText(time); // 타임 라벨에 setText
	    			
	    			StringTokenizer st = new StringTokenizer(time, ":");
	    			ArrayList<String> list = new ArrayList<>();
	    			while (st.hasMoreTokens()) {//시간 순서대로 들어감
						String str = st.nextToken();
						list.add(str);
					}
	    			
	    			int hour = Integer.parseInt(list.get(0));
	    			int minute = Integer.parseInt(list.get(1));
	    			int second = Integer.parseInt(list.get(2));
	    		
//	    			RoutineTime routineTime = new RoutineTime(everyday, "lowerbody", hour, minute, second);
	    			if(!routineDao.ifexistTime(everyday, partname.getText())) { //그날 저장된 값이 없으면 저장
	    				routineDao.saveTime(everyday, partname.getText(), hour, minute, second);
	    				
	    			}
	    			else {//그날 저장된 값이 있으면 수정
						routineDao.updateTime(everyday, partname.getText(), hour, minute, second);
					}

	    			totalListener.onClickListener(routine);
//	    			maineveryrecord.setTotalworkoutTime(routine);
//	    			dayController.setWorkoutTimeLabel(timelabel_main);
	    			
	    			
				}
	    		else {
	    			alarmText.setText("시간 양식을 맞추어 주세요");
				}
			}
	    	else {
				alarmText.setText("시간을 입력하지 않았습니다.");
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
