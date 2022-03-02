package org.vishal.contactsmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vishal.contactsmanager.exceptions.ApplicationException;
import org.vishal.contactsmanager.model.Contact;
import org.vishal.contactsmanager.respository.ContactsRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ContactsService {

    private final ContactsRepository contactsRepository;
    private final ContactValidationService validationService;

    @Autowired
    public ContactsService(ContactsRepository contactsRepository, ContactValidationService validationService) {
        this.contactsRepository = contactsRepository;
        this.validationService = validationService;
    }

    public Contact addOrUpdateContact(Contact contact) throws ApplicationException {
        validationService.validateContact(contact);
        if (contact.getUuid() == null || contact.getUuid().isEmpty()){
            contact.setUuid(UUID.randomUUID().toString());
        }
        if (contact.getAddress() != null && (contact.getAddress().getUuid() == null || contact.getUuid().isEmpty())) {
            contact.getAddress().setUuid(UUID.randomUUID().toString());
        }

        return contactsRepository.save(contact);
    }

    public List<Contact> getContactByPostalCode(String postalCode) {
        return contactsRepository.getContactsByAddress_PostalCode(postalCode);
    }

    public List<Contact> getAllContacts() {
        return (List<Contact>) contactsRepository.findAll();
    }

    public Optional<Contact> getContactById(String uuid) {
        return contactsRepository.findById(uuid);
    }

    public Boolean deleteById(String uuid) {
        contactsRepository.deleteById(uuid);
        return true;
    }
}
