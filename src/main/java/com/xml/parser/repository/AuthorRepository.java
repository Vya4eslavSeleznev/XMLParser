package com.xml.parser.repository;

import com.xml.parser.entity.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorRepository extends CrudRepository<Author, Long> {

    List<Author> findByCustomAuthorIdIn(List<String> customAuthorId);
}
