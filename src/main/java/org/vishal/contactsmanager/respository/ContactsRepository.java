package org.vishal.contactsmanager.respository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.vishal.contactsmanager.model.Contact;

import java.util.List;

public interface ContactsRepository extends CrudRepository<Contact, String> {

    @Query("SELECT c FROM Contact c, Address a WHERE c.uuid = a.uuid AND a.city = :city")
    List<Contact> findByAddressCity(@Param("city") String city);

    @Query("SELECT c FROM Contact c, Address a WHERE c.uuid = a.uuid AND a.postalCode = :postalCode")
    List<Contact> findByAddressPostalCode(@Param("postalCode") String postalCode);
}
