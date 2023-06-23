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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class HizmetlerController {
	
	public HizmetlerController(){
		baglanti = VeritabaniUtil.Baglan();
	}
	
	Connection baglanti=null;
	PreparedStatement sorguifadesi=null;
	ResultSet getirilen = null;
	String sql;
	int silinecekId;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_geri;

    @FXML
    private Button btn_sil;

    @FXML
    private TableColumn<Kayitlar_hizmet, String> col_Ad;

    @FXML
    private TableColumn<Kayitlar_hizmet, String> col_Adres;

    @FXML
    private TableColumn<Kayitlar_hizmet, Integer> col_ID;

    @FXML
    private TableColumn<Kayitlar_hizmet, String> col_Tel;

    @FXML
    private TableColumn<Kayitlar_hizmet, String> col_Tur;

    @FXML
    private Label lbl_sil;

    @FXML
    private TableView<Kayitlar_hizmet> tbl_tablo;

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

    @FXML
    void tbl_tablo_click(MouseEvent event) {
    	
    	//Silinecek kullanıcının ID'sini tıklayarak değişkene ata!
    	Kayitlar_hizmet secilenkayit =tbl_tablo.getSelectionModel().getSelectedItem();
    	silinecekId = secilenkayit.getId();
        System.out.println(silinecekId);

    }
    
    @FXML
    void btn_sil_click(ActionEvent event) {
    	//Buton ile Seçili Satırı silme!
    	sql = "delete from satis where hID=?";
    	 try {
			sorguifadesi=baglanti.prepareStatement(sql);
			sorguifadesi.setInt(1, silinecekId);
			sorguifadesi.executeUpdate();
			System.out.println("Silme İşlemi Başarılı");
			degergetir(tbl_tablo);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage().toString());
		}
    }
    

    
    public void degergetir(TableView tablo) {
    	sql = "select * from satis";
    	ObservableList<Kayitlar_hizmet> hizmetliste = FXCollections.observableArrayList();
    	
    	try {
    		
    		sorguifadesi = baglanti.prepareStatement(sql);
    		ResultSet getirilen = sorguifadesi.executeQuery();
    		while(getirilen.next()) {
    			hizmetliste.add(new Kayitlar_hizmet(getirilen.getInt("hID"), getirilen.getString("hiz_ad"), getirilen.getString("hiz_tur"), getirilen.getString("hiz_tel"), getirilen.getString("hiz_adres")));
    			
    		}
    		
    		col_ID.setCellValueFactory(new PropertyValueFactory<>("id"));
    		col_Ad.setCellValueFactory(new PropertyValueFactory<>("ad"));
    		col_Tur.setCellValueFactory(new PropertyValueFactory<>("tur"));
    		col_Tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
    		col_Adres.setCellValueFactory(new PropertyValueFactory<>("adres"));
    		tbl_tablo.setItems(hizmetliste);
			
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
		}
    	
    }
    @FXML
    void initialize() {
        
    	degergetir(tbl_tablo);
    }

}
