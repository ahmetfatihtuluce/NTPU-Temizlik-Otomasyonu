package Uygulama;

import java.net.URL;
import java.util.ResourceBundle;
import com.temizlikMySQL.util.VeritabaniUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.sql.*;
import javafx.stage.*;


public class LoginController {
	
	//Constructor
	public LoginController() {
		baglanti=VeritabaniUtil.Baglan();
		
	}	
	
	Connection baglanti=null;
	PreparedStatement sorguifadesi=null;
	ResultSet getirilen = null;
	String sql;
	Stage stage1=null;
	public static int mevcutyetki;
	public static String mevcutkullanici;
	
	//Login sonrası pencereyi kapat
	public void afterlogin()  {
		
		stage1 = (Stage)btn_login.getScene().getWindow();
		stage1.close();
	}
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_login;

    @FXML
    private TextField txt_kul;

    @FXML
    private TextField txt_sifre;

    @FXML
    void btn_login_click(ActionEvent event) {
    	sql="select * from login where kul_ad=? and sifre=?";
    	try {
			sorguifadesi=baglanti.prepareStatement(sql);
			sorguifadesi.setString(1, txt_kul.getText().trim());
			sorguifadesi.setString(2, VeritabaniUtil.MD5Sifrele(txt_sifre.getText().trim()));
			ResultSet getirilen= sorguifadesi.executeQuery();
			
			if (!getirilen.next()) {
				Alert alert=new Alert(AlertType.WARNING);
				alert.setTitle("Hata");
				alert.setHeaderText("Hata");
				alert.setContentText("Kullanıcı Adı veya Şifre Hatalı");
				alert.showAndWait();
			}
			else {
				getirilen.getString(1); //Tabloda 1.sütundaki değeri getirir
				System.out.println("ID:"+getirilen.getString("kID"));
				System.out.println("Kullanıcı:"+getirilen.getString("kul_ad"));
				System.out.println("Sifre:"+getirilen.getString("sifre"));
				System.out.println("Yetki:"+getirilen.getString("yetki"));
				mevcutyetki = Integer.parseInt(getirilen.getString("yetki"));
				mevcutkullanici = getirilen.getString("kul_ad");
				System.out.println("mevcutyetki="+mevcutyetki);
				islemekle();
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Başarılı");
				alert.setHeaderText("Başarılı Giriş");
				alert.setContentText("Başarılı Giriş");
				
				try {
					Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
					Stage stage1 = new Stage();						
					stage1.setTitle("Menu");
					stage1.getIcons().add(new Image("file:resources/images/logo.png"));
					Scene scene = new Scene(root,400,400);									
					stage1.setScene(scene);
					stage1.show();
					afterlogin();
					
					
					
					
				} catch(Exception e) {
					e.printStackTrace();
				}
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage().toString());
		}
    }
    public void islemekle() {
    	sql="insert into gecmis(kul_ad) values(?)";
    	
    	try {
    		
    		sorguifadesi=baglanti.prepareStatement(sql);
			sorguifadesi.setString(1, mevcutkullanici);
			sorguifadesi.executeUpdate();
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
		}
    }
    @FXML
    void initialize() {
       

    }

}
