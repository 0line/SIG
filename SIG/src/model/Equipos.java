package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Equipos {
	private int id_equipos,moral_id,fisicos_id,estatus_equipo;
	private String tipo,modelo,nserie,marca,descripcion_equipo,fecha_registro;
	private ConnectionBD myCon;
	private PreparedStatement ps;
	private ResultSet rsEquipos;
	private ObservableList<Equipos> listEquipos;
	
	//Constructor por defecto
	public Equipos() {
		this.id_equipos=0;
		this.moral_id=0;
		this.fisicos_id=0;
		this.estatus_equipo=1;
		myCon= new ConnectionBD();
		listEquipos=FXCollections.observableArrayList();
	}

	public Equipos(int id_equipos, String tipo, String modelo,
			String nserie, String marca, String descripcion_equipo,
			String fecha_registro,int moral_id, int fisicos_id,  int estatus_equipo) 
	{
		this.id_equipos = id_equipos;
		this.moral_id = moral_id;
		this.fisicos_id = fisicos_id;
		this.estatus_equipo = estatus_equipo;
		this.tipo = tipo;
		this.modelo = modelo;
		this.nserie = nserie;
		this.marca = marca;
		this.descripcion_equipo = descripcion_equipo;
		this.fecha_registro = fecha_registro;
	}
	

	//Getters
	public int getId_equipos() {
		return id_equipos;
	}

	public int getMoral_id() {
		return moral_id;
	}

	public int getFisicos_id() {
		return fisicos_id;
	}

	public int getEstatus_equipo() {
		return estatus_equipo;
	}

	public String getTipo() {
		return tipo;
	}

	public String getModelo() {
		return modelo;
	}

	public String getNserie() {
		return nserie;
	}

	public String getMarca() {
		return marca;
	}

	public String getDescripcion_equipo() {
		return descripcion_equipo;
	}

	public String getFecha_registro() {
		return fecha_registro;
	}
	
	//Setters
	public void setId_equipos(int id_equipos) {
		this.id_equipos = id_equipos;
	}

	public void setMoral_id(int moral_id) {
		this.moral_id = moral_id;
	}

	public void setFisicos_id(int fisicos_id) {
		this.fisicos_id = fisicos_id;
	}

	public void setEstatus_equipo(int estatus_equipo) {
		this.estatus_equipo = estatus_equipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public void setNserie(String nserie) {
		this.nserie = nserie;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public void setDescripcion_equipo(String descripcion_equipo) {
		this.descripcion_equipo = descripcion_equipo;
	}

	public void setFecha_registro(String fecha_registro) {
		this.fecha_registro = fecha_registro;
	}
	
	//Método para llenar la tabla
		public ObservableList<Equipos> llenarTabla (String sql){
			try {
				myCon.conect();
				ps=myCon.getCon().prepareStatement(sql);
				rsEquipos=ps.executeQuery();
				while (rsEquipos.next()) {
					Equipos e=new Equipos();
					e.setId_equipos(rsEquipos.getInt("id_equipos"));
					e.setTipo(rsEquipos.getString("tipo"));
					e.setModelo(rsEquipos.getString("modelo"));
					e.setNserie(rsEquipos.getString("nserie"));
					e.setMarca(rsEquipos.getString("marca"));
					e.setDescripcion_equipo(rsEquipos.getString("descripcion_equipo"));
					e.setFecha_registro(rsEquipos.getString("fecha_registro"));
					e.setMoral_id(rsEquipos.getInt("moral_id"));
					e.setFisicos_id(rsEquipos.getInt("fisico_id"));
					e.setEstatus_equipo(rsEquipos.getInt("estatus_equipo"));
					
					//Agregar objeto a la lista
					listEquipos.add(e);				
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				myCon.desconect();
			}
			return listEquipos;
		}
	
	//Métodos para CRUD en BD
	
	
	
	//Método para insertar
	public Boolean insertar (Equipos equipo){
		try {
			myCon.conect();
			String sql="Insert into equipos (id_equipos,tipo,modelo,nserie,marca,descripcion_equipo,fecha_registro,moral_id,fisico_id,estatus_equipo)"
					+ " values (default,?,?,?,?,?,?,?,?,default)";
			ps=myCon.getCon().prepareStatement(sql);
			ps.setString(1, equipo.getTipo());
			ps.setString(2, equipo.getModelo());
			ps.setString(3, equipo.getNserie());
			ps.setString(4, equipo.getMarca());
			ps.setString(5, equipo.getDescripcion_equipo());
			ps.setString(6, equipo.getFecha_registro());
			ps.setInt(7, equipo.getMoral_id());
			ps.setInt(8, equipo.getFisicos_id());
			ps.execute();
			System.out.println(ps);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		finally {
			myCon.desconect();
		}
	}
	
	//Método para actualizar datos
	public Boolean update (Equipos equipo){
		try {
			myCon.conect();
			String sql="update equipos set tipo=?,modelo=?,nserie=?,marca=?,descripcion_equipo=?,fecha_registro=?,moral_id=?,fisico_id=? where id_equipos=?";
			ps=myCon.getCon().prepareStatement(sql);
			ps.setString(1, equipo.getTipo());
			ps.setString(2, equipo.getModelo());
			ps.setString(3, equipo.getNserie());
			ps.setString(4, equipo.getMarca());
			ps.setString(5, equipo.getDescripcion_equipo());
			ps.setString(6, equipo.getFecha_registro());
			ps.setInt(7, equipo.getMoral_id());
			ps.setInt(8, equipo.getFisicos_id());
			ps.setInt(9, equipo.getId_equipos());
			System.out.println(ps);
			ps.execute();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally {
			myCon.desconect();
		}
	}
	//Método para eliminar
	public Boolean delete(int equipo) {
		try {
			myCon.conect();
			String sql="update equipos set estatus_equipo='0'  where id_equipos=?";
			ps=myCon.getCon().prepareStatement(sql);
			ps.setInt(1,equipo);
			ps.execute();
			return true;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		finally {
			myCon.desconect();
		}
	}
	
}
