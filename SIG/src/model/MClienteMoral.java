package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MClienteMoral {
	private int moralesid;
	private String razonsocial,nombre,rfc,direccion,ciudad,estado,pais,teleno,correo;
	private Boolean estatus;
	private ObservableList<MClienteMoral> listCMorales;
	private ConnectionBD myCon;
	private PreparedStatement ps;
	private ResultSet rsCMoral;
	
	//Constructor por default
	public MClienteMoral() {
		this.moralesid = 0;
		this.razonsocial = "";
		this.nombre = "";
		this.rfc = "";
		this.direccion = "";
		this.ciudad = "";
		this.estado = "";
		this.pais = "";
		this.teleno = "";
		this.correo = "";
		this.estatus = true;
		listCMorales=FXCollections.observableArrayList();
		myCon=new ConnectionBD();
	}
	
	//Constructor recibiendo datos
	public MClienteMoral(int moralesid, String razonsocial, String nombre, String rfc, String direccion, String ciudad,
			String estado, String pais, String teleno, String correo, Boolean estatus) {
		super();
		this.moralesid = moralesid;
		this.razonsocial = razonsocial;
		this.nombre = nombre;
		this.rfc = rfc;
		this.direccion = direccion;
		this.ciudad = ciudad;
		this.estado = estado;
		this.pais = pais;
		this.teleno = teleno;
		this.correo = correo;
		this.estatus = estatus;
	}
	
	

	public int getMoralesid() {
		return moralesid;
	}

	public String getRazonsocial() {
		return razonsocial;
	}

	public String getNombre() {
		return nombre;
	}

	public String getRfc() {
		return rfc;
	}

	public String getDireccion() {
		return direccion;
	}

	public String getCiudad() {
		return ciudad;
	}

	public String getEstado() {
		return estado;
	}

	public String getPais() {
		return pais;
	}

	public String getTeleno() {
		return teleno;
	}

	public String getCorreo() {
		return correo;
	}

	public Boolean getEstatus() {
		return estatus;
	}

	public void setMoralesid(int moralesid) {
		this.moralesid = moralesid;
	}

	public void setRazonsocial(String razonsocial) {
		this.razonsocial = razonsocial;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public void setTeleno(String teleno) {
		this.teleno = teleno;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}
	
	public ObservableList<MClienteMoral> colsultarCMorales(String sql){
		try {
			myCon.conect();
			ps=myCon.getCon().prepareStatement(sql);
			rsCMoral=ps.executeQuery();
			while (rsCMoral.next()) {
				MClienteMoral cm= new MClienteMoral();
				cm.setMoralesid(rsCMoral.getInt("moralesid"));
				cm.setRazonsocial(rsCMoral.getString("razonsocial"));
				listCMorales.add(cm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			myCon.desconect();
		}
		return listCMorales;
	}
	
}
