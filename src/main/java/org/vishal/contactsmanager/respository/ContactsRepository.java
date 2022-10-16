package org.vishal.contactsmanager.respository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.vishal.contactsmanager.model.Contact;

import java.util.List;

public interface ContactsRepository extends CrudRepository<Contact, String> {

    List<Contact> findByAddressCity(String city);

    List<Contact> findByAddressPostalCode(String postalCode);

    List<Contact> findByFullName(String fullName);

}
