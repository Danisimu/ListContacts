package com.example.ListContacts.ContactsRepository;

import com.example.ListContacts.Contact;

import java.util.List;
import java.util.Optional;

public interface ContactsRepository {

        List<Contact> findAll();

        Optional<Contact> findById(Long id);

        Contact save(Contact contact);

        Contact update(Contact contact);

        void deleteById(Long id);
}
