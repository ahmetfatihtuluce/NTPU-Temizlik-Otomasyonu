package Uygulama;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;

public class KullanicilarController {
	
	public KullanicilarController() {
		 baglanti = VeritabaniUtil.Baglan();
	}
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_ekle;

    @FXML
    private Button btn_geri;

    @FXML
    private Button btn_sil;

    @FXML
    private Button btn_yenile;
    

    @FXML
    private ComboBox<Integer> cmb_yetki;

    @FXML
    private Label lbl_kulad;

    @FXML
    private Label lbl_sifre;

    @FXML
    private Label lbl_yeniekle;

    @FXML
    private Label lbl_yetki;

    @FXML
    private TableColumn<Kayitlar_login, Integer> tbl_Id;

    @FXML
    private TableColumn<Kayitlar_login, String> tbl_kulad;

    @FXML
    private TableColumn<Kayitlar_login, String> tbl_sifre;

    @FXML
    private TableView<Kayitlar_login> tbl_tablo;

    @FXML
    private TableColumn<Kayitlar_login, Integer> tbl_yetki;

    @FXML
    private TextField txt_kulad;

    @FXML
    private TextField txt_sifre;
    
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
    void btn_ekle_click(ActionEvent event) {
    	sql ="insert into login(kul_ad, sifre, yetki) values(?,?,?)" ;
    	try {
    		
    		if(txt_kulad!=null && txt_sifre!=null && cmb_yetki.getSelectionModel().getSelectedIndex()!=-1) {
    			sorguifadesi = baglanti.prepareStatement(sql);
    			sorguifadesi.setString(1, txt_kulad.getText().trim());
    			sorguifadesi.setString(2, VeritabaniUtil.MD5Sifrele(txt_sifre.getText().trim()) );
    			sorguifadesi.setInt(3, cmb_yetki.getSelectionModel().getSelectedIndex());
    			sorguifadesi.executeUpdate();
    			System.out.println("Başarılı");
    			Degergetir(tbl_tablo);
    			txt_kulad.clear();
    			txt_sifre.clear();
    			cmb_yetki.getSelectionModel().clearSelection();
    		}
    		else{System.out.println("Hata");}
    		
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage().toString());
		}
    }
    int silinecekId;
    @FXML
    void tbl_mouse_click(MouseEvent event) {
    	
    	//Silinecek kullanıcının ID'sini tıklayarak değişkene ata!
    	Kayitlar_login secilenkayit =tbl_tablo.getSelectionModel().getSelectedItem();
        silinecekId = secilenkayit.getId();
        System.out.println(silinecekId);
    }
    @FXML
    void btn_sil_click(ActionEvent event) {
    	
    	//Buton ile Seçili Satırı silme!
    	sql = "delete from login where kID=?";
    	 try {
			sorguifadesi=baglanti.prepareStatement(sql);
			sorguifadesi.setInt(1, silinecekId);
			sorguifadesi.executeUpdate();
			System.out.println("Silme İşlemi Başarılı");
			Degergetir(tbl_tablo);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage().toString());
		}
    	
    }
     
    Connection baglanti=null;
	PreparedStatement sorguifadesi=null;
	ResultSet getirilen = null;
	String sql;
    
    public void Degergetir(TableView tablo) {
    	sql = "select * from login";
    	ObservableList<Kayitlar_login> kayitlarliste = FXCollections.observableArrayList();
    	
    	try {
    		
    		sorguifadesi = baglanti.prepareStatement(sql);
    		ResultSet getirilen = sorguifadesi.executeQuery();
    		while(getirilen.next()) {
    			kayitlarliste.add(new Kayitlar_login(getirilen.getInt("kID"),getirilen.getString("kul_ad"), getirilen.getString("sifre"), getirilen.getInt("yetki")));
    			
    		}
    		tbl_Id.setCellValueFactory(new PropertyValueFactory<>("id"));
    		tbl_kulad.setCellValueFactory(new PropertyValueFactory<>("kul_ad"));
    		tbl_sifre.setCellValueFactory(new PropertyValueFactory<>("sifre"));
    		tbl_yetki.setCellValueFactory(new PropertyValueFactory<>("yetki"));
    		tbl_tablo.setItems(kayitlarliste);
    		
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage().toString());
		}
    	
    	
    
    }
    
    @FXML		
    void initialize() {
    	//şimdilik yenileye ihtiyaç yok!
    	btn_yenile.setDisable(true);
    	btn_yenile.setVisible(false);
    	//Yetki Integer
    	cmb_yetki.getItems().add(0,0);
    	cmb_yetki.getItems().add(1,1);
    	//Başlangıçta Değerleri Tabloya Getir!
    	Degergetir(tbl_tablo);
        
        
    }

}
