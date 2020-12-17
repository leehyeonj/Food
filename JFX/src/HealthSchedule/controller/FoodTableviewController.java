package HealthSchedule.controller;
 
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    @FXML private Button search, search1, plusList;	//검색버튼,조회버튼,추가버튼

    //칼럼row저장을 위한 변수
    public String foodname1;
    public String foodunit1;
    public String cal1;
//    FoodListDao FoodListDao = new FoodListDao();
//    FoodListDao.add();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	productList.clear();
//    	search.setOnAction(e -> handleBtnSelect(e));	//조회 후 검색, 혹은 검색하여 바로 조회하려고 하니 되지 않는다
//    	search1.setOnAction(e -> searchbtn(e));

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

 
	
	//선택된 항목 tableview2에 추가
    public void pluslistbtn(ActionEvent event) {
//    	FoodListDao ld = new FoodListDao();
//    	Connection conn;
//		String name = foodname1;
//		String unit = foodunit1;
//		String C = cal1;
//		//System.out.println(name + " " + unit + " " + C);	//선택한 row가 제대로 들어왔는지 확인하기위함
//		try {		
//			conn = ld.saveContent(name, unit, C);
//			ResultSet rs = conn.createStatement().executeQuery("select * from Foodtest");
//			System.out.println("식사db테이블지정완료");
//			while(rs.next()) {
//				pluslist.add(new FoodListDao(rs.getString("foodname"), rs.getString("foodunit"), rs.getString("cal")));
//			}
//		} catch (SQLException e1) {
//			Logger.getLogger(FoodTableviewController.class.getName()).log(Level.SEVERE, null, e1);
//			System.out.println("호출실패");
//			e1.printStackTrace();
//		}    	
		//위의 코드를 두고 아래의 코드를 주석처리하지 않으면 두개씩 출력하게 된다
		//순서: db에 있는 foodtest테이블에 추가된 값 저장->foodtest테이블지정하여 값호출->pluslist에 값 저장->setitems로 tableview2의 각 칼럼에 저장 및 출력
		//만약 목록추가를 먼저 한 후 저장버튼액션을 따로 두어 db에 저장하는 순서를 뒤로 미루고 싶다면 아래의 코드를 활성화시키고 위에 있는 코드들을 주석처리한 후 저장버튼액션에서 db에 저장하면 된다
    	pluslist.add(new FoodListDao(foodname1, foodunit1, cal1));

    	tableview1_foodname.setCellValueFactory(new PropertyValueFactory<>("name"));
    	tableview1_foodunit.setCellValueFactory(new PropertyValueFactory<>("unit"));
    	tableview1_cal.setCellValueFactory(new PropertyValueFactory<>("cal"));
    	tableview2.setItems(pluslist);
    }
}