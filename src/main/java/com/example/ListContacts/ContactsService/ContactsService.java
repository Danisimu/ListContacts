package com.example.ListContacts.ContactsService;

import com.example.ListContacts.Contact;
import java.util.List;


public interface ContactsService {
    List<Contact> findAll();

    Contact findById(Long id);

    Contact save(Contact contact);

    Contact update(Contact contact);

    void deleteById(Long id);
}
