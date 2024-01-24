package com.example.ListContacts.ContactsService;

import com.example.ListContacts.Contact;
import com.example.ListContacts.ContactsRepository.ContactsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ContactsServiceImpl implements ContactsService{

    private final ContactsRepository contactsRepository;
    @Override
    public List<Contact> findAll() {
        return contactsRepository.findAll();
    }

    @Override
    public Contact findById(Long id) {
        return contactsRepository.findById(id).orElse(null);
    }

    @Override
    public Contact save(Contact contact) {
        return contactsRepository.save(contact);
    }

    @Override
    public Contact update(Contact contact) {
        return contactsRepository.update(contact);
    }

    @Override
    public void deleteById(Long id) {
        contactsRepository.deleteById(id);
    }
}
