package Uygulama;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.temizlikMySQL.util.VeritabaniUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class HizmetSatisController {
	public HizmetSatisController() {
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
    private Button btn_ekle;

    @FXML
    private Button btn_geri;

    @FXML
    private ComboBox<String> cmb_tur;

    @FXML
    private Label lbl_ad;

    @FXML
    private Label lbl_adres;

    @FXML
    private Label lbl_tel;

    @FXML
    private Label lbl_tur;

    @FXML
    private TextField txt_ad;

    @FXML
    private TextArea txt_adres;

    @FXML
    private TextField txt_tel;

    @FXML
    void btn_ekle_click(ActionEvent event) {
    	
    	sql ="insert into satis(hiz_ad, hiz_tur, hiz_tel, hiz_adres) values(?,?,?,?)" ;
    	
    	try {
			
    		if (txt_ad!=null && txt_tel!=null && txt_adres != null && cmb_tur.getSelectionModel().getSelectedItem()!=null) {
				sorguifadesi = baglanti.prepareStatement(sql);
				sorguifadesi.setString(1, txt_ad.getText());
				sorguifadesi.setString(2, cmb_tur.getSelectionModel().getSelectedItem());
				sorguifadesi.setString(3,txt_tel.getText());
				sorguifadesi.setString(4, txt_adres.getText());
				sorguifadesi.executeUpdate();
				System.out.println("Başarılı");
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Başarılı");
				alert.setHeaderText("Ekleme Başarılı");
				alert.setContentText("Ekleme Başarılı");
				alert.showAndWait();
				txt_ad.clear();
				txt_adres.clear();
				txt_tel.clear();
				cmb_tur.getSelectionModel().clearSelection();
				
			}
    		else {
    			Alert alert=new Alert(AlertType.WARNING);
				alert.setTitle("Hata");
				alert.setHeaderText("Hata");
				alert.setContentText("Satırlar veya Tür Boş Geçilemez!");
				alert.showAndWait();
    		}
    		
    		
		} catch (Exception e) {
			Alert alert=new Alert(AlertType.WARNING);
			alert.setTitle("Hata");
			alert.setHeaderText("Hata");
			alert.setContentText("Satırlar veya Tür Boş Geçilemez!");
			alert.showAndWait();
			System.out.println(e.getMessage().toString());
			
		}
    	
    }
    void turekle() {
    	cmb_tur.getItems().add("Ev Temizliği");
    	cmb_tur.getItems().add("Ofis Temizliği");
    	cmb_tur.getItems().add("Koltuk Temizliği");
    	cmb_tur.getItems().add("Halı Temizliği");
    }
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
    void initialize() {
        
    	turekle();
    }

}

    


