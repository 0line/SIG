package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import model.MClienteMoral;

public class SupportController implements Initializable{
	@FXML private ComboBox<model.MClienteMoral>cbmorales;
	
	private ObservableList<model.MClienteMoral> listcmorales;
	private MClienteMoral mcm;
	
	//Método constructor
	public SupportController() {
		listcmorales=FXCollections.observableArrayList();
		mcm=new MClienteMoral();
	}
	
	
	//Método para llenar el combo box con los clientes morales de la base de datos 
	public void llenarCMMorales(){
		listcmorales.clear();
		String sql="select moralesid,razonsocial from clientesmorales where estatus='1'";
		listcmorales=mcm.colsultarCMorales(sql);
		cbmorales.setItems(listcmorales);
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//Inicializando controles para la vista
		llenarCMMorales();
		
	}
}
