package org.vishal.contactsmanager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vishal.contactsmanager.exceptions.ResourceNotFoundException;
import org.vishal.contactsmanager.model.Contact;
import org.vishal.contactsmanager.service.ContactsService;
import org.vishal.contactsmanager.service.providers.ResponseHandler;

import java.util.List;

import static org.vishal.contactsmanager.Constants.CONTACT_NOT_FOUND;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/contact/v1")
public class ContactsController {

    private final ContactsService contactsService;

    private static final Logger log = LoggerFactory.getLogger(ContactsService.class);

    @Autowired
    public ContactsController(ContactsService contactsService) {
        this.contactsService = contactsService;
    }

    @PostMapping(value = "/contact")
    public ResponseEntity<Object> addContact(@RequestBody Contact contact) {
        try {
            Contact addedContact = contactsService.addOrUpdateContact(contact);
            return ResponseHandler.response(addedContact, HttpStatus.OK);
        } catch (Exception e) {
            log.info("Error while add new contact, msg " + e.getMessage());
            return ResponseHandler.error(e.getMessage(), HttpStatus.MULTI_STATUS);
        }
    }

    @GetMapping(value = "/contacts/city/{city}")
    public ResponseEntity<Object> getContactsByCity(@PathVariable("city") String city) {
        try {
            List<Contact> contacts = contactsService.getContactByAddressCity(city);
            return ResponseHandler.response(contacts, HttpStatus.OK);
        } catch (Exception e) {
            log.info("Error while get contacts by city, msg " + e.getMessage());
            return ResponseHandler.error(e.getMessage(), HttpStatus.MULTI_STATUS);
        }
    }

    @GetMapping(value = "/contacts/{postalCode}")
    public ResponseEntity<Object> getContactsByPostalCode(@PathVariable("postalCode") String postalCode) {
        try {
            List<Contact> contacts = contactsService.getContactByAddressPostalCode(postalCode);
            return ResponseHandler.response(contacts, HttpStatus.OK);
        } catch (Exception e) {
            log.info("Error while get contacts by postal code, msg " + e.getMessage());
            return ResponseHandler.error(e.getMessage(), HttpStatus.MULTI_STATUS);
        }
    }

    @GetMapping(value = "/contacts")
    public ResponseEntity<Object> getAllContacts() {
        try {
            List<Contact> contacts = contactsService.getAllContacts();
            log.info("contacts : " + contacts.size());
            return ResponseHandler.response(contacts, HttpStatus.OK);
        } catch (Exception e) {
            log.info("Error while get all contacts, msg " + e.getMessage());
            return ResponseHandler.error(e.getMessage(), HttpStatus.MULTI_STATUS);
        }
    }

    @GetMapping(value = "/contact/{uuid}")
    public ResponseEntity<Object> getContactByUuid(@PathVariable("uuid") String uuid) {
        try {
            Contact contactByUuid = contactsService.getContactById(uuid)
                    .orElseThrow(() -> new ResourceNotFoundException(CONTACT_NOT_FOUND));
            return ResponseHandler.response(contactByUuid, HttpStatus.OK);
        } catch (Exception e) {
            log.info("Error while get contact by uuid " + uuid + ", msg " + e.getMessage());
            return ResponseHandler.error(e.getMessage(), HttpStatus.MULTI_STATUS);
        }
    }

    @DeleteMapping(value = "/contact/{uuid}")
    public ResponseEntity<Object> deleteContact(@PathVariable("uuid") String uuid) {
        try {
            contactsService.getContactById(uuid)
                    .orElseThrow(() -> new ResourceNotFoundException(CONTACT_NOT_FOUND));
            return ResponseHandler.response(contactsService.deleteById(uuid), HttpStatus.OK);
        } catch (Exception e) {
            log.info("Error while delete contact by uuid " + uuid + ", msg " + e.getMessage());
            return ResponseHandler.error(e.getMessage(), HttpStatus.MULTI_STATUS);
        }
    }

    @GetMapping(value = "/contacts/name/{fullName}")
    public ResponseEntity<Object> searchContactsByFullName(@PathVariable("fullName") String fullName) {
        try {
            List<Contact> contacts = contactsService.searchContactsByFullName(fullName);
            log.info("Found contacts size :" + contacts.size() + " by fullName :" + fullName);
            return ResponseHandler.response(contacts, HttpStatus.OK);
        } catch (Exception e) {
            log.info("Error while search contacts by fullName, msg " + e.getMessage());
            return ResponseHandler.error(e.getMessage(), HttpStatus.MULTI_STATUS);
        }
    }

    @DeleteMapping(value = "/contacts/all")
    public ResponseEntity<Object> deleteAllContacts() {
        try {
            return ResponseHandler.response(contactsService.deleteAll(), HttpStatus.OK);
        } catch (Exception e) {
            log.info("Error while deleting all contacts, msg " + e.getMessage());
            return ResponseHandler.error(e.getMessage(), HttpStatus.MULTI_STATUS);
        }
    }
}
