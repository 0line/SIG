package model;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ConnectionBD;


public class Productos {
	private int productoid;
	private String nombre;
	private String descripcion;
	private int numeroserie;	
	private String marca;
	private String modelo;
	private String precio;
	private int existencias;
	private Boolean estatus;
	private ConnectionBD micon = new ConnectionBD();
	private PreparedStatement comando;
	private ResultSet rsProductos;
	private ObservableList<Productos> listaProductos;
	
	public Productos(){
		listaProductos=FXCollections.observableArrayList();
		this.productoid=0;
		this.nombre="";
		this.descripcion="";
		this.numeroserie=0;
		this.marca="";
		this.modelo="";
		this.precio="";
		this.existencias=0;
		this.estatus=false;
		
	}
	
	public Productos (int productoid, String nombre, String descripcion,int numeroserie, String marca, String modelo, String precio,int existencias,Boolean estatus){
		this.productoid=productoid;
		this.nombre=nombre;
		this.descripcion=descripcion;
		this.numeroserie=numeroserie;		
		this.marca=marca;
		this.modelo=modelo;
		this.precio=precio;
		this.existencias=existencias;
		this.estatus=estatus;
		
		micon = new ConnectionBD();
		rsProductos=null;
		listaProductos=FXCollections.observableArrayList();
	}
	
	//SETTERS & GETTERS
	
	public int getProductoid() {
		return productoid;
	}
	public void setProductoid(int productoid) {
		this.productoid = productoid;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public int getNumeroserie() {
		return numeroserie;
	}
	public void setNumeroserie(int numeroserie) {
		this.numeroserie = numeroserie;
	}
	public String getPrecio() {
		return precio;
	}
	public void setPrecio(String precio) {
		this.precio = precio;
	}
	public int getExistencias() {
		return existencias;
	}
	public void setExistencias(int existencias) {
		this.existencias = existencias;
	}
	public Boolean getEstatus() {
		return estatus;
	}
	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

//-------------------------------------------------------------------------------





	
	//Metodo para insertar a la base de datos
	public Boolean insertar(Productos productos){
		try{
			micon.conect();	
		String sql="insert into productos values(";
		sql+="default,?,?,?,?,?,?,?,'1')";
		comando =micon.getCon().prepareStatement(sql);
		comando.setString(1, productos.getNombre());
		comando.setString(2, productos.getDescripcion());
		comando.setInt(3, productos.getNumeroserie());
		comando.setString(4, productos.getMarca());
		comando.setString(5, productos.getModelo());
		comando.setString(6, productos.getPrecio());
		comando.setInt(7, productos.getExistencias());
		//System.out.println(comando);
	comando.execute();
		return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		finally{
			micon.desconect();
		}
		
	}	
	
	// METODO PARA CONSULTAR
	
	/**
	 * @param consulta
	 * @return
	 */
	public ObservableList<Productos> consultarProductos(String consulta){
		try {
			micon.conect();
			comando = micon.getCon().prepareStatement(consulta);
			rsProductos = comando.executeQuery();
			while(rsProductos.next()){
				Productos c = new Productos();
				c.setProductoid(rsProductos.getInt("productoid"));
				c.setNombre(rsProductos.getString("nombre"));
				c.setDescripcion(rsProductos.getString("descripcion"));
				c.setNumeroserie(rsProductos.getInt("numeroserie"));
				c.setMarca(rsProductos.getString("marca"));
				c.setModelo(rsProductos.getString("modelo"));
				c.setPrecio(rsProductos.getString("precio"));
				c.setExistencias(rsProductos.getInt("existencias"));
				c.setEstatus(rsProductos.getBoolean("estatus"));
				//System.out.println(c);
				//AGREGAR OBJETO A LA LISTA
				listaProductos.add(c);
								
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			micon.desconect();
		}
		return listaProductos;
	}

//Metodo para eliminar una categoria
	public Boolean eliminar(int productoid ){
		
		try {
			micon.conect();
			String operacion="update productos set estatus='0' where productoid=?";
			comando=micon.getCon().prepareStatement(operacion);
			
			comando.setInt(1, productoid);
			comando.execute();
			
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		finally{
			micon.desconect();
		}
	}
	public Boolean modificar(Productos cat){
		try {
			micon.conect();
			String operacion ="update productos set nombre=?, descripcion=?, numeroserie=?, marca=?, modelo=?, precio=?, existencias=? where productoid=?";
			comando=micon.getCon().prepareStatement(operacion);
			comando.setString(1, cat.getNombre());
			comando.setString(2, cat.getDescripcion());
			comando.setInt(3, cat.getNumeroserie());
			comando.setString(4, cat.getMarca());
			comando.setString(5, cat.getModelo());
			comando.setString(6, cat.getPrecio());
			comando.setInt(7, cat.getExistencias());
			comando.setInt(8, cat.getProductoid());
	
			
			comando.execute();
			return true;
			} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		finally{
			micon.desconect();
		}
	}
}
