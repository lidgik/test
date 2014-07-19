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

public class ContactServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws IOException, ServletException {
		response.getWriter().println("get contact by id:" + request.getParameter("contactId"));
		
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch(Exception e){
			// handle the error
		}
		
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;

		try{
			connection = DriverManager.getConnection("jdbc:mysql://localhost/test?" + "user=root" + "&password=");
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from contact where id=1");
			rs.next();
			response.getWriter().println(rs.getString("name"));
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
	}
	
}