package org.vishal.contactsmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.vishal.contactsmanager.exceptions.ApplicationException;
import org.vishal.contactsmanager.exceptions.ResourceNotFoundException;
import org.vishal.contactsmanager.model.Contact;
import org.vishal.contactsmanager.service.ContactsService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/contact/v1")
public class ContactsController {

    private ContactsService contactsService;

    @Autowired
    public ContactsController(ContactsService contactsService) {
        this.contactsService = contactsService;
    }

    @PostMapping(value = "/contact")
    public Contact addContact(@RequestBody Contact contact) throws ApplicationException {
        return contactsService.addOrUpdateContact(contact);
    }

    @GetMapping(value = "/contacts/{city}")
    public List<Contact> getContactsByCity(@PathVariable("city") String city) {
        return contactsService.getContactByAddressCity(city);
    }

    @GetMapping(value = "/contacts/{postalCode}")
    public List<Contact> getContactsByPostalCode(@PathVariable("postalCode") String postalCode) {
        return contactsService.getContactByAddressPostalCode(postalCode);
    }

    @GetMapping(value = "/contacts")
    public List<Contact> getAllContacts() {
        return contactsService.getAllContacts();
    }

    @GetMapping(value = "/contact/{uuid}")
    public Contact getContactByUuid(@PathVariable("uuid") String uuid) throws ResourceNotFoundException {
        return contactsService
                .getContactById(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found on :: " + uuid));
    }

    @DeleteMapping(value = "/contact/{uuid}")
    public Map<String, Boolean> deleteContact(@PathVariable("uuid") String uuid) throws ResourceNotFoundException {
        contactsService
                .getContactById(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found on :: " + uuid));

        Boolean isDeleted = contactsService.deleteById(uuid);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", isDeleted);
        return response;
    }
}
