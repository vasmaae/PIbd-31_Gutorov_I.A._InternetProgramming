package com.gutorov.university.service;

import com.gutorov.university.api.program.ProgramRs;
import com.gutorov.university.entity.ProgramEntity;
import com.gutorov.university.exception.NotFoundException;
import com.gutorov.university.mapper.ProgramMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.UUID;

@SpringBootTest
@Rollback
public class ProgramTests {
    @Autowired
    private ProgramService service;
    @Autowired
    private ProgramMapper mapper;

    @BeforeEach
    void setUp() {
        for (final var program : service.getAll())
            service.delete(program.getId());
    }

    @Test
    void getEntity_WithUnexistingId_ThrowsNotFoundException() {
        Assertions.assertThrows(NotFoundException.class, () -> service.getEntity(UUID.randomUUID()));
    }

    @Test
    void getEntity_WithExistingId_ReturnsProgram() {
        final var program = service.create(mapper.toRequest("Program 1"));
        assertProgramsAreEqual(program, service.getEntity(program.getId()));
    }

    @Test
    void getAll_WithNotEmptyRepository_ReturnsPrograms() {
        service.create(mapper.toRequest("Program 1"));
        service.create(mapper.toRequest("Program 2"));
        service.create(mapper.toRequest("Program 3"));
        Assertions.assertEquals(3, service.getAll().size());
    }

    @Test
    void get_WithNotEmptyRepository_ReturnsProgram() {
        final var program = service.create(mapper.toRequest("Program 1"));
        assertProgramsAreEqual(program, service.get(program.getId()));
    }

    @Test
    void create_WithValidData_ReturnProgram() {
        final var createdProgram = service.create(mapper.toRequest("Program 1"));
        assertProgramsAreEqual(createdProgram, service.get(createdProgram.getId()));
    }

    @Test
    void update_WithValidData_ReturnsUpdatedProgram() {
        final var createdProgramId = service.create(mapper.toRequest("Program 1")).getId();
        final var updatedProgram = service.update(createdProgramId, mapper.toRequest("Program 2"));
        assertProgramsAreEqual(updatedProgram, service.get(updatedProgram.getId()));
    }

    @Test
    void delete_WithUnexistingId_ProgramDeletes() {
        Assertions.assertThrows(NotFoundException.class, () -> service.delete(UUID.randomUUID()));
    }

    @Test
    void delete_WithExistingId_ProgramDeletes() {
        final var createdProgramId = service.create(mapper.toRequest("Program 1")).getId();
        service.delete(createdProgramId);
        Assertions.assertEquals(0, service.getAll().size());
    }

    private void assertProgramsAreEqual(ProgramRs expected, ProgramRs actual) {
        Assertions.assertEquals(expected.getId(), actual.getId());
        Assertions.assertEquals(expected.getName(), actual.getName());
    }

    private void assertProgramsAreEqual(ProgramRs expected, ProgramEntity actual) {
        Assertions.assertEquals(expected.getId(), actual.getId());
        Assertions.assertEquals(expected.getName(), actual.getName());
    }
}