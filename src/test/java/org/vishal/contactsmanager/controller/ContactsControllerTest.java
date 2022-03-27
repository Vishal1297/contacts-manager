package org.vishal.contactsmanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.vishal.contactsmanager.model.Contact;
import org.vishal.contactsmanager.service.ContactsService;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.vishal.contactsmanager.util.TestDataFactory.createContact;
import static org.vishal.contactsmanager.util.TestDataFactory.createContacts;

@WebMvcTest(ContactsController.class)
class ContactsControllerTest implements WithAssertions {

    private static final String BASE_URI = "/contact/v1";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    private ContactsService service;

    @BeforeEach
    void setUp() {

    }

    @Test
    void givenContact_whenAddContact_thenReturns_200_OK() throws Exception {
        Contact contactToAdd = createContact();
        Contact addedContact = contactToAdd;
        Contact contactRequest = createContact();

        when(service.addOrUpdateContact(contactToAdd)).thenReturn(addedContact);

        mockMvc.perform(post(BASE_URI + "/contact")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(contactRequest)))
                .andExpect(status().isOk());
    }

    @Test
    void givenContact_whenGetAllContacts_thenReturns_200_OK() throws Exception {
        List<Contact> foundContacts = createContacts();
        when(service.getAllContacts()).thenReturn(foundContacts);

        mockMvc.perform(get(BASE_URI + "/contacts")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}