package controller;

import java.net.URL;
import java.util.ResourceBundle;


import model.Empleados;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableView;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
public class ControllerEmpleados implements Initializable{
	@FXML private TextArea txtDireccion;
	@FXML private TextField txtID, txtNombre, txtPaterno, txtMaterno, txtTelefono, txtEstado, txtCiudad, txtPais,txtCorreo;
	@FXML private Button btnModificar, btnEliminar,btnNuevo, btnGuardar;
	@FXML private Label lblMensaje;
	@FXML private CheckBox ckbInactivos;
	@FXML private TableView<Empleados> tvEmpleados;

	//Instancia 
	//Instancia 
	private Empleados daoEm = new Empleados();
	private ObservableList<model.Empleados> listaEmpleados;
	private Empleados empleado;
	
	public ControllerEmpleados(){
		listaEmpleados=FXCollections.observableArrayList();
		empleado= new Empleados();
	}
	
	public void llenarTabla(Boolean estatus){
		listaEmpleados.clear();
		
		if(estatus==true){
			listaEmpleados = empleado.consultarEmpleados("select * from empleados where estatus='1'");
			
		}else{
			

			listaEmpleados = empleado.consultarEmpleados("select * from empleados where estatus='0'");
			
		}
		
		tvEmpleados.setItems(listaEmpleados);
			}
		

	
	@FXML public void clickNuevo(){
		
		btnModificar.setDisable(true);
		btnEliminar.setDisable(true);
		btnNuevo.setDisable(true);
btnGuardar.setDisable(false);
		txtPaterno.setDisable(false);
		txtMaterno.setDisable(false);
		txtPais.setDisable(false);
		
		txtNombre.setDisable(false);
		txtTelefono.setDisable(false);
		txtCiudad.setDisable(false);
		txtEstado.setDisable(false);
		txtDireccion.setDisable(false);
		txtCorreo.setDisable(false);
		txtNombre.clear();
		txtPaterno.clear();
		txtMaterno.clear();
		txtDireccion.clear();
		txtTelefono.clear();
		txtCorreo.clear();
		txtCiudad.clear();
		txtEstado.clear();
		
		
	}
	@FXML public void clickInsertar(){
		if(txtNombre.getText().trim().isEmpty() || txtPaterno.getText().trim().isEmpty() || txtMaterno.getText().trim().isEmpty()  ||  txtDireccion.getText().trim().isEmpty() ||txtCiudad.getText().trim().isEmpty() || txtEstado.getText().trim().isEmpty() || txtPais.getText().trim().isEmpty() || txtTelefono.getText().trim().isEmpty() || txtCorreo.getText().trim().isEmpty() )
		{
			lblMensaje.setText("Todos los campos son Obligatorios");
			
		}
		else{
			Boolean result = daoEm.insertar(new Empleados(0, txtNombre.getText(),txtPaterno.getText() , txtMaterno.getText(), txtDireccion.getText(), txtCiudad.getText(), txtEstado.getText(),txtPais.getText(), txtTelefono.getText(), txtCorreo.getText(),true));
			if(result) {
				lblMensaje.setText("Los datos se han insertado satisfactoriamente");
				llenarTabla(true);
				btnGuardar.setDisable(true);
				btnNuevo.setDisable(false);
				btnModificar.setDisable(true);
				btnEliminar.setDisable(true);
				txtNombre.setDisable(true);
				txtNombre.clear();
				txtPaterno.setDisable(true);
				txtPaterno.clear();
				
				
				txtPais.clear();
				txtNombre.clear();
				txtPaterno.clear();
				txtMaterno.clear();
				
				txtDireccion.clear();
				txtTelefono.clear();
				txtCorreo.clear();
				
				txtCiudad.clear();
				txtEstado.clear();
				txtID.clear();
				
				txtPaterno.setDisable(true);
				txtMaterno.setDisable(true);
				txtPais.setDisable(true);
				
				
				txtNombre.setDisable(true);
				txtTelefono.setDisable(true);
				txtCiudad.setDisable(true);
				txtEstado.setDisable(true);
				txtDireccion.setDisable(true);
				
				txtCorreo.setDisable(true);
			}
			else{
				lblMensaje.setText("Ha ocurrido un error consulte a su administrador");
			}
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		llenarTabla(true);
		
	}
	@FXML public void clickTableView(){
		//Evaluar si se seleccciono una categoria
		
		if(tvEmpleados.getSelectionModel().getSelectedItem()!=null){
			empleado = tvEmpleados.getSelectionModel().getSelectedItem();
			txtNombre.setText(empleado.getNombre());
			txtPaterno.setText(empleado.getApellidop());
			txtMaterno.setText(empleado.getApellidom());
			txtDireccion.setText(empleado.getDireccion());
			txtCiudad.setText(empleado.getCiudad());
			txtEstado.setText(empleado.getEstado());
			txtPais.setText(empleado.getPais());
			txtTelefono.setText(empleado.getTelefono());
			txtCorreo.setText(empleado.getCorreo());
			txtID.setText(String.valueOf(empleado.getEmpleadoid()));
			//Habilitar las cajas de texto
			txtPaterno.setDisable(false);
			txtMaterno.setDisable(false);
			txtPais.setDisable(false);
			txtNombre.setDisable(false);
			txtTelefono.setDisable(false);
			txtCiudad.setDisable(false);
			txtEstado.setDisable(false);
			txtDireccion.setDisable(false);
			txtCorreo.setDisable(false);
			//Habilitar los botones para modificar y eliminar
			btnEliminar.setDisable(false);
			btnModificar.setDisable(false);
			//Deshabilitar el de nuevo
			btnNuevo.setDisable(true);
			btnGuardar.setDisable(true);
		}
	}
	
	@FXML public void clickEliminar(){
		if(txtID.getText().isEmpty()){
			lblMensaje.setText("No se ha seleccionado un empleado");
			}
		else{
			int empleadoid = Integer.parseInt(txtID.getText());
			Boolean result =daoEm.eliminar(empleadoid);
			if(result==true){
				lblMensaje.setText("Se ha eliminado el empleado");
				txtNombre.setDisable(true);
				txtNombre.clear();
				txtPaterno.setDisable(true);
				txtPaterno.clear();
				
				
				txtPais.clear();
				txtNombre.clear();
				txtPaterno.clear();
				txtMaterno.clear();
				
				txtDireccion.clear();
				txtTelefono.clear();
				txtCorreo.clear();
				
				txtCiudad.clear();
				txtEstado.clear();
				txtID.clear();
				
				txtPaterno.setDisable(true);
				txtMaterno.setDisable(true);
				txtPais.setDisable(true);
				
				
				txtNombre.setDisable(true);
				txtTelefono.setDisable(true);
				txtCiudad.setDisable(true);
				txtEstado.setDisable(true);
				txtDireccion.setDisable(true);
				
				txtCorreo.setDisable(true);
				llenarTabla(true);
				btnNuevo.setDisable(false);
				btnEliminar.setDisable(true);
				btnModificar.setDisable(true);
				btnGuardar.setDisable(true);
			}
			else{
				lblMensaje.setText("Ha ocurrido un error");
			}
			
		}
	}
	
	@FXML public void clickModificar(){
		if(txtID.getText().isEmpty()){
			lblMensaje.setText("Debe Seleccionar un Empleado");
			
		}
		else{
			empleado= new Empleados();
			empleado.setEmpleadoid(Integer.parseInt(txtID.getText()));
			
			empleado.setNombre(txtNombre.getText());
			empleado.setApellidop(txtPaterno.getText());
			empleado.setApellidom(txtMaterno.getText());
			
			empleado.setDireccion(txtDireccion.getText());
			empleado.setCiudad(txtCiudad.getText());
			empleado.setEstado(txtEstado.getText());
			empleado.setPais(txtPais.getText());
			empleado.setTelefono(txtTelefono.getText());
			empleado.setCorreo(txtCorreo.getText());
			Boolean result=daoEm.modificar(empleado);
			if(result==true){
				lblMensaje.setText("Se han modificado los datos");
				llenarTabla(true);
				txtNombre.setDisable(true);
				txtNombre.clear();
				txtPaterno.setDisable(true);
				txtPaterno.clear();
				
		txtPais.clear();
				txtNombre.clear();
				txtPaterno.clear();
				txtMaterno.clear();
				
				txtDireccion.clear();
				txtTelefono.clear();
				txtCorreo.clear();
				
				txtCiudad.clear();
				txtEstado.clear();
				txtID.clear();
				
				txtPaterno.setDisable(true);
				txtMaterno.setDisable(true);
				txtPais.setDisable(true);
				
				txtNombre.setDisable(true);
				txtTelefono.setDisable(true);
				txtCiudad.setDisable(true);
				txtEstado.setDisable(true);
				txtDireccion.setDisable(true);
				
				txtCorreo.setDisable(true);
				btnNuevo.setDisable(false);
				btnEliminar.setDisable(true);
				btnModificar.setDisable(true);
				btnGuardar.setDisable(true);
				txtNombre.setDisable(true);
			}
			else{
				lblMensaje.setText("Ha ocurrido un error");
			}
		}
	}
	
@FXML public void mostrarInactivos(){
		if(ckbInactivos.isSelected()){
			//Mostrar Inactivos
			llenarTabla(false);
			txtNombre.setDisable(true);
			txtNombre.clear();
			txtPaterno.setDisable(true);
			txtPaterno.clear();
			
			
			txtPais.clear();
			txtNombre.clear();
			txtPaterno.clear();
			txtMaterno.clear();
			
			txtDireccion.clear();
			txtTelefono.clear();
			txtCorreo.clear();
			
			txtCiudad.clear();
			txtEstado.clear();
			txtID.clear();
			
			txtPaterno.setDisable(true);
			txtMaterno.setDisable(true);
			txtPais.setDisable(true);
			
			
			txtNombre.setDisable(true);
			txtTelefono.setDisable(true);
			txtCiudad.setDisable(true);
			txtEstado.setDisable(true);
			txtDireccion.setDisable(true);
			
			txtCorreo.setDisable(true);
			
			btnNuevo.setDisable(false);
			btnEliminar.setDisable(true);
			btnModificar.setDisable(true);
			btnGuardar.setDisable(true);
		}
		else{
			//Mostrar activos
			llenarTabla(true);
			btnNuevo.setDisable(false);
		}
	}
}
