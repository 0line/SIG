package controller;

import java.net.URL;
import java.util.ResourceBundle;

import model.ClientesFisicos;
import model.ClientesMorales;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableView;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
public class ControllerClientesF implements Initializable{
	@FXML private TextField txtIDF,txtDireccionF, txtNombreF, txtPaternoF, txtMaternoF, txtTelefonoF, txtEstadoF, txtCiudadF, txtPaisF,txtCorreoF, txtRFCF;
	@FXML private Button btnCancelarF,btnModificar, btnEliminar,btnNuevo, btnGuardar;
	@FXML private Label lblMensaje;
	@FXML private CheckBox ckbInactivos;
	@FXML private TableView<ClientesFisicos> tvClientes;
	@FXML private TextField txtIDM,txtDireccionM, txtNombreM, txtRazonM,txtTelefonoM, txtEstadoM, txtCiudadM, txtPaisM,txtCorreoM, txtRFCM;
	@FXML private Button btnCancelarM,btnModificarM, btnEliminarM,btnNuevoM, btnGuardarM;
	@FXML private Label lblMensajeM;
	@FXML private CheckBox ckbInactivosM;
	@FXML private TableView<ClientesMorales> tvClientesM;
	//Instancia 
	private ClientesMorales daoMor = new ClientesMorales();
	private ObservableList<ClientesMorales> listaClientesM;
	private ClientesMorales clienteM;
	//Instancia 
	private ClientesFisicos daoCAT = new ClientesFisicos();
	private ObservableList<model.ClientesFisicos> listaClientes;
	private ClientesFisicos cliente;
	
	public ControllerClientesF(){
		listaClientes=FXCollections.observableArrayList();
		cliente= new ClientesFisicos();
		listaClientesM= FXCollections.observableArrayList();
		clienteM= new ClientesMorales();
	}
	
	public void llenarTabla(Boolean estatus){
		listaClientes.clear();
		listaClientesM.clear();
		if(estatus==true){
			listaClientes = cliente.consultarClientes("select * from clientesfisicos where estatus='1'");
			listaClientesM = clienteM.consultarClientesM("select * from clientesmorales where estatus='1'");
		}else{
			

			listaClientes = cliente.consultarClientes("select * from clientesfisicos where estatus='0'");
			listaClientesM = clienteM.consultarClientesM("select * from clientesmorales where estatus='0'");
		}
		//System.out.println(listaClientes);
		tvClientes.setItems(listaClientes);
		tvClientesM.setItems(listaClientesM);
		/*listaClientesM.clear();
		if(estatus==true){
			listaClientesM = clienteM.consultarClientesM("select * from clientesmorales where estatus='1'");
		}else{
			

			listaClientesM = clienteM.consultarClientesM("select * from clientesmorales where estatus='0'");
		}
		tvClientesM.setItems(listaClientesM);
		*/
	}
		

	
	@FXML public void clickNuevo(){
		
		btnModificar.setDisable(true);
		btnEliminar.setDisable(true);
		btnCancelarF.setDisable(false);
		btnNuevo.setDisable(true);
btnGuardar.setDisable(false);
		txtPaternoF.setDisable(false);
		txtMaternoF.setDisable(false);
		txtPaisF.setDisable(false);
		txtRFCF.setDisable(false);
		txtNombreF.setDisable(false);
		txtTelefonoF.setDisable(false);
		txtCiudadF.setDisable(false);
		txtEstadoF.setDisable(false);
		txtDireccionF.setDisable(false);
		txtCorreoF.setDisable(false);
		txtNombreF.clear();
		txtPaternoF.clear();
		txtMaternoF.clear();
		txtDireccionF.clear();
		txtTelefonoF.clear();
		txtCorreoF.clear();
		txtCiudadF.clear();
		txtEstadoF.clear();
		txtIDF.clear();
		
	}
@FXML public void clickNuevoM(){
		
		btnModificarM.setDisable(true);
		btnEliminarM.setDisable(true);
		btnCancelarM.setDisable(false);
		btnNuevoM.setDisable(true);
btnGuardarM.setDisable(false);
		txtRazonM.setDisable(false);
		txtPaisM.setDisable(false);
		txtRFCM.setDisable(false);
		txtNombreM.setDisable(false);
		txtTelefonoM.setDisable(false);
		txtCiudadM.setDisable(false);
		txtEstadoM.setDisable(false);
		txtDireccionM.setDisable(false);
		txtCorreoM.setDisable(false);
		txtNombreM.clear();
		txtRazonM.clear();
		txtDireccionM.clear();
		txtTelefonoM.clear();
		txtCorreoM.clear();
		txtCiudadM.clear();
		txtEstadoM.clear();
		txtIDM.clear();
		
	}
	

@FXML public void clickCancelarM(){
		
		btnModificarM.setDisable(true);
		btnEliminarM.setDisable(true);
		btnNuevoM.setDisable(false);
btnGuardarM.setDisable(true);
		txtRazonM.setDisable(false);
		txtPaisM.setDisable(false);
		txtRFCM.setDisable(false);
		txtNombreM.setDisable(false);
		txtTelefonoM.setDisable(false);
		txtCiudadM.setDisable(false);
		txtEstadoM.setDisable(false);
		txtDireccionM.setDisable(false);
		txtCorreoM.setDisable(false);
		txtNombreM.clear();
		txtRazonM.clear();
		txtDireccionM.clear();
		txtTelefonoM.clear();
		txtCorreoM.clear();
		txtCiudadM.clear();
		txtEstadoM.clear();
		txtIDM.clear();
		
	}

@FXML public void clickCancelarF(){
	
	btnModificar.setDisable(true);
	btnEliminar.setDisable(true);
	btnCancelarF.setDisable(true);
	btnNuevo.setDisable(false);
btnGuardar.setDisable(true);
	txtPaternoF.setDisable(false);
	txtMaternoF.setDisable(false);
	txtPaisF.setDisable(false);
	txtRFCF.setDisable(false);
	txtNombreF.setDisable(false);
	txtTelefonoF.setDisable(false);
	txtCiudadF.setDisable(false);
	txtEstadoF.setDisable(false);
	txtDireccionF.setDisable(false);
	txtCorreoF.setDisable(false);
	txtNombreF.clear();
	txtPaternoF.clear();
	txtMaternoF.clear();
	txtDireccionF.clear();
	txtTelefonoF.clear();
	txtCorreoF.clear();
	txtCiudadF.clear();
	txtEstadoF.clear();
	txtIDF.clear();
	
}


	@FXML public void clickInsertar(){
		if(txtNombreF.getText().trim().isEmpty() || txtPaternoF.getText().trim().isEmpty() || txtMaternoF.getText().trim().isEmpty()  || txtRFCF.getText().trim().isEmpty() || txtDireccionF.getText().trim().isEmpty() ||txtCiudadF.getText().trim().isEmpty() || txtEstadoF.getText().trim().isEmpty() || txtPaisF.getText().trim().isEmpty() || txtTelefonoF.getText().trim().isEmpty() || txtCorreoF.getText().trim().isEmpty() )
		{
			lblMensaje.setText("Todos los campos son Obligatorios");
			
		}
		else{
			Boolean result = daoCAT.insertar(new ClientesFisicos(0, txtRFCF.getText(), txtNombreF.getText(),txtPaternoF.getText() , txtMaternoF.getText(), txtDireccionF.getText(), txtCiudadF.getText(), txtEstadoF.getText(),txtPaisF.getText(), txtTelefonoF.getText(), txtCorreoF.getText(),true));
			if(result) {
				lblMensaje.setText("Los datos se han insertado satisfactoriamente");
				llenarTabla(true);
				btnGuardar.setDisable(true);
				btnNuevo.setDisable(false);
				btnModificar.setDisable(true);
				btnCancelarF.setDisable(true);
				btnEliminar.setDisable(true);
				txtNombreF.setDisable(true);
				txtNombreF.clear();
				txtPaternoF.setDisable(true);
				txtPaternoF.clear();
				
				txtRFCF.clear();
				txtPaisF.clear();
				txtNombreF.clear();
				txtPaternoF.clear();
				txtMaternoF.clear();
				
				txtDireccionF.clear();
				txtTelefonoF.clear();
				txtCorreoF.clear();
				
				txtCiudadF.clear();
				txtEstadoF.clear();
				txtIDF.clear();
				
				txtPaternoF.setDisable(true);
				txtMaternoF.setDisable(true);
				txtPaisF.setDisable(true);
				txtRFCF.setDisable(true);
				
				txtNombreF.setDisable(true);
				txtTelefonoF.setDisable(true);
				txtCiudadF.setDisable(true);
				txtEstadoF.setDisable(true);
				txtDireccionF.setDisable(true);
				
				txtCorreoF.setDisable(true);
			}
			else{
				lblMensaje.setText("Ha ocurrido un error consulte a su administrador");
			}
		}
	}
	
	@FXML public void clickInsertarM(){
		if(txtNombreM.getText().trim().isEmpty() || txtRazonM.getText().trim().isEmpty() || txtRFCM.getText().trim().isEmpty() || txtDireccionM.getText().trim().isEmpty() ||txtCiudadM.getText().trim().isEmpty() || txtEstadoM.getText().trim().isEmpty() || txtPaisM.getText().trim().isEmpty() || txtTelefonoM.getText().trim().isEmpty() || txtCorreoM.getText().trim().isEmpty() )
		{
			lblMensajeM.setText("Todos los campos son Obligatorios");
			
		}
		else{
			Boolean result = daoMor.insertarM(new ClientesMorales(0, txtRazonM.getText(), txtNombreM.getText(),txtRFCM.getText() ,txtDireccionM.getText(), txtCiudadM.getText(), txtEstadoM.getText(),txtPaisM.getText(), txtTelefonoM.getText(), txtCorreoM.getText(),true));
			if(result) {
				lblMensajeM.setText("Los datos se han insertado satisfactoriamente");
				llenarTabla(true);
				btnGuardarM.setDisable(true);
				btnNuevoM.setDisable(false);
				btnCancelarM.setDisable(false);
				btnModificarM.setDisable(true);
				btnEliminarM.setDisable(true);
				txtNombreM.setDisable(true);
				txtNombreM.clear();
				txtRazonM.setDisable(true);
				
				
				txtRFCM.clear();
				txtPaisM.clear();
				txtNombreM.clear();
				
				txtRazonM.clear();
				
				txtDireccionM.clear();
				txtTelefonoM.clear();
				txtCorreoM.clear();
				
				txtCiudadM.clear();
				txtEstadoM.clear();
				txtIDM.clear();
				
				
				txtRazonM.setDisable(true);
				txtPaisM.setDisable(true);
				txtRFCM.setDisable(true);
				
				txtNombreM.setDisable(true);
				txtTelefonoM.setDisable(true);
				txtCiudadM.setDisable(true);
				txtEstadoM.setDisable(true);
				txtDireccionM.setDisable(true);
				
				txtCorreoM.setDisable(true);
			}
			else{
				lblMensajeM.setText("Ha ocurrido un error consulte a su administrador");
			}
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		llenarTabla(true);
		
	}
	@FXML public void clickTableView(){
		//Evaluar si se seleccciono una categoria
		
		if(tvClientes.getSelectionModel().getSelectedItem()!=null){
			cliente = tvClientes.getSelectionModel().getSelectedItem();
			txtRFCF.setText(cliente.getRfc());
			txtNombreF.setText(cliente.getNombre());
			txtPaternoF.setText(cliente.getApellidop());
			txtMaternoF.setText(cliente.getApellidom());
			txtDireccionF.setText(cliente.getDireccion());
			txtCiudadF.setText(cliente.getCiudad());
			txtEstadoF.setText(cliente.getEstado());
			txtPaisF.setText(cliente.getPais());
			txtTelefonoF.setText(cliente.getTelefono());
			txtCorreoF.setText(cliente.getCorreo());
			txtIDF.setText(String.valueOf(cliente.getFisicosid()));
			//Habilitar las cajas de texto
			txtPaternoF.setDisable(false);
			txtMaternoF.setDisable(false);
			txtPaisF.setDisable(false);
			txtRFCF.setDisable(false);
			txtNombreF.setDisable(false);
			txtTelefonoF.setDisable(false);
			txtCiudadF.setDisable(false);
			txtEstadoF.setDisable(false);
			txtDireccionF.setDisable(false);
			txtCorreoF.setDisable(false);
			//Habilitar los botones para modificar y eliminar
			btnEliminar.setDisable(false);
			btnCancelarF.setDisable(false);
			btnModificar.setDisable(false);
			//Deshabilitar el de nuevo
			btnNuevo.setDisable(true);
			btnGuardar.setDisable(true);
		}
	}
	
	@FXML public void clickTableViewM(){
		//Evaluar si se seleccciono una categoria
		
		if(tvClientesM.getSelectionModel().getSelectedItem()!=null){
			clienteM = tvClientesM.getSelectionModel().getSelectedItem();
			txtRazonM.setText(clienteM.getRazonsocial());
			txtNombreM.setText(clienteM.getNombre());
			txtRFCM.setText(clienteM.getRfc());
			txtDireccionM.setText(clienteM.getDireccion());
			txtCiudadM.setText(clienteM.getCiudad());
			txtEstadoM.setText(clienteM.getEstado());
			txtPaisM.setText(clienteM.getPais());
			txtTelefonoM.setText(clienteM.getTelefono());
			txtCorreoM.setText(clienteM.getCorreo());
			txtIDM.setText(String.valueOf(clienteM.getMoralesid()));
			//Habilitar las cajas de texto
			txtRazonM.setDisable(false);
			txtPaisM.setDisable(false);
			txtRFCM.setDisable(false);
			txtNombreM.setDisable(false);
			txtTelefonoM.setDisable(false);
			txtCiudadM.setDisable(false);
			txtEstadoM.setDisable(false);
			txtDireccionM.setDisable(false);
			txtCorreoM.setDisable(false);
			
			//Habilitar los botones para modificar y eliminar
			btnEliminarM.setDisable(false);
			btnModificarM.setDisable(false);
			btnCancelarM.setDisable(false);
			//Deshabilitar el de nuevo
			btnNuevoM.setDisable(true);
			btnGuardarM.setDisable(true);
		}
	}
	
	@FXML public void clickEliminar(){
		if(txtIDF.getText().isEmpty()){
			lblMensaje.setText("No se ha seleccionado un cliente");
			}
		else{
			int fisicosid = Integer.parseInt(txtIDF.getText());
			Boolean result =daoCAT.eliminar(fisicosid);
			if(result==true){
				lblMensaje.setText("Se ha eliminado el cliente");
				txtNombreF.setDisable(true);
				txtNombreF.clear();
				txtPaternoF.setDisable(true);
				txtPaternoF.clear();
				
				txtRFCF.clear();
				txtPaisF.clear();
				txtNombreF.clear();
				txtPaternoF.clear();
				txtMaternoF.clear();
				
				txtDireccionF.clear();
				txtTelefonoF.clear();
				txtCorreoF.clear();
				
				txtCiudadF.clear();
				txtEstadoF.clear();
				txtIDF.clear();
				
				txtPaternoF.setDisable(true);
				txtMaternoF.setDisable(true);
				txtPaisF.setDisable(true);
				txtRFCF.setDisable(true);
				
				txtNombreF.setDisable(true);
				txtTelefonoF.setDisable(true);
				txtCiudadF.setDisable(true);
				txtEstadoF.setDisable(true);
				txtDireccionF.setDisable(true);
				btnCancelarF.setDisable(true);
				txtCorreoF.setDisable(true);
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
	
	@FXML public void clickEliminarM(){
		if(txtIDM.getText().isEmpty()){
			lblMensajeM.setText("No se ha seleccionado un cliente");
			}
		else{
			int moralesid = Integer.parseInt(txtIDM.getText());
			Boolean result =daoMor.eliminarM(moralesid);
			if(result==true){
				lblMensajeM.setText("Se ha eliminado el cliente");
				btnGuardarM.setDisable(true);
				btnNuevoM.setDisable(false);
				btnModificarM.setDisable(true);
				btnEliminarM.setDisable(true);
				txtNombreM.setDisable(true);
				txtNombreM.clear();
				txtRazonM.setDisable(true);
				
				
				txtRFCM.clear();
				txtPaisM.clear();
				txtNombreM.clear();
				
				txtRazonM.clear();
				btnCancelarM.setDisable(true);
				txtDireccionM.clear();
				txtTelefonoM.clear();
				txtCorreoM.clear();
				
				txtCiudadM.clear();
				txtEstadoM.clear();
				txtIDM.clear();
				
				
				txtRazonM.setDisable(true);
				txtPaisM.setDisable(true);
				txtRFCM.setDisable(true);
				
				txtNombreM.setDisable(true);
				txtTelefonoM.setDisable(true);
				txtCiudadM.setDisable(true);
				txtEstadoM.setDisable(true);
				txtDireccionM.setDisable(true);
				llenarTabla(true);
				txtCorreoM.setDisable(true);
			}
			else{
				lblMensajeM.setText("Ha ocurrido un error");
			}
			
		}
	}
	
	
	@FXML public void clickModificar(){
		if(txtIDF.getText().isEmpty()){
			lblMensaje.setText("Debe Seleccionar un cliente");
			
		}
		else{
			cliente= new ClientesFisicos();
			cliente.setFisicosid(Integer.parseInt(txtIDF.getText()));
			cliente.setRfc(txtRFCF.getText());
			cliente.setNombre(txtNombreF.getText());
			cliente.setApellidop(txtPaternoF.getText());
			cliente.setApellidom(txtMaternoF.getText());
			
			cliente.setDireccion(txtDireccionF.getText());
			cliente.setCiudad(txtCiudadF.getText());
			cliente.setEstado(txtEstadoF.getText());
			cliente.setPais(txtPaisF.getText());
			cliente.setTelefono(txtTelefonoF.getText());
			cliente.setCorreo(txtCorreoF.getText());
			Boolean result=daoCAT.modificar(cliente);
			if(result==true){
				lblMensaje.setText("Se han modificado los datos");
				llenarTabla(true);
				txtNombreF.setDisable(true);
				txtNombreF.clear();
				txtPaternoF.setDisable(true);
				txtPaternoF.clear();
				
				txtRFCF.clear();
				txtPaisF.clear();
				txtNombreF.clear();
				txtPaternoF.clear();
				txtMaternoF.clear();
				
				txtDireccionF.clear();
				txtTelefonoF.clear();
				txtCorreoF.clear();
				
				txtCiudadF.clear();
				txtEstadoF.clear();
				txtIDF.clear();
				
				txtPaternoF.setDisable(true);
				txtMaternoF.setDisable(true);
				txtPaisF.setDisable(true);
				txtRFCF.setDisable(true);
				
				txtNombreF.setDisable(true);
				txtTelefonoF.setDisable(true);
				txtCiudadF.setDisable(true);
				txtEstadoF.setDisable(true);
				txtDireccionF.setDisable(true);
				
				txtCorreoF.setDisable(true);
				btnNuevo.setDisable(false);
				btnEliminar.setDisable(true);
				btnModificar.setDisable(true);
				btnGuardar.setDisable(true);
				txtNombreF.setDisable(true);
				btnCancelarF.setDisable(true);
			}
			else{
				lblMensaje.setText("Ha ocurrido un error");
			}
		}
	}
	
	@FXML public void clickModificarM(){
		if(txtIDM.getText().isEmpty()){
			lblMensajeM.setText("Debe Seleccionar un cliente");
			
		}
		else{
			clienteM= new ClientesMorales();
			clienteM.setMoralesid(Integer.parseInt(txtIDM.getText()));
			clienteM.setRazonsocial(txtRazonM.getText());
			clienteM.setNombre(txtNombreM.getText());
			clienteM.setRfc(txtRFCM.getText());
			clienteM.setDireccion(txtDireccionM.getText());
			clienteM.setCiudad(txtCiudadM.getText());
			clienteM.setEstado(txtEstadoM.getText());
			clienteM.setPais(txtPaisM.getText());
			clienteM.setTelefono(txtTelefonoM.getText());
			clienteM.setCorreo(txtCorreoM.getText());
			Boolean result=daoMor.modificarM(clienteM);
			if(result==true){
				lblMensajeM.setText("Se han modificado los datos");
				llenarTabla(true);
				btnGuardarM.setDisable(true);
				btnNuevoM.setDisable(false);
				btnModificarM.setDisable(true);
				btnEliminarM.setDisable(true);
				txtNombreM.setDisable(true);
				txtNombreM.clear();
				txtRazonM.setDisable(true);
				
				
				txtRFCM.clear();
				txtPaisM.clear();
				txtNombreM.clear();
				
				txtRazonM.clear();
				
				txtDireccionM.clear();
				txtTelefonoM.clear();
				txtCorreoM.clear();
				
				txtCiudadM.clear();
				txtEstadoM.clear();
				txtIDM.clear();
				
				btnCancelarM.setDisable(true);
				txtRazonM.setDisable(true);
				txtPaisM.setDisable(true);
				txtRFCM.setDisable(true);
				
				txtNombreM.setDisable(true);
				txtTelefonoM.setDisable(true);
				txtCiudadM.setDisable(true);
				txtEstadoM.setDisable(true);
				txtDireccionM.setDisable(true);
				
				txtCorreoM.setDisable(true);
			}
			else{
				lblMensajeM.setText("Ha ocurrido un error");
			}
		}
	}
@FXML public void mostrarInactivos(){
		if(ckbInactivos.isSelected()){
			//Mostrar Inactivos
			llenarTabla(false);
			txtNombreF.setDisable(true);
			txtNombreF.clear();
			txtPaternoF.setDisable(true);
			txtPaternoF.clear();
			
			txtRFCF.clear();
			txtPaisF.clear();
			txtNombreF.clear();
			txtPaternoF.clear();
			txtMaternoF.clear();
			
			txtDireccionF.clear();
			txtTelefonoF.clear();
			txtCorreoF.clear();
			
			txtCiudadF.clear();
			txtEstadoF.clear();
			txtIDF.clear();
			
			txtPaternoF.setDisable(true);
			txtMaternoF.setDisable(true);
			txtPaisF.setDisable(true);
			txtRFCF.setDisable(true);
			
			txtNombreF.setDisable(true);
			txtTelefonoF.setDisable(true);
			txtCiudadF.setDisable(true);
			txtEstadoF.setDisable(true);
			txtDireccionF.setDisable(true);
			btnCancelarF.setDisable(true);
			txtCorreoF.setDisable(true);
		}
		else{
			//Mostrar activos
			llenarTabla(true);
			btnNuevo.setDisable(false);
		}
	}


@FXML public void mostrarInactivosM(){
		if(ckbInactivosM.isSelected()){
			//Mostrar Inactivos
			llenarTabla(false);
			btnGuardarM.setDisable(true);
			btnNuevoM.setDisable(false);
			btnModificarM.setDisable(true);
			btnEliminarM.setDisable(true);
			txtNombreM.setDisable(true);
			txtNombreM.clear();
			txtRazonM.setDisable(true);
			
			
			txtRFCM.clear();
			txtPaisM.clear();
			txtNombreM.clear();
			
			txtRazonM.clear();
			
			txtDireccionM.clear();
			txtTelefonoM.clear();
			txtCorreoM.clear();
			
			txtCiudadM.clear();
			txtEstadoM.clear();
			txtIDM.clear();
			
			
			txtRazonM.setDisable(true);
			txtPaisM.setDisable(true);
			txtRFCM.setDisable(true);
			
			txtNombreM.setDisable(true);
			txtTelefonoM.setDisable(true);
			txtCiudadM.setDisable(true);
			txtEstadoM.setDisable(true);
			txtDireccionM.setDisable(true);
			btnCancelarM.setDisable(true);
			txtCorreoM.setDisable(true);
		}
		else{
			//Mostrar activos
			llenarTabla(true);
			btnNuevoM.setDisable(false);
		}
	}
}

