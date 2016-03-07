package model;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class ClientesFisicos {
	private int fisicosid;
	private String rfc;
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
	private ConnectionBD myCon = new ConnectionBD();
	private PreparedStatement comando;
	private ResultSet rsClientes;
	private ObservableList<ClientesFisicos> listaClientes;
	private ObservableList<ClientesFisicos> listaCFisicos;
	public ClientesFisicos(){
		listaClientes=FXCollections.observableArrayList();
		listaCFisicos=FXCollections.observableArrayList();
		this.fisicosid=0;
		this.rfc="";
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
	
	public ClientesFisicos (int fisicosid, String rfc, String nombre, String apellidop,String apellidom, String direccion, String ciudad, String estado, String pais, String telefono, String correo, Boolean estatus){
		this.fisicosid=fisicosid;
		this.rfc=rfc;
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
		
		rsClientes=null;
		listaClientes=FXCollections.observableArrayList();
	}
	
	//SETTERS & GETTERS
	public int getFisicosid() {
		return fisicosid;
	}
	public void setFisicosid(int fisicosid) {
		this.fisicosid = fisicosid;
	}
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
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
	public Boolean insertar(ClientesFisicos clientes){
		try{
			myCon.conect();	
		String sql="insert into clientesfisicos values(";
		sql+="default,?,?,?,?,?,?,?,?,?,?,'1')";
		//System.out.println(sql);
		comando =myCon.getCon().prepareStatement(sql);
		//System.out.println(comando);
		comando.setString(1, clientes.getRfc());
		comando.setString(2, clientes.getNombre());
		comando.setString(3, clientes.getApellidop());
		comando.setString(4, clientes.getApellidom());
		comando.setString(5, clientes.getDireccion());
		comando.setString(6, clientes.getCiudad());
		comando.setString(7, clientes.getEstado());
		comando.setString(8, clientes.getPais());
		comando.setString(9, clientes.getTelefono());
		comando.setString(10, clientes.getCorreo());
		//System.out.println(comando);
	comando.execute();
		return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		finally{
			myCon.desconect();
		}
		
	}	
	
	// METODO PARA CONSULTAR
	
	/**
	 * @param consulta
	 * @return
	 */
	public ObservableList<ClientesFisicos> consultarClientes(String consulta){
		try {
			myCon.conect();
			comando = myCon.getCon().prepareStatement(consulta);
			rsClientes = comando.executeQuery();
			while(rsClientes.next()){
				ClientesFisicos c = new ClientesFisicos();
				c.setFisicosid(rsClientes.getInt("fisicosid"));
				c.setRfc(rsClientes.getString("rfc"));
				c.setNombre(rsClientes.getString("nombre"));
				c.setApellidop(rsClientes.getString("apellidop"));
				c.setApellidom(rsClientes.getString("apellidom"));
				c.setDireccion(rsClientes.getString("direccion"));
				c.setCiudad(rsClientes.getString("ciudad"));
				c.setEstado(rsClientes.getString("estado"));
				c.setPais(rsClientes.getString("pais"));
				c.setTelefono(rsClientes.getString("telefono"));
				c.setCorreo(rsClientes.getString("correo"));				
				c.setEstatus(rsClientes.getBoolean("estatus"));
				//System.out.println(c);
				//AGREGAR OBJETO A LA LISTA
				listaClientes.add(c);
								
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			myCon.desconect();
		}
		return listaClientes;
	}

//Metodo para eliminar una categoria
	public Boolean eliminar(int fisicosid ){
		
		try {
			myCon.conect();
			String operacion="update clientesfisicos set estatus='0' where fisicosid=?";
			comando=myCon.getCon().prepareStatement(operacion);
			
			comando.setInt(1, fisicosid);
			comando.execute();
			
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		finally{
			myCon.desconect();
		}
	}
	public Boolean modificar(ClientesFisicos cat){
		try {
			myCon.conect();
			String operacion ="update clientesfisicos set rfc=?, nombre=?, apellidop=?, apellidom=?, direccion=?, ciudad=?, estado=?, pais=?, telefono=?, correo=? where fisicosid=?";
			comando=myCon.getCon().prepareStatement(operacion);
			comando.setString(1, cat.getRfc());
			comando.setString(2, cat.getNombre());
			comando.setString(3, cat.getApellidop());
			comando.setString(4, cat.getApellidom());
			comando.setString(5, cat.getDireccion());
			comando.setString(6, cat.getCiudad());
			comando.setString(7, cat.getEstado());
			comando.setString(8, cat.getPais());
			comando.setString(9, cat.getTelefono());
			comando.setString(10, cat.getCorreo());
			comando.setInt(11, cat.getFisicosid());
	
			
			comando.execute();
			return true;
			} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		finally{
			myCon.desconect();
		}
	}
	public ObservableList<ClientesFisicos> llenarCBFisicos(String sql){
		try {
			myCon.conect();
			comando=myCon.getCon().prepareStatement(sql);
			rsClientes=comando.executeQuery();
			while (rsClientes.next()) {
				ClientesFisicos cm= new ClientesFisicos();
				cm.setFisicosid(rsClientes.getInt("FisicosID"));
				cm.setNombre(rsClientes.getString("nombre"));
				cm.setApellidop(rsClientes.getString("apellidoP"));
				cm.setApellidop(rsClientes.getString("apellidoM"));
				listaCFisicos.add(cm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			myCon.desconect();
		}
		return listaCFisicos;
	}
}
