package com.bodejidi;

import java.util.List;
import java.util.ArrayList;

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


