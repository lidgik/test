package com.bodejidi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.List;
import java.util.ArrayList;

public class DatabaseManager {
	Connection connection = null;
	Statement stmt = null;
	ResultSet rs = null;

	public Contact getContactFromResultSet(ResultSet rs)
		throws SQLException {
		Contact contact = new Contact();

		contact.setId(rs.getInt("id"));
		contact.setName(rs.getString("name"));
		contact.setMobile(rs.getString("mobile"));
		contact.setVpmn(rs.getString("vpmn"));
		contact.setEmail(rs.getString("email"));
		contact.setHomeAddress(rs.getString("home_address"));
		contact.setOfficeAddress(rs.getString("office_address"));
		contact.setMemo(rs.getString("memo"));
		contact.setJob(rs.getString("job"));
		contact.setJobLevel(rs.getInt("job_level"));

		return contact;
	}

	public void connectAndExecuteQuery(String sql) {
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch(Exception e){
				// handle the error
		}

		try{
			connection = DriverManager.getConnection("jdbc:mysql://localhost/test?" + "user=root" + "&password=");
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
		} catch(SQLException sqle){
			sqle.printStackTrace();
		}
	}
	

	public List<Contact> getAllContactsFromDatabase(List<Contact> contacts){
		try{
			if(rs != null){
				while(rs.next()){
					contacts.add(getContactFromResultSet(rs));
				}
			}
		} catch(SQLException sqle){
			sqle.printStackTrace();
		}
		return contacts;
	}

	public Contact getContactByIdFromDatabase(Contact contact){
		try{
			if(rs.next()){
				contact = getContactFromResultSet(rs);	
			}
		} catch(SQLException sqle){
			sqle.printStackTrace();
		}
		return contact;
	}

	public void close(){
		if(rs != null){
			try{
				rs.close();
			} catch(SQLException sqle){
				// handle the error
			}
		}

		if(stmt != null){
			try{
				stmt.close();
			} catch(SQLException sqle){
				// handle the error
			}
		}

		if(connection != null){
			try{
				connection.close();
			} catch(SQLException sqle){
				// handle the error
			}
		}

	}
}
