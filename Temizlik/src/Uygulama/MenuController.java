package Uygulama;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorpane1;

    @FXML
    private Button btn_cikis;

    @FXML
    private Button btn_envanter;

    @FXML
    private Button btn_gecmis;

    @FXML
    private Button btn_hizmetler;

    @FXML
    private Button btn_kullanicilar;

    @FXML
    private Button btn_satis;

    @FXML
    private Label lbl_main;

    @FXML
    void btn_cikis_click(ActionEvent event) {
    	Platform.exit();
    }

    @FXML
    void btn_envanter_click(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("Envanter.fxml"));
		Stage stage1 = new Stage();						
		stage1.setTitle("Envanter");
		stage1.getIcons().add(new Image("file:resources/images/logo.png"));
		Scene scene = new Scene(root,400,400);									
		stage1.setScene(scene);
		stage1.show();
		stage1 = (Stage)btn_envanter.getScene().getWindow();
		stage1.close();
    }

    @FXML
    void btn_gecmis_click(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("Gecmis.fxml"));
		Stage stage1 = new Stage();						
		stage1.setTitle("Gecmis");
		stage1.getIcons().add(new Image("file:resources/images/logo.png"));
		Scene scene = new Scene(root,400,400);									
		stage1.setScene(scene);
		stage1.show();
		stage1 = (Stage)btn_gecmis.getScene().getWindow();
		stage1.close();
    }

    @FXML
    void btn_hizmetler_click(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("Hizmetler.fxml"));
		Stage stage1 = new Stage();						
		stage1.setTitle("Hizmetler");
		stage1.getIcons().add(new Image("file:resources/images/logo.png"));
		Scene scene = new Scene(root,400,400);									
		stage1.setScene(scene);
		stage1.show();
		stage1 = (Stage)btn_hizmetler.getScene().getWindow();
		stage1.close();
    }
    
    @FXML
    void btn_kullanicilar_click(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("Kullanicilar.fxml"));
		Stage stage1 = new Stage();						
		stage1.setTitle("Kullanıcılar");
		stage1.getIcons().add(new Image("file:resources/images/logo.png"));
		Scene scene = new Scene(root,400,400);									
		stage1.setScene(scene);
		stage1.show();
		stage1 = (Stage)btn_kullanicilar.getScene().getWindow();
		stage1.close();
    }

    @FXML
    void btn_satis_click(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("HizmetSatis.fxml"));
		Stage stage1 = new Stage();						
		stage1.setTitle("Hizmet Satış");
		stage1.getIcons().add(new Image("file:resources/images/logo.png"));
		Scene scene = new Scene(root,400,400);									
		stage1.setScene(scene);
		stage1.show();
		stage1 = (Stage)btn_satis.getScene().getWindow();
		stage1.close();
    }

    @FXML
    void initialize() {
       
    	
    	if (LoginController.mevcutyetki==1) {
    	  btn_gecmis.setVisible(true);
    	  btn_kullanicilar.setVisible(true);
    	}
    	else {
    	  btn_gecmis.setVisible(false);
      	  btn_kullanicilar.setVisible(false);
    	}

    }

}


    	
		
    
