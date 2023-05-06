package br.com.rosivan.services;

import br.com.rosivan.exceptions.ResourceNotFoundException;
import br.com.rosivan.model.Person;
import br.com.rosivan.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    public List<Person> findAll(){
        logger.info("Finding all!");
        return repository.findAll();
    }

    public Person findById(Long id) {
        logger.info("Finding one person!");
        return repository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException(("No records found for this ID"))
        );
    }

    public Person create(Person person) {
        logger.info("Creating one person!");
        return repository.save(person);
    }

    public Person update(Person person) {
        logger.info("Updating a person!");
        var entity = repository.findById(person.getId()).orElseThrow(
            () -> new ResourceNotFoundException(("No records found for this ID"))
        );
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        return repository.save(person);
    }
    public void delete(Long id) {
        logger.info("Deleting a person!");
        var entity = repository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException(("No records found for this ID"))
        );
        repository.delete(entity);
    }

    /*  private Person mockPerson(int i) {
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Person name: " + i);
        person.setLastName("Last Name: " + i);
        person.setAddress("Same Address: " + i);
        person.setGender("Male");

        return person;
    } */

}
