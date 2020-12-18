package HealthSchedule.controller;
 
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import HealthSchedule.Dao.FoodListDao;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
 
public class FoodTableviewController implements Initializable {
 
    private ObservableList<FoodListDao> productList = FXCollections.observableArrayList();	//음식전체끌어오는 ObservableList
    private ObservableList<FoodListDao> pluslist = FXCollections.observableArrayList();	//선택해서 테이블뷰2에 넣어야되는 ObservableList
    
	@FXML
	private TableView<FoodListDao> tableview1,tableview2;	//조회테이블뷰, 추가테이블뷰

	@FXML
	private TableColumn<FoodListDao, String> foodname, foodunit, cal;	//테이블뷰1 칼럼명
	@FXML
	private TableColumn<FoodListDao, String> tableview1_foodname, tableview1_foodunit, tableview1_cal;	//테이블뷰2 칼럼명
    @FXML private TextField searchtext;	//검색창
    @FXML private Button exit, plusList, delete;	//저장버튼,추가버튼,취소버튼

    //칼럼row저장을 위한 변수
    public String foodname1;
    public String foodunit1;
    public String cal1;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
  	
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
    	//tabeview1에 곧바로 목록출력
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
    	searchtext.textProperty().addListener((observable, oldValue, newValue) -> {	//우리가 필요한 것은 검색뿐이기에 newValue만 사용하게 된다
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
					return true;	//음식단위필터
				} else if (person.getCal().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;	//칼로리필터
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
	//삭제기능
	public void deletebtn(ActionEvent e) {
    	FoodListDao ld = new FoodListDao();
		String name = foodname1;
		String unit = foodunit1;
		String C = cal1;
		//System.out.println(name + " " + unit + " " + C);	//선택한 row가 제대로 들어왔는지 확인하기위함
		try {		
			ld.deleteFood(name, unit, C);
			
		} catch (SQLException e1) {
			Logger.getLogger(FoodTableviewController.class.getName()).log(Level.SEVERE, null, e1);
			System.out.println("호출실패");
			e1.printStackTrace();
		} 
		//tableview2에서 선택row삭제
		tableview2.getItems().removeAll(tableview2.getSelectionModel().getSelectedItem());	
	}	
	//선택된 항목 tableview2에 추가
    public void pluslistbtn(ActionEvent event) {
    	pluslist.clear();	//시작할때 목록한번 제거(중복출력을 막아준다). 제일 위에 둘것!
    	FoodListDao ld = new FoodListDao();
    	Connection conn;
		String name = foodname1;
		String unit = foodunit1;
		String C = cal1;
//		System.out.println(name + " " + unit + " " + C);	//선택한 row가 제대로 들어왔는지 확인하기위함
		try {		
			conn = ld.saveContent(name, unit, C);
			ResultSet rs = conn.createStatement().executeQuery("select * from Foodtest");
			System.out.println("식사db테이블지정완료");
			while(rs.next()) {
				pluslist.add(new FoodListDao(rs.getString("foodname"), rs.getString("foodunit"), rs.getString("cal")));
			}
		} catch (SQLException e1) {
			Logger.getLogger(FoodTableviewController.class.getName()).log(Level.SEVERE, null, e1);
			System.out.println("호출실패");
			e1.printStackTrace();
		}
		//끌어온 칼럼값
    	tableview1_foodname.setCellValueFactory(new PropertyValueFactory<>("name"));
    	tableview1_foodunit.setCellValueFactory(new PropertyValueFactory<>("unit"));
    	tableview1_cal.setCellValueFactory(new PropertyValueFactory<>("cal"));
    	tableview2.setItems(pluslist);
    }
    //종료(확인버튼)
    public void exitbtn(ActionEvent event) {
    	Stage stage = (Stage) exit.getScene().getWindow();
    	stage.close();
    }  
}