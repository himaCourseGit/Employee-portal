package com.acintyo.generators;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeIdGenerator {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private DataSource ds;
	
	private static final String prefix="ACIN";
	
	public  String employeeSequence()
	{
	
		try {
			Connection connection = ds.getConnection();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select counter from AcintyoDB.emp_id_generator where id='emp_id'");
			if(rs.next())
			{
				int counter = rs.getInt(1)+1;
				statement.executeUpdate("update AcintyoDB.emp_id_generator set counter="+counter+" where id='emp_id'");
				
			return prefix+String.format("%4d", counter);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
