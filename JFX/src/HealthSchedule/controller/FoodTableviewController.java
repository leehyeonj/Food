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
    private ObservableList<FoodListDao> productList = FXCollections.observableArrayList();   //������ü������� ObservableList
    private ObservableList<FoodListDao> pluslist = FXCollections.observableArrayList();   //�����ؼ� ���̺��2�� �־�ߵǴ� ObservableList
    ArrayList<Foodlist> foodlistlist = new ArrayList<>();
    FoodTableListener foodTableListener;
    Foodlist foodlist;
    FoodController foodController;
    Main_everydayRecord_controller main_everydayRecord_controller;
    DayController dayController;
//    FoodListDao foodlistdao= new FoodListDao();
   @FXML
   private TableView<FoodListDao> tableview1,tableview2;   //��ȸ���̺��, �߰����̺��

   @FXML
   private TableColumn<FoodListDao, String> foodname, foodunit, cal;   //���̺��1 Į����
   @FXML
   private TableColumn<FoodListDao, String> tableview1_foodname, tableview1_foodunit, tableview1_cal;   //���̺��2 Į����
    @FXML private TextField searchtext;   //�˻�â
    @FXML private JFXButton exit, plusList, delete, listrefresh;   //�����ư,�߰���ư,��ҹ�ư

    @FXML
    private JFXButton plusFood;
    //Į��row������ ���� ����
    public String foodname1;
    public String foodunit1;
    public String cal1;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	stageDragableMoveWindow();
    	
    	
       //�߰����. ���̺�� row����. Į���� ����
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
       //���̺��2 ������ row�� �����ϱ� ���� Į������ ����
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
        //tabeview1�� ��ٷ� ������
    	productList.clear();
        Connection conn;
        try {
           conn = FoodListDao.connect();
           ResultSet rs = conn.createStatement().executeQuery("select * from Food");
           System.out.println("���ĸ��db���̺������Ϸ�");
           while(rs.next()) {
              productList.add(new FoodListDao(rs.getString("foodname"), rs.getString("foodunit"), rs.getString("cal")));      
           }
        } catch (SQLException e1) {
           Logger.getLogger(FoodTableviewController.class.getName()).log(Level.SEVERE, null, e1);
           System.out.println("ȣ�����");
           e1.printStackTrace();
        }
    
        //Į���� ���� ���� set
        foodname.setCellValueFactory(new PropertyValueFactory<>("name"));
        foodunit.setCellValueFactory(new PropertyValueFactory<>("unit"));
        cal.setCellValueFactory(new PropertyValueFactory<>("cal"));
         //�˻����
         FilteredList<FoodListDao> filteredData = new FilteredList<>(productList, p -> true);   
         searchtext.textProperty().addListener((observable, oldValue, newValue) -> {   //�츮�� �ʿ��� ���� �˻����̱⿡ newValue�� ����ϰ� �ȴ�
           filteredData.setPredicate(person -> {
              // �˻�â�� ����ְų� ������ ���
              if (newValue == null || newValue.isEmpty()) {
                 return true;
              }
              //�˻�����
              String lowerCaseFilter = newValue.toLowerCase();
              if (person.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                 return true; // �����̸�����
              } else if (person.getUnit().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                 return true;   //���Ĵ�������
              } else if (person.getCal().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                 return true;   //Į�θ�����
              }
              return false; //��Ī�Ǵ� ���� ���� ���
           });
        });
        SortedList<FoodListDao> sortedData = new SortedList<>(filteredData);
        //�˻��� ������ tableview1�� ���ε�. �ǽð����� �˻���ư�� ������ ����ä �˻��� �ȴ�
        sortedData.comparatorProperty().bind(tableview1.comparatorProperty());
        
        //���� ��ü��� tableview1�� ���
        tableview1.setItems(sortedData);
    }
    //��ϰ���
    public void refreshtable() {
    	tableview1.refresh();
    	viewtable1();
    }
    
   //�������
   public void deletebtn(ActionEvent e) {
       FoodListDao ld = new FoodListDao();
      String name = foodname1;
      String unit = foodunit1;
      String C = cal1;
      //System.out.println(name + " " + unit + " " + C);   //������ row�� ����� ���Դ��� Ȯ���ϱ�����
      try {      
         ld.deleteFood(name, unit, C);
//         foodlistlist = foodListDao.viewDayFood(everyday, eatTime);
//         foodTableListener.onClickListener(foodlistlist);
      } catch (SQLException e1) { System.out.println("���� ���� ����");  
      e1.printStackTrace();} 
      //tableview2���� ����row����
      tableview2.getItems().removeAll(tableview2.getSelectionModel().getSelectedItem());   
   }   
  
//    
    public void pluslistbtn(ActionEvent event) throws SQLException {
        pluslist.clear();   //�����Ҷ� ����ѹ� ����(�ߺ������ �����ش�). ���� ���� �Ѱ�!
        FoodListDao ld = new FoodListDao();
        Connection conn;
       String name = foodname1;
       String unit = foodunit1;
       String C = cal1;
////       System.out.println(name + " " + unit + " " + C);   //������ row�� ����� ���Դ��� Ȯ���ϱ�����
       try {      
          conn = ld.saveContent(everyday, eatTime, name, unit, C);
          PreparedStatement pstmt = null;
          String sql = "select * from Foodtest where everyday =? and eattime=?";
          //��� ���� ���� ��
     
              pstmt = conn.prepareStatement(sql);
              pstmt.setString(1, everyday);
              pstmt.setString(2, eatTime);
              ResultSet rs = pstmt.executeQuery();
         
          System.out.println("�Ļ�db���̺����� �Ϸ�");
          while(rs.next()) {
             pluslist.add(new FoodListDao(rs.getString("foodname"), rs.getString("foodunit"), rs.getString("cal")));
          }
//          foodlistlist = foodListDao.viewDayFood(everyday, eatTime);
//        foodTableListener.onClickListener(foodlistlist);
          
       } catch (SQLException e1) {
    	  System.out.println("�Ļ� ������ ���� ����");
    	  e1.printStackTrace();
       }

       //����� Į����
        tableview1_foodname.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableview1_foodunit.setCellValueFactory(new PropertyValueFactory<>("unit"));
        tableview1_cal.setCellValueFactory(new PropertyValueFactory<>("cal"));
        tableview2.setItems(pluslist);
     	
//     	FoodListDao foodListDao = new FoodListDao();
//     	foodListDao.saveContent(everyday, "launch", name, unit, C);
     	
     }
    
    //����(Ȯ�ι�ư)
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
		   // �ٸ�â ���� �۾� .... 2
		   Scene anotherScene = new Scene( PickPage );
		   Stage stage = new  Stage();
		   stage.setScene(anotherScene);
		   stage.initStyle(StageStyle.UNDECORATED);
		   stage.show();
		   // �ٸ�â ���� �۾� .... 2 ��.

		} catch (IOException e) {} 
    }
    
//////////////////////////ȭ�� ����//////////////////
    @FXML private AnchorPane pane;
		private double xOffset = 0;
		private double yOffset = 0;
		private Stage stage = null;

		//ȭ�� �����϶� �������� ���ϰ� �ϱ�
		private void stageDragableMoveWindow() {
				pane.setOnMousePressed((event) -> {
				xOffset = event.getSceneX();
				yOffset = event.getSceneY();
				});
				pane.setOnMouseDragged((event) -> {
				// Launcher.stage.setX(event.getScreenX() - xOffset);
				// Launcher.stage.setY(event.getScreenY() - yOffset);
				// Launcher.stage.setOpacity(0.8f); // â ����ȭ
				stage = (Stage) pane.getScene().getWindow();
				stage.setX(event.getScreenX() - xOffset);
				stage.setY(event.getScreenY() - yOffset);
				stage.setOpacity(0.8f); // â ����ȭ
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
		
		//ȭ�� �����
		@FXML
		private void actionMinWindow(MouseEvent event) {
			// Launcher.stage.setIconified(true);
			stage = (Stage) pane.getScene().getWindow();
			stage.setIconified(true);
		}
		
		
		//ȭ�� ����
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