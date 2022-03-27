package org.vishal.contactsmanager.service;

import org.springframework.stereotype.Service;
import org.vishal.contactsmanager.exceptions.ApplicationException;
import org.vishal.contactsmanager.exceptions.NotAllowedException;
import org.vishal.contactsmanager.model.Contact;

@Service
public class ContactValidationService {

    public void validateContact(Contact contact) throws ApplicationException {
        if (contact == null) {
            throw new NotAllowedException("Contact required");
        }

        if (contact.getFullName() == null || contact.getFullName().isEmpty()) {
            throw new NotAllowedException("Contact full name not provided");
        }

        if ((contact.getAddress() == null) ||
            (contact.getAddress().getPostalCode() == null) ||
            (contact.getAddress().getPostalCode().isEmpty())) {
                throw new NotAllowedException("Contact postal code not provided");
        }
    }
}
