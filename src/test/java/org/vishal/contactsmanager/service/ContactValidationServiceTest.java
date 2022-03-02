package org.vishal.contactsmanager.service;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.vishal.contactsmanager.exceptions.NotAllowedException;
import org.vishal.contactsmanager.model.Address;
import org.vishal.contactsmanager.model.Contact;
import org.vishal.contactsmanager.respository.ContactsRepository;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ContactValidationServiceTest implements WithAssertions {

    private ContactValidationService service;

    @BeforeEach
    void setUp() {
        service = new ContactValidationService();
    }

    @Test
    void givenContact_whenContactIsNull_thenThrowNotAllowedException() {
        assertForNotAllowedException(null, "required");
    }

    @Test
    void givenContact_whenContactFullNameIsNull_thenThrowNotAllowedException() {
        Contact contact = new Contact();
        contact.setFullName(null);

        assertForNotAllowedException(contact, "full name not provided");
    }

    @Test
    void givenContact_whenContactFullNameIsEmpty_thenThrowNotAllowedException() {
        Contact contact = new Contact();
        contact.setFullName("");

        assertForNotAllowedException(contact, "full name not provided");
    }

    @Test
    void givenContact_whenContactAddressIsNull_thenThrowNotAllowedException() {
        Contact contact = new Contact();
        contact.setFullName("BIG BOY");
        contact.setAddress(null);

        assertForNotAllowedException(contact, "postal code not provided");
    }

    @Test
    void givenContact_whenContactAddressPostalCodeIsNull_thenThrowNotAllowedException() {
        Contact contact = createContactWithAddress("BIG BOY", null);
        assertForNotAllowedException(contact, "postal code not provided");
    }

    @Test
    void givenContact_whenContactAddressPostalCodeIsEmpty_thenThrowNotAllowedException() {
        Contact contact = createContactWithAddress("BIG BOY", "");
        assertForNotAllowedException(contact, "postal code not provided");
    }

    private Contact createContactWithAddress(String fullName, String postalCode) {
        Contact contact = new Contact();
        contact.setFullName(fullName);
        Address address = new Address();
        address.setPostalCode(postalCode);
        contact.setAddress(address);

        return contact;
    }

    private void assertForNotAllowedException(Contact contact, String message) {
        Exception exception = assertThrows(NotAllowedException.class,
                () -> service.validateContact(contact));
        assertThat(exception.getMessage()).contains(message);
    }
}