package com.example.SpringAOP.service;

import com.example.SpringAOP.exception.PersonNotFoundException;
import com.example.SpringAOP.model.Person;
import com.example.SpringAOP.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;
    @Transactional
    public Person createPerson(Person person) {
        return personRepository.save(person);
    }

    public Optional<Person> getPerson(Long id) {
        return personRepository.findById(id);
    }

    public Iterable<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public Person updatePerson(Long id, Person personDetails) {
        Person person = personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException());
        person.setName(personDetails.getName());
        person.setAge(personDetails.getAge());
        return personRepository.save(person);
    }

    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }

}
