package org.vishal.contactsmanager.controller;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.vishal.contactsmanager.service.ContactsService;

@WebMvcTest(ContactsController.class)
class ContactsControllerTest implements WithAssertions {

    @Mock
    private ContactsService service;

    @BeforeEach
    void setUp() {

    }


}