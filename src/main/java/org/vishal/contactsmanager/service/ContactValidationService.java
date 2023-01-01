package org.vishal.contactsmanager.service;

import org.springframework.stereotype.Service;
import org.vishal.contactsmanager.exceptions.ApplicationException;
import org.vishal.contactsmanager.exceptions.NotAllowedException;
import org.vishal.contactsmanager.model.Contact;

import static org.vishal.contactsmanager.Constants.*;

@Service
public class ContactValidationService {

    public void validateContact(Contact contact) throws ApplicationException {
        if (contact == null) {
            throw new NotAllowedException(CONTACT_IS_REQUIRED);
        }
        if (isNullOrEmpty(contact.getFullName())) {
            throw new NotAllowedException(INVALID_FULL_NAME);
        }
        if (isNullOrEmpty(contact.getMobileNumber())) {
            throw new NotAllowedException(INVALID_MOBILE_NUMBER);
        }
        if ((contact.getAddress() == null)) {
            throw new NotAllowedException(INVALID_ADDRESS);
        }else {
            if (isNullOrEmpty(contact.getAddress().getPostalCode())) {
                throw new NotAllowedException(INVALID_POSTAL_CODE);
            }
        }
    }

    public static Boolean isNullOrEmpty(String field) {
        return field == null || field.isEmpty();
    }
}
