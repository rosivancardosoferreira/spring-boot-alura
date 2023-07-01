package br.com.rosivan.services;

import br.com.rosivan.controllers.PersonController;
import br.com.rosivan.data.vo.v1.PersonVO;
import br.com.rosivan.data.vo.v2.PersonVOV2;
import br.com.rosivan.exceptions.ResourceNotFoundException;
import br.com.rosivan.mapper.DozerMapper;
import br.com.rosivan.mapper.custom.PersonMapper;
import br.com.rosivan.model.Person;
import br.com.rosivan.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonMapper mapper;

    public List<PersonVO> findAll() {
        logger.info("Finding all!");
        var persons = DozerMapper.parseListObject(repository.findAll(), PersonVO.class);

        persons
                .stream()
                .forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
        return persons;

    }

    public PersonVO findById(Long id) {
        logger.info("Finding one person!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(("No records found for this ID")));

        var vo = DozerMapper.parseObject(entity, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return vo;
    }

    public PersonVO create(PersonVO person) {
        logger.info("Creating one person!");
        var entity = DozerMapper.parseObject(person, Person.class);
        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }


    public PersonVOV2 createV2(PersonVOV2 person) {
        logger.info("Creating one person WITH VERSION 2!");
        var entity = mapper.convertVoToEntity(person);
        var vo = mapper.convertEntityToVo(repository.save(entity));
        return vo;
    }

    public PersonVO update(PersonVO person) {
        logger.info("Updating a person!");
        var entity = repository.findById(person.getKey())
                .orElseThrow(() -> new ResourceNotFoundException(("No records found for this ID")));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public void delete(Long id) {
        logger.info("Deleting a person!");
        var entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(("No records found for this ID"))
        );
        repository.delete(entity);
    }
}
