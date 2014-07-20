package com.bodejidi;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

