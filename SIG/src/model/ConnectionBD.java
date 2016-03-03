package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionBD {
	private String bd;
	private String server;
	private String user;
	private String password;
	private Connection con;
	
	public ConnectionBD(){
		bd="sig";
		user="postgres";
		password="Lion$2015$";
		server="jdbc:postgresql://localhost:5432/";
		con=null;
	}

	public Boolean conect(){
		Boolean result=false;
		try {
			Class.forName("org.postgresql.Driver");
			con=DriverManager.getConnection(server+bd,user,password);
			result=true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	public Boolean desconect(){
		try {
			con.close();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	
	//Setters
	public void setUser(String user) {
		this.user = user;
	}
	public void setServer(String server) {
		this.server = server;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setBd(String bd) {
		this.bd = bd;
	}
	//Getters
	public Connection getCon() {
		return con;
	}	
}
