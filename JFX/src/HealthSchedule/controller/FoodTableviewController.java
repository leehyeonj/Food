package HealthSchedule.controller;
 
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.controls.JFXButton;

import HealthSchedule.Dao.FoodListDao;
import HealthSchedule.Interface.ControllerSettable;
import HealthSchedule.Interface.ControllerSettable2;
import HealthSchedule.Interface.ControllerSettable3;
import HealthSchedule.Interface.FoodTableListener;
import HealthSchedule.model.Food;
import HealthSchedule.model.Foodlist;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

 
public class FoodTableviewController extends FoodController implements Initializable, ControllerSettable, ControllerSettable2{
 
	
	Food foodfood;
    private ObservableList<FoodListDao> productList = FXCollections.observableArrayList();   //음식전체끌어오는 ObservableList
    private ObservableList<FoodListDao> pluslist = FXCollections.observableArrayList();   //선택해서 테이블뷰2에 넣어야되는 ObservableList
    ArrayList<Foodlist> foodlistlist = new ArrayList<>();
    FoodTableListener foodTableListener;
    Foodlist foodlist;
    FoodController foodController;
    Main_everydayRecord_controller main_everydayRecord_controller;
    DayController dayController;
//    FoodListDao foodlistdao= new FoodListDao();
   @FXML
   private TableView<FoodListDao> tableview1,tableview2;   //조회테이블뷰, 추가테이블뷰

   @FXML
   private TableColumn<FoodListDao, String> foodname, foodunit, cal;   //테이블뷰1 칼럼명
   @FXML
   private TableColumn<FoodListDao, String> tableview1_foodname, tableview1_foodunit, tableview1_cal;   //테이블뷰2 칼럼명
    @FXML private TextField searchtext;   //검색창
    @FXML private JFXButton exit, plusList, delete, listrefresh;   //저장버튼,추가버튼,취소버튼

    @FXML
    private JFXButton plusFood;
    //칼럼row저장을 위한 변수
    public String foodname1;
    public String foodunit1;
    public String cal1;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	stageDragableMoveWindow();
    	
    	
       //추가기능. 테이블뷰 row선택. 칼럼값 저장
       tableview1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<FoodListDao>() {
         @Override
         public void changed(ObservableValue<? extends FoodListDao> observable, FoodListDao oldValue, FoodListDao newValue) {   
            if(newValue!=null) {  
               foodname1 = newValue.getName();
               foodunit1 = newValue.getUnit();
               cal1 = newValue.getCal();
            }
         }
      });
       //테이블뷰2 선택한 row를 삭제하기 위해 칼럼값을 저장
       tableview2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<FoodListDao>() {
         @Override
         public void changed(ObservableValue<? extends FoodListDao> observable, FoodListDao oldValue, FoodListDao newValue) {   
            if(newValue!=null) {
               foodname1 = newValue.getName();
               foodunit1 = newValue.getUnit();
               cal1 = newValue.getCal();
            }
         }
      });
   
       viewtable1();
       
       
    }
    
    public void viewtable1() {
        //tabeview1에 곧바로 목록출력
    	productList.clear();
        Connection conn;
        try {
           conn = FoodListDao.connect();
           ResultSet rs = conn.createStatement().executeQuery("select * from Food");
           System.out.println("음식목록db테이블지정완료");
           while(rs.next()) {
              productList.add(new FoodListDao(rs.getString("foodname"), rs.getString("foodunit"), rs.getString("cal")));      
           }
        } catch (SQLException e1) {
           Logger.getLogger(FoodTableviewController.class.getName()).log(Level.SEVERE, null, e1);
           System.out.println("호출실패");
           e1.printStackTrace();
        }
    
        //칼럼셀 별로 내용 set
        foodname.setCellValueFactory(new PropertyValueFactory<>("name"));
        foodunit.setCellValueFactory(new PropertyValueFactory<>("unit"));
        cal.setCellValueFactory(new PropertyValueFactory<>("cal"));
         //검색기능
         FilteredList<FoodListDao> filteredData = new FilteredList<>(productList, p -> true);   
         searchtext.textProperty().addListener((observable, oldValue, newValue) -> {   //우리가 필요한 것은 검색뿐이기에 newValue만 사용하게 된다
           filteredData.setPredicate(person -> {
              // 검색창이 비어있거나 공백일 경우
              if (newValue == null || newValue.isEmpty()) {
                 return true;
              }
              //검색필터
              String lowerCaseFilter = newValue.toLowerCase();
              if (person.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                 return true; // 음식이름필터
              } else if (person.getUnit().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                 return true;   //음식단위필터
              } else if (person.getCal().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                 return true;   //칼로리필터
              }
              return false; //매칭되는 것이 없을 경우
           });
        });
        SortedList<FoodListDao> sortedData = new SortedList<>(filteredData);
        //검색한 내용을 tableview1에 바인드. 실시간으로 검색버튼을 누르지 않은채 검색이 된다
        sortedData.comparatorProperty().bind(tableview1.comparatorProperty());
        
        //음식 전체목록 tableview1에 출력
        tableview1.setItems(sortedData);
    }
    //목록갱신
    public void refreshtable() {
    	tableview1.refresh();
    	viewtable1();
    }
    
   //삭제기능
   public void deletebtn(ActionEvent e) {
       FoodListDao ld = new FoodListDao();
      String name = foodname1;
      String unit = foodunit1;
      String C = cal1;
      //System.out.println(name + " " + unit + " " + C);   //선택한 row가 제대로 들어왔는지 확인하기위함
      try {      
         ld.deleteFood(name, unit, C);
//         foodlistlist = foodListDao.viewDayFood(everyday, eatTime);
//         foodTableListener.onClickListener(foodlistlist);
      } catch (SQLException e1) { System.out.println("음식 삭제 실패");  
      e1.printStackTrace();} 
      //tableview2에서 선택row삭제
      tableview2.getItems().removeAll(tableview2.getSelectionModel().getSelectedItem());   
   }   
  
//    
    public void pluslistbtn(ActionEvent event) throws SQLException {
        pluslist.clear();   //시작할때 목록한번 제거(중복출력을 막아준다). 제일 위에 둘것!
        FoodListDao ld = new FoodListDao();
        Connection conn;
       String name = foodname1;
       String unit = foodunit1;
       String C = cal1;
////       System.out.println(name + " " + unit + " " + C);   //선택한 row가 제대로 들어왔는지 확인하기위함
       try {      
          conn = ld.saveContent(everyday, eatTime, name, unit, C);
          PreparedStatement pstmt = null;
          String sql = "select * from Foodtest where everyday =? and eattime=?";
          //결과 값을 담을 곳
     
              pstmt = conn.prepareStatement(sql);
              pstmt.setString(1, everyday);
              pstmt.setString(2, eatTime);
              ResultSet rs = pstmt.executeQuery();
         
          System.out.println("식사db테이블저장 완료");
          while(rs.next()) {
             pluslist.add(new FoodListDao(rs.getString("foodname"), rs.getString("foodunit"), rs.getString("cal")));
          }
//          foodlistlist = foodListDao.viewDayFood(everyday, eatTime);
//        foodTableListener.onClickListener(foodlistlist);
          
       } catch (SQLException e1) {
    	  System.out.println("식사 데이터 삽입 실패");
    	  e1.printStackTrace();
       }

       //끌어온 칼럼값
        tableview1_foodname.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableview1_foodunit.setCellValueFactory(new PropertyValueFactory<>("unit"));
        tableview1_cal.setCellValueFactory(new PropertyValueFactory<>("cal"));
        tableview2.setItems(pluslist);
     	
//     	FoodListDao foodListDao = new FoodListDao();
//     	foodListDao.saveContent(everyday, "launch", name, unit, C);
     	
     }
    
    //종료(확인버튼)
    public void exitbtn(ActionEvent event) {
    	try {
    		System.out.println(everyday+"////////////");
    		System.out.println(eatTime+"//////////////");
    		foodlistlist = foodListDao.viewDayFood(everyday, eatTime);
    		foodfood = foodListDao.selecTotalKcal(everyday);
//    		foodController.setFoodData(foodlistlist);
    	}catch (Exception e) {
			e.printStackTrace();
		}
    	foodController.setFoodData(foodlistlist);
    	main_everydayRecord_controller.setTotalKcal2(foodfood);
//    	dayController.setDayKcal(foodfood);
       Stage stage = (Stage) exit.getScene().getWindow();
       stage.close();
    }  
  
    public void setinit(Food food, FoodTableListener foodTableListener) {
    	this.food = super.food;
    	this.foodTableListener = super.foodTableListener;
    }

    @FXML
    public void plusFoodbtn(ActionEvent event) {
    	 FXMLLoader another = new FXMLLoader(getClass().getResource("/HealthSchedule/resources/addFoodToTable.fxml") );

			try {

		   AnchorPane PickPage = (AnchorPane) another.load();
		   // 다른창 띄우는 작업 .... 2
		   Scene anotherScene = new Scene( PickPage );
		   Stage stage = new  Stage();
		   stage.setScene(anotherScene);
		   stage.initStyle(StageStyle.UNDECORATED);
		   stage.show();
		   // 다른창 띄우는 작업 .... 2 끝.

		} catch (IOException e) {} 
    }
    
//////////////////////////화면 조정//////////////////
    @FXML private AnchorPane pane;
		private double xOffset = 0;
		private double yOffset = 0;
		private Stage stage = null;

		//화면 움직일때 투명으로 변하게 하기
		private void stageDragableMoveWindow() {
				pane.setOnMousePressed((event) -> {
				xOffset = event.getSceneX();
				yOffset = event.getSceneY();
				});
				pane.setOnMouseDragged((event) -> {
				// Launcher.stage.setX(event.getScreenX() - xOffset);
				// Launcher.stage.setY(event.getScreenY() - yOffset);
				// Launcher.stage.setOpacity(0.8f); // 창 투명화
				stage = (Stage) pane.getScene().getWindow();
				stage.setX(event.getScreenX() - xOffset);
				stage.setY(event.getScreenY() - yOffset);
				stage.setOpacity(0.8f); // 창 투명화
				});
				pane.setOnDragDone((event) -> {
				// Launcher.stage.setOpacity(1.0f);
				stage = (Stage) pane.getScene().getWindow();
				stage.setOpacity(1.0f);
				});
				pane.setOnMouseReleased((event) -> {
				// Launcher.stage.setOpacity(1.0f);
				stage = (Stage) pane.getScene().getWindow();
				stage.setOpacity(1.0f);
				});
		}
		
		//화면 숨기기
		@FXML
		private void actionMinWindow(MouseEvent event) {
			// Launcher.stage.setIconified(true);
			stage = (Stage) pane.getScene().getWindow();
			stage.setIconified(true);
		}
		
		
		//화면 끄기
		@FXML
		private void actionCloseWindow(MouseEvent event) {
			stage.close();
		}

		@Override
		public void setController(Initializable controller) {
			foodController = (FoodController)controller;
		}
		
		@Override
		public void setController2(Initializable controller) {
			main_everydayRecord_controller = (Main_everydayRecord_controller)controller;
		}
		

		
}