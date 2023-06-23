package Uygulama;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import com.temizlikMySQL.util.VeritabaniUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GecmisController {
	
	public GecmisController() {
		
		baglanti = VeritabaniUtil.Baglan();
	}
	
	Connection baglanti=null;
	PreparedStatement sorguifadesi=null;
	ResultSet getirilen = null;
	String sql;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_geri;

    @FXML
    private TableColumn<Kayitlar_gecmis, Integer> col_ID;

    @FXML
    private TableColumn<Kayitlar_gecmis, String> col_kul;

    @FXML
    private AnchorPane pane1;

    @FXML
    private TableView<Kayitlar_gecmis> tbl_tablo;

    @FXML
    void btn_geri_click(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
		Stage stage1 = new Stage();						
		stage1.setTitle("Menu");
		stage1.getIcons().add(new Image("file:resources/images/logo.png"));
		Scene scene = new Scene(root,400,400);									
		stage1.setScene(scene);
		stage1.show();
		stage1 = (Stage)btn_geri.getScene().getWindow();
		stage1.close();
    }
    public void tablodoldur(TableView tablo) {
    	sql = "select * from gecmis";
    	ObservableList<Kayitlar_gecmis> gecmisliste = FXCollections.observableArrayList();
    	
    	try {
    		sorguifadesi = baglanti.prepareStatement(sql);
    		ResultSet getirilen = sorguifadesi.executeQuery();
    		while(getirilen.next()) {
    			gecmisliste.add(new Kayitlar_gecmis(getirilen.getInt("iID"), getirilen.getString("kul_ad")));
    		}
    		col_ID.setCellValueFactory(new PropertyValueFactory<>("id"));
    		col_kul.setCellValueFactory(new PropertyValueFactory<>("kullanici"));
    		tbl_tablo.setItems(gecmisliste);
		} 
    	
    	catch (Exception e) {
			System.out.println(e.getMessage().toString());
		}

	}
    @FXML
    void initialize() {
        tablodoldur(tbl_tablo);

    }

}
