package org.vishal.contactsmanager.service;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.vishal.contactsmanager.exceptions.ApplicationException;
import org.vishal.contactsmanager.model.Address;
import org.vishal.contactsmanager.model.Contact;
import org.vishal.contactsmanager.respository.ContactsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ContactsServiceTest implements WithAssertions {

    private static final String RANDOM_UUID = UUID.randomUUID().toString();

    @Mock
    private ContactsRepository repository;

    @Mock
    private ContactValidationService validation;

    @InjectMocks
    private ContactsService underTest;

    @BeforeEach
    void setUp() {

    }

    @Test
    void givenContact_whenAddOrUpdate_thenReturnsContact() throws ApplicationException {
        Contact contact = createContact();
        when(repository.save(any(Contact.class))).thenReturn(contact);
        Contact createdContact = underTest.addOrUpdateContact(contact);

        assertThat(createdContact).isNotNull();
        verify(validation).validateContact(contact);
    }

    @Test
    void givenContacts_whenGetContactsByAddressCity_thenReturnsContacts() {
        List<Contact> contacts = createContacts();
        when(repository.findByAddressCity(anyString())).thenReturn(contacts);
        List<Contact> foundedContacts = underTest.getContactByAddressCity("NEW YORK");

        assertThat(foundedContacts).isNotEmpty();
    }

    @Test
    void givenContacts_whenGetContactsByAddressPostalCode_thenReturnsContacts() {
        List<Contact> contacts = createContacts();
        when(repository.findByAddressPostalCode(anyString())).thenReturn(contacts);
        List<Contact> foundedContacts = underTest.getContactByAddressPostalCode("10005");

        assertThat(foundedContacts).isNotEmpty();
    }

    @Test
    void givenContacts_whenGetAllContacts_thenReturnsContacts() {
        List<Contact> contacts = createContacts();
        when(repository.findAll()).thenReturn(contacts);
        List<Contact> foundedContacts = underTest.getAllContacts();

        assertThat(foundedContacts).isNotEmpty();
    }

    @Test
    void givenContact_whenGetContactById_thenReturnsContact() {
        Contact contact = createContact();
        when(repository.findById(anyString())).thenReturn(Optional.of(contact));
        Optional<Contact> foundedContact = underTest.getContactById(RANDOM_UUID);

        assertThat(foundedContact).isPresent();
    }

    @Test
    void givenContact_whenDeleteById_thenReturnsTrue() {
        boolean contactIsDeleted = underTest.deleteById(RANDOM_UUID);
        assertThat(contactIsDeleted).isTrue();
        verify(repository).deleteById(RANDOM_UUID);
    }

    private Contact createContact() {
        Address address = new Address(UUID.randomUUID().toString(), "NEW YORK", "10005");
        return new Contact(UUID.randomUUID().toString(), "FULL NAME", System.currentTimeMillis(), address);
    }

    private List<Contact> createContacts() {
        List<Contact> contacts = new ArrayList<>();
        contacts.add(createContact());
        return contacts;
    }
}