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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class EnvanterController {

	public EnvanterController() {
		baglanti=VeritabaniUtil.Baglan();
	}
	
	Connection baglanti=null;
	PreparedStatement sorguifadesi=null;
	ResultSet getirilen = null;
	String sql;
	int mevcutadet = 0;
	int toplamtutar=0;
	
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
    private ComboBox<Integer> cmb_adet;

    @FXML
    private ComboBox<String> cmb_urun;

    @FXML
    private TableColumn<Kayitlar_envanter, Integer> col_ID;

    @FXML
    private TableColumn<Kayitlar_envanter, Integer> col_adet;

    @FXML
    private TableColumn<Kayitlar_envanter, Integer> col_tutar;

    @FXML
    private TableColumn<Kayitlar_envanter, String> col_urun;

    @FXML
    private Label lbl_top1;

    @FXML
    private Label lbl_top2;

    @FXML
    private TableView<Kayitlar_envanter> tbl_urun;

    @FXML
    void btn_ekle_click(ActionEvent event) {
    		
    		degertut();
    		sql="UPDATE urunler SET urun_adet=? where urun_ad=?";
    		try {

    			if (cmb_adet.getSelectionModel().getSelectedIndex()!=-1 && cmb_urun.getSelectionModel().getSelectedIndex()!=-1) {
    				
    				sorguifadesi = baglanti.prepareStatement(sql);   				
    				sorguifadesi.setInt(1,cmb_adet.getSelectionModel().getSelectedItem()+mevcutadet); 
    				sorguifadesi.setString(2,cmb_urun.getSelectionModel().getSelectedItem());
    				sorguifadesi.executeUpdate();
    				mevcutadet=0;
    				cmb_adet.getSelectionModel().clearSelection();
    				cmb_urun.getSelectionModel().clearSelection();
    				degergetir(tbl_urun);
				}
    			else {
    				System.out.println("Hata");
    			}
    			
    			
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage().toString());
			}
    		
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
    void btn_sil_click(ActionEvent event) {
    	degertut();
		sql="UPDATE urunler SET urun_adet=? where urun_ad=?";
		try {

			if (cmb_adet.getSelectionModel().getSelectedIndex()!=-1 && cmb_urun.getSelectionModel().getSelectedIndex()!=-1) {
				
				if (mevcutadet>=cmb_adet.getSelectionModel().getSelectedItem()) {
					sorguifadesi = baglanti.prepareStatement(sql);   				
					sorguifadesi.setInt(1,mevcutadet-cmb_adet.getSelectionModel().getSelectedItem()); 
					sorguifadesi.setString(2,cmb_urun.getSelectionModel().getSelectedItem());
					sorguifadesi.executeUpdate();
					mevcutadet=0;
					cmb_adet.getSelectionModel().clearSelection();
					cmb_urun.getSelectionModel().clearSelection();
					degergetir(tbl_urun);
				}
				else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setHeaderText("Hata");
					alert.setTitle("Hata");
					alert.setContentText("Stoktaki ürünler eksiye inemez!");
					alert.show();
				}
			}
			else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Hata");
				alert.setTitle("Hata");
				alert.setContentText("Hatalı İşlem");
				alert.show();
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage().toString());
		}
    }
    
    public void degertut() {
    	
		sql="Select urun_adet from urunler where urun_ad=?";
		try {
			
			sorguifadesi=baglanti.prepareStatement(sql);
			sorguifadesi.setString(1, cmb_urun.getSelectionModel().getSelectedItem());;
			ResultSet getirilen = sorguifadesi.executeQuery();
			while(getirilen.next()) {
				
				mevcutadet= getirilen.getInt("urun_adet");
				
			}
			
				
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage().toString());
		}
    }
    
    
    public void degergetir(TableView tablo) {
    	
    	//Tabloyu Doldur!
    	sql = "select * from urunler";
    	ObservableList<Kayitlar_envanter> envanterliste = FXCollections.observableArrayList();
    	//Urunler için liste
    	ObservableList<String> urunler = FXCollections.observableArrayList();
    	//Adet için liste
    	ObservableList<Integer> adetler = FXCollections.observableArrayList();
    	//toplamtutar için lazım olacak!
    	ObservableList<Integer> adetler2 = FXCollections.observableArrayList();
    	//Tutarlar için liste
    	ObservableList<Integer> tutarlar = FXCollections.observableArrayList();
    	//Comboboxu Doldur
    	for (int i = 1; i < 26; i++) {
			adetler.add(i);
		}
    	
    	try {
    		sorguifadesi = baglanti.prepareStatement(sql);
    		ResultSet getirilen = sorguifadesi.executeQuery();
    		while(getirilen.next()) {
    			envanterliste.add(new Kayitlar_envanter(getirilen.getInt("uID"),getirilen.getString("urun_ad"), getirilen.getInt("urun_adet"), getirilen.getInt("urun_tutar")));
    			urunler.add(getirilen.getString("urun_ad"));
    			adetler2.add(getirilen.getInt("urun_adet"));
    			tutarlar.add(getirilen.getInt("urun_tutar"));
    			
    		}
    		cmb_urun.setItems(urunler);
    		cmb_adet.setItems(adetler);
    		col_ID.setCellValueFactory(new PropertyValueFactory<>("id"));
    		col_urun.setCellValueFactory(new PropertyValueFactory<>("urunadi"));
    		col_adet.setCellValueFactory(new PropertyValueFactory<>("adet"));
    		col_tutar.setCellValueFactory(new PropertyValueFactory<>("tutar"));
    		tbl_urun.setItems(envanterliste);
    		for (int i = 0; i < adetler2.size(); i++) {
				
    			toplamtutar += adetler2.get(i)* tutarlar.get(i);
    			
			}
    		lbl_top2.setText(Integer.toString(toplamtutar));
    		toplamtutar=0;
    		
    		
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage().toString());
		}
    }

    @FXML
    void initialize() {
    	
    
       degergetir(tbl_urun);
       
    }

}
