package org.vishal.contactsmanager.respository;

import org.springframework.data.repository.CrudRepository;
import org.vishal.contactsmanager.model.Contact;

import java.util.List;

public interface ContactsRepository extends CrudRepository<Contact, String> {

    List<Contact> getContactsByAddress_PostalCode(String address_postalCode);

}
