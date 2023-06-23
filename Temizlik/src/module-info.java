module Temizlik {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires javafx.base;
	requires java.desktop;
	requires javafx.graphics;
	
	
	opens Uygulama to javafx.graphics, javafx.fxml, javafx.base, java.desktop;
	
}
