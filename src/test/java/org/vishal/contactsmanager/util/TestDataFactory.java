package org.vishal.contactsmanager.util;

import org.vishal.contactsmanager.model.Address;
import org.vishal.contactsmanager.model.Contact;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestDataFactory {

    public static final String FULL_NAME = "FULL NAME";
    public static final String CITY = "NEW YORK";
    public static final String POSTAL_CODE = "10005";

    public static Contact createContact() {
        Address address = new Address(UUID.randomUUID().toString(), CITY, POSTAL_CODE);
        return new Contact(UUID.randomUUID().toString(), FULL_NAME, System.currentTimeMillis(), address);
    }

    public static List<Contact> createContacts() {
        List<Contact> contacts = new ArrayList<>();
        contacts.add(createContact());
        return contacts;
    }
}
