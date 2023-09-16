package br.com.rosivan.services;

import br.com.rosivan.controllers.BookController;
import br.com.rosivan.data.vo.v1.BookVO;
import br.com.rosivan.exceptions.RequiredObjectIsNullException;
import br.com.rosivan.exceptions.ResourceNotFoundException;
import br.com.rosivan.mapper.DozerMapper;
import br.com.rosivan.model.Book;
import br.com.rosivan.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookServices {

    private Logger logger = Logger.getLogger(BookServices.class.getName());

    @Autowired
    BookRepository repository;

    public List<BookVO> findAll() {
        logger.info("Finding all book!");
        var persons = DozerMapper.parseListObject(repository.findAll(), BookVO.class);

        persons
                .stream()
                .forEach(p -> p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel()));
        return persons;

    }

    public BookVO findById(Long id) {
        logger.info("Finding one person!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(("No records found for this ID")));

        var vo = DozerMapper.parseObject(entity, BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
        return vo;
    }

    public BookVO create(BookVO person) {
        if(person == null) {
            throw new RequiredObjectIsNullException();
        }
        logger.info("Creating one person!");
        var entity = DozerMapper.parseObject(person, Book.class);
        var vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public BookVO update(BookVO person) {
        logger.info("Updating a person!");
        var entity = repository.findById(person.getKey())
                .orElseThrow(() -> new ResourceNotFoundException(("No records found for this ID")));

        entity.setAuthor(person.getAuthor());
        entity.setLaunchDate(person.getLaunchDate());
        entity.setPrice(person.getPrice());
        entity.setTitle(person.getTitle());


        var vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
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
