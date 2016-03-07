package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class MenuController implements Initializable,IControllerWindows{
	ControllerWindows myWindows=ControllerWindows.getInstancia();
	private static MenuController menucontroller;
	@FXML private Button btnLogin,btnMantenimientos,btnClientes;
	@FXML private TextField txtuser;
	@FXML private PasswordField txtpass;
	@FXML private Label lblMensaje;
	public MenuController() {
		// TODO Auto-generated constructor stub
		super();
		
	}
	
	public static MenuController getInstancia(){
		if(menucontroller==null){
			menucontroller= new MenuController();
		}
		return menucontroller;
	}
	
	@Override
	public void getMainlWindows(ControllerWindows InternalPage) {
		myWindows=InternalPage;
	}

	//Inicializamos la ventana para mostrar el login
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		myWindows.showScreen(Main.screenLogin);
	}
	
	//Métodos para asignar menú segun el nivel
	
		//Método para asignar el menu de administrador
		@FXML private void admin(){
			btnClientes.setVisible(false);
		}
	
	//Métodos para activar las ventanas
	
		@FXML private void Welcome(){
			myWindows.showScreen(Main.screenWelcome);
		}	
		
	@FXML private void supports(){
		myWindows.showScreen(Main.screenSupports);
	}
	
	@FXML private void clientes(){
		System.out.println(Main.screenClientes);
		myWindows.showScreen(Main.screenClientes);
	}

	@FXML private void empleados(){
		System.out.println(Main.screenEmpleados);
		myWindows.showScreen(Main.screenEmpleados);
	}
	
	@FXML private void productos(){
		System.out.println(Main.screenProductos);
		myWindows.showScreen(Main.screenProductos);
	}
	
	@FXML private void salir(){
		System.exit(0);
	}
	
	@FXML public void activeMenu(){

		Welcome();
		/*if(txtuser.getText().trim().isEmpty() ||	txtpass.getText().trim().isEmpty())
		{
			lblMensaje.setText("Los campos son obligatorios");
		}
		else{
			Welcome();
			//Usuario user= new Usuario();
		}*/
		
	}
}
