package org.vishal.contactsmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vishal.contactsmanager.exceptions.ApplicationException;
import org.vishal.contactsmanager.exceptions.NotAllowedException;
import org.vishal.contactsmanager.model.Contact;
import org.vishal.contactsmanager.respository.ContactsRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.vishal.contactsmanager.Constants.*;
import static org.vishal.contactsmanager.service.ContactValidationService.isNullOrEmpty;

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
        if (isNullOrEmpty(contact.getUuid())) {
            contact.setUuid(UUID.randomUUID().toString());
        }
        if (contact.getAddress() != null &&
                (isNullOrEmpty(contact.getAddress().getUuid()) || isNullOrEmpty(contact.getUuid()))) {
            contact.getAddress().setUuid(UUID.randomUUID().toString());
        }
        return contactsRepository.save(contact);
    }

    public List<Contact> getContactByAddressCity(String city) throws ApplicationException {
        if (isNullOrEmpty(city)) throw new NotAllowedException(INVALID_CITY_NAME);
        return contactsRepository.findByAddressCity(city);
    }

    public List<Contact> getContactByAddressPostalCode(String postalCode) throws ApplicationException {
        if (isNullOrEmpty(postalCode)) throw new NotAllowedException(INVALID_POSTAL_CODE);
        return contactsRepository.findByAddressPostalCode(postalCode);
    }

    public List<Contact> getAllContacts() {
        return (List<Contact>) contactsRepository.findAll();
    }

    public Optional<Contact> getContactById(String uuid) throws ApplicationException {
        if (isNullOrEmpty(uuid)) throw new NotAllowedException(INVALID_CONTACT_UUID);
        return contactsRepository.findById(uuid);
    }

    public boolean deleteById(String uuid) throws ApplicationException {
        if (isNullOrEmpty(uuid)) throw new NotAllowedException(INVALID_CONTACT_UUID);
        contactsRepository.deleteById(uuid);
        return true;
    }

    public List<Contact> searchContactsByFullName(String fullName) throws ApplicationException {
        if (isNullOrEmpty(fullName)) throw new NotAllowedException(INVALID_FULL_NAME);
        return contactsRepository.findByFullName(fullName);
    }

    public boolean deleteAll() {
        contactsRepository.deleteAll();
        return true;
    }
}
