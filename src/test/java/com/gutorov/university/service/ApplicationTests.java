package com.gutorov.university.service;

import com.gutorov.university.api.application.ApplicationRq;
import com.gutorov.university.api.application.ApplicationRs;
import com.gutorov.university.api.program.ProgramRq;
import com.gutorov.university.entity.ApplicationEntity;
import com.gutorov.university.exception.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootTest
@Transactional
@Rollback
public class ApplicationTests {
    @Autowired
    private ProgramService programService;
    @Autowired
    private ApplicationService applicationService;

    @BeforeEach
    void setUp() {
        for (final var program : programService.getAll())
            programService.delete(program.getId());
        for (final var application : applicationService.getAll())
            applicationService.delete(application.getId());
    }

    @Test
    void getEntity_WithUnexistingId_ThrowsNotFoundException() {
        Assertions.assertThrows(NotFoundException.class, () -> applicationService.getEntity(UUID.randomUUID()));
    }

    @Test
    void getEntity_WithExistingId_ReturnsApplication() {
        final var program = programService.create(new ProgramRq("Program 1"));
        final var application = applicationService.create(
                new ApplicationRq(
                        "Application 1", "example@mail.ru", program.getId(), LocalDateTime.now(), false));
        assertApplicationsAreEqual(application, applicationService.getEntity(application.getId()));
    }

    @Test
    void getAll_WithNotEmptyRepository_ReturnsApplications() {
        final var program = programService.create(new ProgramRq("Program 1"));
        applicationService.create(new ApplicationRq(
                "Application 1", "example1@mail.ru", program.getId(), LocalDateTime.now(), false));
        applicationService.create(new ApplicationRq(
                "Application 2", "example2@mail.ru", program.getId(), LocalDateTime.now(), false));
        applicationService.create(new ApplicationRq(
                "Application 3", "example3@mail.ru", program.getId(), LocalDateTime.now(), false));
        Assertions.assertEquals(3, applicationService.getAll().size());
    }

    @Test
    void get_WithNotEmptyRepository_ReturnsApplication() {
        final var program = programService.create(new ProgramRq("Program 1"));
        final var application = applicationService.create(new ApplicationRq(
                "Application 1", "example@mail.ru", program.getId(), LocalDateTime.now(), false));
        assertApplicationsAreEqual(application, applicationService.get(application.getId()));
    }

    @Test
    void create_WithValidData_ReturnApplication() {
        final var program = programService.create(new ProgramRq("Program 1"));
        final var createdApplication = applicationService.create(new ApplicationRq(
                "Application 1", "example@mail.ru", program.getId(), LocalDateTime.now(), false));
        assertApplicationsAreEqual(createdApplication, applicationService.get(createdApplication.getId()));
    }

    @Test
    void update_WithValidData_ReturnsUpdatedApplication() {
        final var program = programService.create(new ProgramRq("Program 1"));
        final var createdApplicationId = applicationService.create(new ApplicationRq(
                "Application 1", "example1@mail.ru", program.getId(), LocalDateTime.now(), false)).getId();
        final var updatedApplication = applicationService.update(createdApplicationId, new ApplicationRq(
                "Application 2", "example2@mail.ru", program.getId(), LocalDateTime.now(), true));
        assertApplicationsAreEqual(updatedApplication, applicationService.get(updatedApplication.getId()));
    }

    @Test
    void delete_WithUnexistingId_ApplicationDeletes() {
        Assertions.assertThrows(NotFoundException.class, () -> applicationService.delete(UUID.randomUUID()));
    }

    @Test
    void delete_WithExistingId_ApplicationDeletes() {
        final var program = programService.create(new ProgramRq("Program 1"));
        final var createdApplicationId = applicationService.create(new ApplicationRq(
                "Application 1", "example@mail.ru", program.getId(), LocalDateTime.now(), false)).getId();
        applicationService.delete(createdApplicationId);
        Assertions.assertEquals(0, applicationService.getAll().size());
    }

    private void assertApplicationsAreEqual(ApplicationRs expected, ApplicationRs actual) {
        Assertions.assertEquals(expected.getId(), actual.getId());
        Assertions.assertEquals(expected.getFullName(), actual.getFullName());
        Assertions.assertEquals(expected.getEmail(), actual.getEmail());
        Assertions.assertEquals(expected.getProgram().getId(), actual.getProgram().getId());
        Assertions.assertEquals(expected.getProgram().getName(), actual.getProgram().getName());
        Assertions.assertEquals(expected.getSubmissionDate(), actual.getSubmissionDate());
        Assertions.assertEquals(expected.isAdmitted(), actual.isAdmitted());
    }

    private void assertApplicationsAreEqual(ApplicationRs expected, ApplicationEntity actual) {
        Assertions.assertEquals(expected.getId(), actual.getId());
        Assertions.assertEquals(expected.getFullName(), actual.getFullName());
        Assertions.assertEquals(expected.getEmail(), actual.getEmail());
        Assertions.assertEquals(expected.getProgram().getId(), actual.getProgram().getId());
        Assertions.assertEquals(expected.getProgram().getName(), actual.getProgram().getName());
        Assertions.assertEquals(expected.getSubmissionDate(), actual.getSubmissionDate());
        Assertions.assertEquals(expected.isAdmitted(), actual.isAdmitted());
    }
}