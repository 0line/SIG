package controller;

import java.net.URL;
import java.util.ResourceBundle;







import model.Productos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableView;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
public class ControllerProductos implements Initializable{
	
	@FXML private TextField txtID, txtNombre, txtDescripcion, txtMarca, txtNumero, txtModelo,txtPrecio, txtExistencias;
	@FXML private Button btnModificar, btnEliminar,btnNuevo, btnGuardar;
	@FXML private Label lblMensaje;
	@FXML private CheckBox ckbInactivos;
	@FXML private TableView<Productos> tvProductos;

	//Instancia 
	//Instancia 
	private Productos daoPRO = new Productos();
	private ObservableList<model.Productos> listaProductos;
	private Productos producto;
	
	public ControllerProductos(){
		listaProductos=FXCollections.observableArrayList();
		producto= new Productos();
	}
	
	public void llenarTabla(Boolean estatus){
		listaProductos.clear();
		
		if(estatus==true){
			listaProductos = producto.consultarProductos("select * from productos where estatus='1'");
			
		}else{
			

			listaProductos = producto.consultarProductos("select * from productos where estatus='0'");
			
		}
		
		tvProductos.setItems(listaProductos);
			}
		

	
	@FXML public void clickNuevo(){
		
		btnModificar.setDisable(true);
		btnEliminar.setDisable(true);
		btnNuevo.setDisable(true);
btnGuardar.setDisable(false);

txtNombre.setDisable(false);
txtMarca.setDisable(false);		
txtDescripcion.setDisable(false);
txtNumero.setDisable(false);
txtModelo.setDisable(false);
txtExistencias.setDisable(false);
txtPrecio.setDisable(false);
txtNombre.clear();
txtMarca.clear();
txtDescripcion.clear();
txtModelo.clear();
txtExistencias.clear();
txtPrecio.clear();
txtNumero.clear();
	}
	@FXML public void clickInsertar(){
		if(txtNombre.getText().trim().isEmpty() || txtMarca.getText().trim().isEmpty() || txtModelo.getText().trim().isEmpty()  ||  txtDescripcion.getText().trim().isEmpty() ||txtNumero.getText().trim().isEmpty() || txtExistencias.getText().trim().isEmpty() || txtPrecio.getText().trim().isEmpty()) 
		{
			lblMensaje.setText("Todos los campos son Obligatorios");
			
		}
		else{
			Boolean result = daoPRO.insertar(new Productos(0, txtNombre.getText(), txtDescripcion.getText(), (Integer.parseInt(txtNumero.getText())), txtMarca.getText(), txtModelo.getText(), txtPrecio.getText(), (Integer.parseInt(txtExistencias.getText())),true));
			if(result) {
				lblMensaje.setText("Los datos se han insertado satisfactoriamente");
				llenarTabla(true);
				btnModificar.setDisable(true);
				btnEliminar.setDisable(true);
				btnNuevo.setDisable(false);
		btnGuardar.setDisable(true);

		txtNombre.setDisable(true);
		txtMarca.setDisable(true);		
		txtDescripcion.setDisable(true);
		txtNumero.setDisable(true);
		txtModelo.setDisable(true);
		txtExistencias.setDisable(true);
		txtPrecio.setDisable(true);
		txtNombre.clear();
		txtMarca.clear();
		txtDescripcion.clear();
		txtModelo.clear();
		txtExistencias.clear();
		txtPrecio.clear();
		txtNumero.clear();				
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
		
		if(tvProductos.getSelectionModel().getSelectedItem()!=null){
			producto = tvProductos.getSelectionModel().getSelectedItem();
			txtNombre.setText(producto.getNombre());
			txtDescripcion.setText(producto.getDescripcion());
			txtNumero.setText(String.valueOf(producto.getNumeroserie()));
			txtMarca.setText(producto.getMarca());
			txtModelo.setText(producto.getModelo());
			txtPrecio.setText(producto.getPrecio());
			txtExistencias.setText(String.valueOf(producto.getExistencias()));
			txtID.setText(String.valueOf(producto.getProductoid()));
			//Habilitar las cajas de texto		//Habilitar los botones para modificar y eliminar
			btnEliminar.setDisable(false);
			btnModificar.setDisable(false);
			//Deshabilitar el de nuevo
			btnNuevo.setDisable(true);
			btnGuardar.setDisable(true);
			
			txtNombre.setDisable(false);
			txtMarca.setDisable(false);		
			txtDescripcion.setDisable(false);
			txtNumero.setDisable(false);
			txtModelo.setDisable(false);
			txtExistencias.setDisable(false);
			txtPrecio.setDisable(false);
	txtNombre.clear();
	txtMarca.clear();
	txtDescripcion.clear();
	txtModelo.clear();
	txtExistencias.clear();
	txtPrecio.clear();
	txtNumero.clear();
		}
	}
	
	@FXML public void clickEliminar(){
		if(txtID.getText().isEmpty()){
			lblMensaje.setText("No se ha seleccionado un empleado");
			}
		else{
			int productoid = Integer.parseInt(txtID.getText());
			Boolean result =daoPRO.eliminar(productoid);
			if(result==true){
				lblMensaje.setText("Se ha eliminado el empleado");
				txtNombre.setDisable(true);
				txtNombre.clear();
				
				llenarTabla(true);
				btnNuevo.setDisable(false);
				btnEliminar.setDisable(true);
				btnModificar.setDisable(true);
				btnGuardar.setDisable(true);
				txtNombre.setDisable(true);
				txtMarca.setDisable(true);		
				txtDescripcion.setDisable(true);
				txtNumero.setDisable(true);
				txtModelo.setDisable(true);
				txtExistencias.setDisable(true);
				txtPrecio.setDisable(true);
				txtNombre.clear();
				txtMarca.clear();
				txtDescripcion.clear();
				txtModelo.clear();
				txtExistencias.clear();
				txtPrecio.clear();
				txtNumero.clear();	
			}
			else{
				lblMensaje.setText("Ha ocurrido un error");
			}
			
		}
	}
	
	@FXML public void clickModificar(){
		if(txtID.getText().isEmpty()){
			lblMensaje.setText("Debe Seleccionar un producto");
			
		}
		else{
			producto= new Productos();
			producto.setProductoid(Integer.parseInt(txtID.getText()));
			
			producto.setNombre(txtNombre.getText());
			producto.setDescripcion(txtDescripcion.getText());
			producto.setNumeroserie(Integer.parseInt(txtNumero.getText()));
			
			producto.setMarca(txtMarca.getText());
			producto.setModelo(txtModelo.getText());
			producto.setPrecio(txtPrecio.getText());
			producto.setExistencias(Integer.parseInt(txtExistencias.getText()));
			Boolean result=daoPRO.modificar(producto);
			if(result==true){
				lblMensaje.setText("Se han modificado los datos");
				llenarTabla(true);
				txtNombre.setDisable(true);
				txtNombre.clear();	btnNuevo.setDisable(false);
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
			btnNuevo.setDisable(false);
			btnEliminar.setDisable(true);
			btnModificar.setDisable(true);
			btnGuardar.setDisable(true);
			txtNombre.setDisable(true);
			txtMarca.setDisable(true);		
			txtDescripcion.setDisable(true);
			txtNumero.setDisable(true);
			txtModelo.setDisable(true);
			txtExistencias.setDisable(true);
			txtPrecio.setDisable(true);
			txtNombre.clear();
			txtMarca.clear();
			txtDescripcion.clear();
			txtModelo.clear();
			txtExistencias.clear();
			txtPrecio.clear();
			txtNumero.clear();	
		}
		else{
			//Mostrar activos
			llenarTabla(true);
			btnNuevo.setDisable(false);
		}
	}
}