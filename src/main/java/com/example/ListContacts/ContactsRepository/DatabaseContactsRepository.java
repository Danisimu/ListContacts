package com.example.ListContacts.ContactsRepository;

import com.example.ListContacts.Contact;
import com.example.ListContacts.ContactsRepository.mapper.ContactsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.ArgumentPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Primary
@RequiredArgsConstructor
public class DatabaseContactsRepository implements ContactsRepository{

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Contact> findAll() {
        String sql = "SELECT * FROM contacts";
        return jdbcTemplate.query(sql, new ContactsMapper());
    }

    @Override
    public Optional<Contact> findById(Long id) {
        String sql = "SELECT * FROM contacts WHERE id = ?";
        Contact contact = DataAccessUtils.singleResult(
        jdbcTemplate.query(sql,
                new ArgumentPreparedStatementSetter(new Object[]{id}),
                new RowMapperResultSetExtractor<>(new ContactsMapper(), 1)));
        return Optional.ofNullable(contact);
    }

    @Override
    public Contact save(Contact contact) {
        String sql = "INSERT INTO contacts (firstName, lastName, email, phoneNumber, id) VALUES " +
                "(?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,contact.getFirstName(),
                contact.getLastName(),
                contact.getEmail(),
                contact.getPhoneNumber(),
                contact.getId());
        return contact;
    }

    @Override
    public Contact update(Contact contact) {
       String sql = "UPDATE contacts SET firstName = ?, lastName = ?, email = ?," +
               "phoneNumber = ? WHERE id = ?";
       jdbcTemplate.update(sql,
               contact.getFirstName(),
               contact.getLastName(),
               contact.getEmail(),
               contact.getPhoneNumber(),
               contact.getId());
       return  contact;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM contacts WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
