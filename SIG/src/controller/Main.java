package controller;


import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application{
	
	ControllerWindows myWindows=ControllerWindows.getInstancia();
	BorderPane container=null;
	public static String screenSupports="Mantenimientos";
	public static String fileSupports="../views/fxml/Mantenimientos.fxml";
	public static String screenLogin="Login";
	public static String fileLogin="../views/fxml/login.fxml";
	public static String screenWelcome="Bienvenido";
	public static String fileWelcome="../views/fxml/welcome.fxml";
	public static String screenClientes="Bienvenido";
	public static String fileClientes="../views/fxml/Clientes.fxml";
	public static String screenEmpleados="Bienvenido";
	public static String fileEmpleados="../views/fxml/Empleados.fxml";
	Group root= new Group();	
	@Override
	public void start(Stage primaryStage) throws Exception{
		myWindows.loadScreen(Main.screenSupports, Main.fileSupports);
		myWindows.loadScreen(Main.screenLogin, Main.fileLogin);
		myWindows.loadScreen(Main.screenWelcome, Main.fileWelcome);
		java.net.URL location = getClass().getResource("../views/fxml/menu.fxml");
		FXMLLoader fxml = new FXMLLoader(location);
		container = (BorderPane) fxml.load();
		//fxml.getController();
		Scene scene= new Scene(root);
		container.setCenter(myWindows);
		root.getChildren().add(container);
		scene.getStylesheets().add(getClass().getResource("../views/css/main.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.centerOnScreen();
		primaryStage.show();		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
