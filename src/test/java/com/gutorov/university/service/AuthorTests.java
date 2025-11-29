package com.gutorov.university.service;

import com.gutorov.university.api.author.AuthorRq;
import com.gutorov.university.api.author.AuthorRs;
import com.gutorov.university.entity.AuthorEntity;
import com.gutorov.university.exception.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@SpringBootTest
@Transactional
@Rollback
public class AuthorTests {
    @Autowired
    private AuthorService service;

    @BeforeEach
    void setUp() {
        for (final var author : service.getAll())
            service.delete(author.getId());
    }

    @Test
    void getEntity_WithUnexistingId_ThrowsNotFoundException() {
        Assertions.assertThrows(NotFoundException.class, () -> service.getEntity(UUID.randomUUID()));
    }

    @Test
    void getEntity_WithExistingId_ReturnsAuthor() {
        final var author = service.create(new AuthorRq("Author 1"));
        assertAuthorsAreEqual(author, service.getEntity(author.getId()));
    }

    @Test
    void getAll_WithNotEmptyRepository_ReturnsAuthors() {
        service.create(new AuthorRq("Author 1"));
        service.create(new AuthorRq("Author 2"));
        service.create(new AuthorRq("Author 3"));
        Assertions.assertEquals(3, service.getAll().size());
    }

    @Test
    void get_WithNotEmptyRepository_ReturnsAuthor() {
        final var author = service.create(new AuthorRq("Author 1"));
        assertAuthorsAreEqual(author, service.get(author.getId()));
    }

    @Test
    void create_WithValidData_ReturnAuthor() {
        final var createdAuthor = service.create(new AuthorRq("Author 1"));
        assertAuthorsAreEqual(createdAuthor, service.get(createdAuthor.getId()));
    }

    @Test
    void update_WithValidData_ReturnsUpdatedAuthor() {
        final var createdAuthorId = service.create(new AuthorRq("Author 1")).getId();
        final var updatedAuthor = service.update(createdAuthorId, new AuthorRq("Author 2"));
        assertAuthorsAreEqual(updatedAuthor, service.get(updatedAuthor.getId()));
    }

    @Test
    void delete_WithUnexistingId_AuthorDeletes() {
        Assertions.assertThrows(NotFoundException.class, () -> service.delete(UUID.randomUUID()));
    }

    @Test
    void delete_WithExistingId_AuthorDeletes() {
        final var createdAuthorId = service.create(new AuthorRq("Author 1")).getId();
        service.delete(createdAuthorId);
        Assertions.assertEquals(0, service.getAll().size());
    }

    private void assertAuthorsAreEqual(AuthorRs expected, AuthorRs actual) {
        Assertions.assertEquals(expected.getId(), actual.getId());
        Assertions.assertEquals(expected.getName(), actual.getName());
    }

    private void assertAuthorsAreEqual(AuthorRs expected, AuthorEntity actual) {
        Assertions.assertEquals(expected.getId(), actual.getId());
        Assertions.assertEquals(expected.getName(), actual.getName());
    }
}
