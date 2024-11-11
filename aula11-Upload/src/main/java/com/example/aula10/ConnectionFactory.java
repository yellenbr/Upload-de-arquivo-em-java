/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.aula10;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public String driver = "com.mysql.jdbc.Driver";
	public String url = "jdbc:mysql://localhost/exemplo";
	public String usuario = "root";
	public String senha = "Odeioisso2608";
	
	public Connection getConnectionFactory () throws SQLException {
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection
					(url,usuario,senha);
			return conn;
		} catch (ClassNotFoundException e) {
			throw new SQLException();
		}
	}
}
