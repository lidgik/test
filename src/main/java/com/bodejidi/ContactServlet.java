package com.bodejidi;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class ContactServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws IOException, ServletException {
	    Integer id = null;
		String name = null;
	
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		if(request.getParameter("contactId") == null){
			response.getWriter().println("Get all contacts.");

			for(Object obj: getAllContacts()){	
				Map contact = (Map) obj;

				response.getWriter().println("Name:" + contact.get("name"));
			}

		} else{
			Map contact = new HashMap();
			contact = getContactById(request.getParameter("contactId"));
			response.getWriter().println("get contact by id:" + request.getParameter("contactId"));
			
			if(contact.get("id") != null){
				response.getWriter().println("Name:" + contact.get("name"));
			} else {
				response.getWriter().println("Contact not found.");
			}
	
		}
	}
	

	public List getAllContacts(){	
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;

		List contacts = new ArrayList();

		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch(Exception e){
			// handle the error
		}

		try{
			connection = DriverManager.getConnection("jdbc:mysql://localhost/test?" + "user=root" + "&password=");
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from contact");
			while(rs.next()){
				Map contact = new HashMap();

				contact.put("name", rs.getString("name"));

				contacts.add(contact);
			}
		} catch(SQLException sqle){
			sqle.printStackTrace();
		}

		if(rs != null){
			try{
				rs.close();
			} catch(Exception e){
				// handle the error
			}
		}
		
		if(stmt != null){
			try{
				stmt.close();
			} catch(Exception e){
				// handle the error
			}
		}
		
		if(connection != null){
			try{
				connection.close();
			} catch(Exception e){
				// handle the error
			}
		}	
		return contacts;
	}

	public Map getContactById(String id){
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		Map contact = new HashMap();
			
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch(Exception e){
				// handle the error
		}

		try{
			connection = DriverManager.getConnection("jdbc:mysql://localhost/test?" + "user=root" + "&password=");
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from contact where id=" + id);
			if(rs.next()){
				contact.put("id", rs.getInt("id"));
				contact.put("name", rs.getString("name"));
			} 
		} catch(SQLException sqle){
			sqle.printStackTrace();
		}

		if(rs != null){
			try{
				rs.close();
			} catch(Exception e){
				// handle the error
			}
		}
		
		if(stmt != null){
			try{
				stmt.close();
			} catch(Exception e){
				// handle the error
			}
		}
		
		if(connection != null){
			try{
				connection.close();
			} catch(Exception e){
				// handle the error
			}
		}
		return contact;
	}
}
