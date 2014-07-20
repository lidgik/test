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

public class ContactServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws IOException, ServletException {
		ContactService contactService = new ContactService();

		if(request.getParameter("contactId") == null){
			response.getWriter().println("Get all contacts.");

			for(Contact contact: contactService.getAllContacts()) {
				response.getWriter().println("Name:" + contact.getName());
			}

		} else{
			Contact contact = new Contact();
			contact = contactService.getContactById(request.getParameter("contactId"));
			response.getWriter().println("get contact by id:" + request.getParameter("contactId"));
			
			if(contact.getId() != null){
				response.getWriter().println("Name:" + contact.getName());
			} else {
				response.getWriter().println("Contact not found.");
			}
	
		}
	}
	
}

class ContactService {
	DatabaseManager db = new DatabaseManager();
	String sql = null;

	public List<Contact> getAllContacts(){
		List<Contact> contacts = new ArrayList();	
		sql = "select * from contact";
		db.connectAndExecuteQuery(sql);			
		contacts = db.getAllContactsFromDatabase(contacts);
		db.close();
		return contacts;
	}

	public Contact getContactById(String id){
		Contact contact = new Contact();
		sql = "select * from contact where id=" + id;	
		db.connectAndExecuteQuery(sql);
		contact = db.getContactByIdFromDatabase(contact);	
		db.close();
		return contact;
	}
	
}

class DatabaseManager {
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
	

	public List<Contact>getAllContactsFromDatabase(List<Contact> contacts){
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
	
	public void close() {
		resultSetClose();
		statementClose();
		connectionClose();
	}

	private void resultSetClose(){
		if(rs != null){
			try{
				rs.close();
			} catch(Exception e){
				// handle the error
			}
		}
	}
	
	
	private void statementClose(){
		if(stmt != null){
			try{
				stmt.close();
			} catch(Exception e){
				// handle the error
			}
		}
	}

	private void connectionClose(){
		if(connection != null){
			try{
				connection.close();
			} catch(Exception e){
				// handle the error
			}
		}
	}
}
