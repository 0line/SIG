package model;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Empleados {
	private int empleadoid;
	private String nombre;
	private String apellidop;
	private String apellidom;	
	private String direccion;
	private String ciudad;
	private String estado;
	private String pais;	
	private String telefono;
	private String correo;;
	private Boolean estatus;
	private ConnectionBD micon = new ConnectionBD();
	private PreparedStatement comando;
	private ResultSet rsEmpleados;
	private ObservableList<Empleados> listaEmpleados;
	
	public Empleados(){
		listaEmpleados=FXCollections.observableArrayList();
		this.empleadoid=0;
		this.nombre="";
		this.apellidop="";
		this.apellidom="";
		this.direccion="";
		this.ciudad="";
		this.estado="";
		this.pais="";
		this.telefono="";
		this.correo="";
		this.estatus=false;
		
	}
	
	public Empleados (int empleadoid, String nombre, String apellidop,String apellidom, String direccion, String ciudad, String estado, String pais, String telefono, String correo, Boolean estatus){
		this.empleadoid=empleadoid;
		this.nombre=nombre;
		this.apellidop=apellidop;
		this.apellidom=apellidom;		
		this.direccion=direccion;
		this.ciudad=ciudad;
		this.estado=estado;
		this.pais=pais;
		this.telefono=telefono;
		this.correo=correo;
		this.estatus=estatus;
		
		rsEmpleados=null;
		listaEmpleados=FXCollections.observableArrayList();
	}
	
	//SETTERS & GETTERS
	public int getEmpleadoid() {
		return empleadoid;
	}
	public void setEmpleadoid(int empleadoid) {
		this.empleadoid = empleadoid;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidop() {
		return apellidop;
	}
	public void setApellidop(String apellidop) {
		this.apellidop = apellidop;
	}
	public String getApellidom() {
		return apellidom;
	}
	public void setApellidom(String apellidom) {
		this.apellidom = apellidom;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
public Boolean getEstatus() {
	return estatus;
}
public void setEstatus(Boolean estatus) {
	this.estatus = estatus;
}

//-------------------------------------------------------------------------------





	
	//Metodo para insertar a la base de datos
	public Boolean insertar(Empleados empleados){
		try{
			micon.conect();	
		String sql="insert into empleados values(";
		sql+="default,?,?,?,?,?,?,?,?,?,'1')";
		//System.out.println(sql);
		comando =micon.getCon().prepareStatement(sql);
		//System.out.println(comando);
		comando.setString(1, empleados.getNombre());
		comando.setString(2, empleados.getApellidop());
		comando.setString(3, empleados.getApellidom());
		comando.setString(4, empleados.getDireccion());
		comando.setString(5, empleados.getCiudad());
		comando.setString(6, empleados.getEstado());
		comando.setString(7, empleados.getPais());
		comando.setString(8, empleados.getTelefono());
		comando.setString(9, empleados.getCorreo());
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
	public ObservableList<Empleados> consultarEmpleados(String consulta){
		try {
			micon.conect();
			comando = micon.getCon().prepareStatement(consulta);
			rsEmpleados = comando.executeQuery();
			while(rsEmpleados.next()){
				Empleados c = new Empleados();
				c.setEmpleadoid(rsEmpleados.getInt("empleadoid"));
				c.setNombre(rsEmpleados.getString("nombre"));
				c.setApellidop(rsEmpleados.getString("apellidop"));
				c.setApellidom(rsEmpleados.getString("apellidom"));
				c.setDireccion(rsEmpleados.getString("direccion"));
				c.setCiudad(rsEmpleados.getString("ciudad"));
				c.setEstado(rsEmpleados.getString("estado"));
				c.setPais(rsEmpleados.getString("pais"));
				c.setTelefono(rsEmpleados.getString("telefono"));
				c.setCorreo(rsEmpleados.getString("correo"));				
				c.setEstatus(rsEmpleados.getBoolean("estatus"));
				//System.out.println(c);
				//AGREGAR OBJETO A LA LISTA
				listaEmpleados.add(c);
								
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			micon.desconect();
		}
		return listaEmpleados;
	}

//Metodo para eliminar una categoria
	public Boolean eliminar(int empleadoid ){
		
		try {
			micon.conect();
			String operacion="update empleados set estatus='0' where empleadoid=?";
			comando=micon.getCon().prepareStatement(operacion);
			
			comando.setInt(1, empleadoid);
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
	public Boolean modificar(Empleados cat){
		try {
			micon.conect();
			String operacion ="update empleados set nombre=?, apellidop=?, apellidom=?, direccion=?, ciudad=?, estado=?, pais=?, telefono=?, correo=? where empleadoid=?";
			comando=micon.getCon().prepareStatement(operacion);
			comando.setString(1, cat.getNombre());
			comando.setString(2, cat.getApellidop());
			comando.setString(3, cat.getApellidom());
			comando.setString(4, cat.getDireccion());
			comando.setString(5, cat.getCiudad());
			comando.setString(6, cat.getEstado());
			comando.setString(7, cat.getPais());
			comando.setString(8, cat.getTelefono());
			comando.setString(9, cat.getCorreo());
			comando.setInt(10, cat.getEmpleadoid());
	
			
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