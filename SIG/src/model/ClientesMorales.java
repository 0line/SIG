package model;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class ClientesMorales {
	private int moralesid;
	private String razonsocial;
	private String nombre;
	private String rfc;
	private String direccion;
	private String ciudad;
	private String estado;
	private String pais;	
	private String telefono;
	private String correo;;
	private Boolean estatus;
	private ConnectionBD micon = new ConnectionBD();
	private PreparedStatement comando;
	private ResultSet rsClientes;
	private ObservableList<ClientesMorales> listaClientesM;
	
	public ClientesMorales(){
		listaClientesM=FXCollections.observableArrayList();
		this.moralesid=0;
		this.razonsocial="";
		this.nombre="";
		this.rfc="";
		this.direccion="";
		this.ciudad="";
		this.estado="";
		this.pais="";
		this.telefono="";
		this.correo="";
		this.estatus=false;
		
	}
	
	public ClientesMorales (int moralesid, String razonsocial, String nombre, String rfc, String direccion, String ciudad, String estado, String pais, String telefono, String correo, Boolean estatus){
		this.moralesid=moralesid;
		this.razonsocial=razonsocial;
		this.nombre=nombre;
		this.rfc=rfc;
		this.direccion=direccion;
		this.ciudad=ciudad;
		this.estado=estado;
		this.pais=pais;
		this.telefono=telefono;
		this.correo=correo;
		this.estatus=estatus;
		rsClientes=null;
		listaClientesM=FXCollections.observableArrayList();
	}
	
	//SETTERS & GETTERS
//-------------------------------------------------------------------------------

	public void setMoralesid(int moralesid) {
		this.moralesid = moralesid;
	}
	public int getMoralesid() {
		return moralesid;
	}
	public String getRazonsocial() {
		return razonsocial;
	}
	public void setRazonsocial(String razonsocial) {
		this.razonsocial = razonsocial;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
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
	



	
	//Metodo para insertar a la base de datos
	public Boolean insertarM(ClientesMorales clientesM){
		try{
			micon.desconect();	
			
		String sql="insert into clientesmorales values(";
		sql+="default,?,?,?,?,?,?,?,?,?,'1')";
		//System.out.println(sql);
		comando =micon.getCon().prepareStatement(sql);
		//System.out.println(comando);
		comando.setString(1, clientesM.getRazonsocial());
		comando.setString(2, clientesM.getNombre());
		comando.setString(3, clientesM.getRfc());
		comando.setString(4, clientesM.getDireccion());
		comando.setString(5, clientesM.getCiudad());
		comando.setString(6, clientesM.getEstado());
		comando.setString(7, clientesM.getPais());
		comando.setString(8, clientesM.getTelefono());
		comando.setString(9, clientesM.getCorreo());
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
	
	public ObservableList<ClientesMorales> consultarClientesM(String consulta){
		try {
			micon.conect();
			comando = micon.getCon().prepareStatement(consulta);
			rsClientes = comando.executeQuery();
			while(rsClientes.next()){
				ClientesMorales c = new ClientesMorales();
				c.setMoralesid(rsClientes.getInt("moralesid"));
				c.setRazonsocial(rsClientes.getString("razonsocial"));
				c.setNombre(rsClientes.getString("nombre"));
				c.setRfc(rsClientes.getString("rfc"));
				c.setDireccion(rsClientes.getString("direccion"));
				c.setCiudad(rsClientes.getString("ciudad"));
				c.setEstado(rsClientes.getString("estado"));
				c.setPais(rsClientes.getString("pais"));
				c.setTelefono(rsClientes.getString("telefono"));
				c.setCorreo(rsClientes.getString("correo"));				
				c.setEstatus(rsClientes.getBoolean("estatus"));
				
				//AGREGAR OBJETO A LA LISTA
				listaClientesM.add(c);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		System.out.println("No se puede llenar la tabla Morales");
		}
		finally{
			micon.desconect();
		}
		return listaClientesM;
	}

//Metodo para eliminar una categoria
	public Boolean eliminarM(int moralesid ){
		
		try {
			micon.conect();
			String operacion="update clientesmorales set estatus='0' where moralesid=?";
			comando=micon.getCon().prepareStatement(operacion);
			
			comando.setInt(1, moralesid);
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
	public Boolean modificarM(ClientesMorales cat){
		try {
			micon.conect();
			String operacion ="update clientesmorales set razonsocial=?, nombre=?, rfc=?, direccion=?, ciudad=?, estado=?, pais=?, telefono=?, correo=? where moralesid=?";
			comando=micon.getCon().prepareStatement(operacion);
			comando.setString(1, cat.getRazonsocial());
			comando.setString(2, cat.getNombre());
			comando.setString(3, cat.getRfc());
			comando.setString(4, cat.getDireccion());
			comando.setString(5, cat.getCiudad());
			comando.setString(6, cat.getEstado());
			comando.setString(7, cat.getPais());
			comando.setString(8, cat.getTelefono());
			comando.setString(9, cat.getCorreo());
			comando.setInt(10, cat.getMoralesid());
	
			
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