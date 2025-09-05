package com.gutorov.university.controller;

import com.gutorov.university.config.Constants;
import com.gutorov.university.dto.ApplicationDto;
import com.gutorov.university.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedDeque;

@RestController
@RequestMapping(Constants.API_URL + ApplicationsController.URL)
public class ApplicationsController {
    static final String URL = "/applications";
    private final Logger log = LoggerFactory.getLogger(ApplicationsController.class);
    private final ConcurrentLinkedDeque<ApplicationDto> applications;
    private final ProgramsController programsController;

    ApplicationsController(ProgramsController programsController) {
        this.programsController = programsController;
        var programs = programsController.getPrograms();
        this.applications = new ConcurrentLinkedDeque<>(List.of(
                new ApplicationDto(UUID.randomUUID().toString(), "Ivan Ivanov", "ivanov@mail.com",
                        programs.get(0).getId(), LocalDateTime.of(2025, Month.SEPTEMBER, 5, 14, 30, 20)),
                new ApplicationDto(UUID.randomUUID().toString(), "Petr Petrov", "petrov@mail.com",
                        programs.get(1).getId(), LocalDateTime.of(2025, Month.SEPTEMBER, 5, 13, 20, 30)),
                new ApplicationDto(UUID.randomUUID().toString(), "Vasya Vasin", "vasin@mail.com",
                        programs.get(2).getId(), LocalDateTime.of(2025, Month.SEPTEMBER, 5, 12, 10, 40)))
        );
        this.applications.forEach(application -> {
            application.setProgram(programsController.get(application.getProgramId()));
        });
    }

    @GetMapping
    public List<ApplicationDto> getApplications() {
        var response = applications.stream().toList();
        log.debug("get all: {}", response);
        return response;
    }

    @GetMapping("/{id}")
    public ApplicationDto get(@PathVariable String id) {
        var response = applications.stream()
                .filter(application -> application.getId().equals(id))
                .findAny()
                .orElseThrow(() -> new NotFoundException(ApplicationDto.class, id));
        log.debug("get by id \"{}\": {}", id, response);
        return response;
    }

    @PostMapping
    public ApplicationDto create(@RequestBody ApplicationDto newApplication) {
        newApplication.setId(UUID.randomUUID().toString());
        newApplication.setProgram(programsController.get(newApplication.getProgramId()));
        log.debug("create: {}", newApplication);
        applications.add(newApplication);
        return newApplication;
    }

    @PutMapping("/{id}")
    public ApplicationDto update(@PathVariable String id, @RequestBody ApplicationDto newApplication) {
        log.debug("update by id \"{}\": {}", id, newApplication);
        final var application = get(id);
        application.setFullName(newApplication.getFullName());
        application.setEmail(newApplication.getEmail());
        application.setProgramId(newApplication.getProgramId());
        application.setProgram(programsController.get(newApplication.getProgramId()));
        application.setSubmissionDate(newApplication.getSubmissionDate());
        return application;
    }

    @DeleteMapping("/{id}")
    public ApplicationDto delete(@PathVariable String id) {
        final var application = get(id);
        log.debug("delete by id \"{}\": {}", id, application);
        applications.remove(application);
        return application;
    }
}
