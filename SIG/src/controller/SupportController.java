package controller;

import java.net.URL;
import java.sql.Date;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LocalDateStringConverter;
import jdk.nashorn.internal.parser.DateParser;
import model.Equipos;
import model.MClienteMoral;
import sun.misc.FpUtils;
import sun.security.provider.MD5;

public class SupportController implements Initializable{
	@FXML private Button btnNew,btnSave,btnCancel,btnUpdate,btnDelete;
	@FXML private DatePicker dpregistro;
	@FXML private RadioButton rdfisico, rdmoral;
	@FXML private ToggleGroup tipocliente;
	@FXML private ComboBox<model.MClienteFisico>cbfisicos;
	@FXML private ComboBox<model.MClienteMoral>cbmorales;
	@FXML private ComboBox<String> cbtipos;
	@FXML private TextField txtmarca,txtmodelo,txtnserie;
	@FXML private TextArea txtdescripcion;
	@FXML private Label lblMensaje;
	@FXML private CheckBox ckbinactivos;
	@FXML private TableView<model.Equipos>tvEquipos;
	private ObservableList<model.MClienteMoral> listcmorales;
	private ObservableList<String> listTipos;
	private ObservableList<model.Equipos> listequipos;
	private MClienteMoral mcm;
	private Equipos equipo;
	private FilteredList<model.MClienteMoral> bslistmorales;
	int fisico;
	public int id,fis,mor;
	//Método constructor
	public SupportController() {
		listTipos=FXCollections.observableArrayList("pc","laptop","tablet","smartphone");
		listcmorales=FXCollections.observableArrayList();
		listequipos=FXCollections.observableArrayList();
		mcm=new MClienteMoral();
		equipo= new Equipos();
		id=fis=mor=0;
	}
	
	
	//Método para llenar el combo box con los clientes morales de la base de datos 
	public void llenarCMMorales(){
		listcmorales.clear();
		String sql="select moralesid,razonsocial from clientesmorales where estatus='1'";
		listcmorales=mcm.colsultarCMorales(sql);
		//Bloque para convertir a String lo recivido de la BD
		cbmorales.setConverter(new StringConverter<MClienteMoral>() {
			@Override
			public String toString(MClienteMoral object) {
				// TODO Auto-generated method stub
				String text= String.valueOf(object.getMoralesid())+"-"+object.getRazonsocial();
				return text;
			}

			@Override
			public MClienteMoral fromString(String string) {
				// TODO Auto-generated method stub
				return null;
			}
		});
		bslistmorales=new FilteredList<>(listcmorales);
		cbmorales.setItems(listcmorales);
	}

	//Metodos para activar combobox clientes fisicos y morales
	
	@FXML private void actCombo(){
		if (rdmoral.isSelected()) {
			cbfisicos.setDisable(true);
			cbmorales.setDisable(false);
			cbfisicos.setValue(null);
		}
		else{
			if (rdfisico.isSelected()) {
				cbmorales.setDisable(true);
				cbmorales.setValue(null);
				cbfisicos.setDisable(false);
			}
		}
		
	}
	
	
	
	@FXML private void New(){
		btnNew.setDisable(true);
		btnSave.setDisable(false);
		btnCancel.setDisable(false);
		dpregistro.setDisable(false);
		rdfisico.setDisable(false); 
		rdmoral.setDisable(false);
		cbtipos.setDisable(false);
		txtmarca.setDisable(false);
		txtmodelo.setDisable(false);
		txtnserie.setDisable(false);
		txtdescripcion.setDisable(false);
	}
	@FXML private void Cancel(){
		btnSave.setDisable(true);
		btnCancel.setDisable(true);
		dpregistro.setDisable(true);
		rdfisico.setDisable(true); 
		rdmoral.setDisable(true);
		cbtipos.setDisable(true);
		txtmarca.setDisable(true);
		txtmodelo.setDisable(true);
		txtnserie.setDisable(true);
		txtdescripcion.setDisable(true);
		btnNew.setDisable(false);
		btnCancel.setDisable(true);
		cbmorales.setValue(null);
		cbfisicos.setValue(null);
		dpregistro.setValue(null);
		rdfisico.setSelected(true);
		rdmoral.setSelected(false);
		cbtipos.setValue(null);
		txtmarca.clear();
		txtmodelo.clear();
		txtnserie.clear();
		txtdescripcion.clear();
		lblMensaje.setText("");
		lblMensaje.setVisible(false);
		btnDelete.setDisable(true);
	}
	@FXML private void save(){
		if (dpregistro.getValue()==null) {
			lblMensaje.setVisible(true);
			lblMensaje.setText("Falta la fecha");
			dpregistro.setFocusTraversable(true);
		}
		else{
			if(rdfisico.isSelected() || rdmoral.isSelected()){
				if (cbfisicos.getValue()==null && cbmorales.getValue()==null) {
					lblMensaje.setVisible(true);
					lblMensaje.setText("Falta elegir al dueño del equipo (cliente)");
				}
				else{
					if (cbtipos.getValue()==null) {
						lblMensaje.setVisible(true);
						lblMensaje.setText("Falta elegir el tipo de equipo");
					}
					else{
						if (txtmarca.getText()==null) {
							lblMensaje.setVisible(true);
							lblMensaje.setText("Falta la marca del equipo");
						}
						else{
							if (txtmodelo.getText()==null) {
								lblMensaje.setVisible(true);
								lblMensaje.setText("Falta el modelo del equipo");
							}
							else{
								if (txtnserie.getText()==null) {
									lblMensaje.setVisible(true);
									lblMensaje.setText("Falta el número de serie del equipo");
								}
								else{
									if (txtdescripcion.getText()==null) {
										lblMensaje.setVisible(true);
										lblMensaje.setText("Falta la descripción del equipo");
									}
									else{
										String fecha=String.valueOf(dpregistro.getValue());
										if (cbfisicos.getValue()==null) {
											 fisico=0;
										} else {
											 fisico=Integer.parseInt(String.valueOf(cbfisicos.getValue()));
										}
										int moral=Integer.parseInt(String.valueOf(cbmorales.getSelectionModel().getSelectedItem().getMoralesid()));
										Boolean result=equipo.insertar(new Equipos(0,cbtipos.getValue(),txtmodelo.getText(),txtnserie.getText(),txtmarca.getText(),txtdescripcion.getText(),fecha,moral,fisico,1));
										if(result){
											lblMensaje.setVisible(true);
											lblMensaje.setText("Los datos se han "
													+ "insertado satisfactoriamente");
											llenarTabla(true);
											
										}
										else{
											lblMensaje.setVisible(true);
											lblMensaje.setText("Ha Ocurrido un"
													+ " error consulte a su administrador");
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	//Método para llenar la tabla
	public void llenarTabla(Boolean estatus){
		listequipos.clear();
		if(estatus==true){
			System.out.println("antes de llenar la lista");
			String sql="select * from equipos where estatus_equipo=1";
			listequipos=equipo.llenarTabla(sql);
		}
		else{listequipos=listequipos=equipo.llenarTabla("select * from equipos where estatus_equipo=0");}
		//listaBusqueda=new FilteredList<>(listautores);
		tvEquipos.setItems(listequipos);
		}
	
	//Método para regresar la table
	
	@FXML public void selectionTable(){
		if(tvEquipos.getSelectionModel().getSelectedItem()!=null){
			equipo=tvEquipos.getSelectionModel().getSelectedItem();
			btnUpdate.setDisable(false);
			btnCancel.setDisable(false);
			btnDelete.setDisable(false);
			dpregistro.setDisable(false);
			rdfisico.setDisable(false); 
			rdmoral.setDisable(false);
			cbtipos.setDisable(false);
			txtmarca.setDisable(false);
			txtmodelo.setDisable(false);
			txtnserie.setDisable(false);
			txtdescripcion.setDisable(false);
			id=equipo.getId_equipos();
			int day=equipo.getFecha_registro().length();
			//Variable para recuperar la fecha
			int año=Integer.parseInt(equipo.getFecha_registro().substring(0, 4));
			int mes=Integer.parseInt(equipo.getFecha_registro().substring(5, 7));
			int dia=Integer.parseInt(equipo.getFecha_registro().substring(8, 10));
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
			dpregistro.setValue(dpregistro.getValue().parse(equipo.getFecha_registro()));
			
			if (cbfisicos.getValue()==null) {
				 fis=equipo.getFisicos_id();
			} else {
				 fis=equipo.getFisicos_id();
			}
			if (cbmorales.getValue()==null) {
				 mor=equipo.getMoral_id();
			}
			else{
				 mor=cbmorales.getSelectionModel().getSelectedItem().getMoralesid();
			}
			cbtipos.setValue(equipo.getTipo());
			txtmarca.setText(equipo.getMarca());
			txtmodelo.setText(equipo.getModelo());
			txtnserie.setText(equipo.getNserie());
			txtdescripcion.setText(equipo.getDescripcion_equipo());
		}
	}
	
	//Método para actualizar
	@FXML private void update(){
		if(id==0){
			lblMensaje.setText("No ha seleccionado un equipo");
		}
		else{
			String fecha=String.valueOf(dpregistro.getValue());
			equipo.setId_equipos(id);
			equipo.setFecha_registro(fecha);
			equipo.setFisicos_id(fis);
			equipo.setMoral_id(mor);
			equipo.setTipo(cbtipos.getValue());
			equipo.setMarca(txtmarca.getText());
			equipo.setModelo(txtmodelo.getText());
			equipo.setNserie(txtnserie.getText());
			equipo.setDescripcion_equipo(txtdescripcion.getText());
			Boolean result=equipo.update(equipo);
			if (result==true) {
				lblMensaje.setText("Se han actualizado los datos correctamente");
				llenarTabla(true);
				Cancel();
			}
		 else {
			lblMensaje.setText("No ha seleccionado una sucursal");
		 	}
		}
	}
	
	@FXML public void delete(){
		if(id==0){
			lblMensaje.setText("No ha seleccionado una categoría");
		}
		else{
			Boolean result= equipo.delete(id);
			if(result==true){
				lblMensaje.setText("Se ha eliminado la categoria");
				llenarTabla(true);
				Cancel();
			}
			else{
				lblMensaje.setText("Ha ocurrido un error,consultar a tu proveedor de servicios");
			}
		}
	}
	
	//Método para mostrar eliminados
	@FXML public void seeDisable(){
		listequipos.clear();
		if(ckbinactivos.isSelected()){
			//Mostrar inactivos
			llenarTabla(false);
		}
		else{
			//Mostrar Activos
			llenarTabla(true);
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//Inicializando controles para la vista
		llenarCMMorales();
		cbtipos.setItems(listTipos);
		llenarTabla(true);
	}
}
