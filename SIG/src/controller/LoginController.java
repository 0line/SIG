package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class LoginController implements Initializable, IControllerWindows{	
	//Instancias
	ControllerWindows myWindows=ControllerWindows.getInstancia();
	MenuController mc=MenuController.getInstancia();
	
	//Variable
	String nivel="admin";
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
			
	}

	@Override
	public void getMainlWindows(ControllerWindows InternalPage) {
		myWindows=InternalPage;
	}
	
	
	//Ventana de bienvenido si el usuario se loguea correctamente
	/*@FXML private Boolean Welcome(){ //Aqui debo pasar el nivel que recupero
		try {
			myWindows.showScreen(Main.screenWelcome);
			nivel="admin";
			System.out.println(nivel);
			mc.activeMenu(nivel);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("error");
			e.printStackTrace();
			return false;
		}
		
	}*/
	
	
	
}

