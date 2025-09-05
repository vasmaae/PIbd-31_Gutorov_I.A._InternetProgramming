package com.gutorov.university.controller;

import com.gutorov.university.config.Constants;
import com.gutorov.university.dto.ProgramDto;
import com.gutorov.university.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedDeque;

@RestController
@RequestMapping(Constants.API_URL + ProgramsController.URL)
class ProgramsController {
    static final String URL = "/programs";
    private final Logger log = LoggerFactory.getLogger(ProgramsController.class);
    private final ConcurrentLinkedDeque<ProgramDto> programs;

    public ProgramsController() {
        this.programs = new ConcurrentLinkedDeque<>(List.of(
                new ProgramDto(UUID.randomUUID().toString(), "Physics"),
                new ProgramDto(UUID.randomUUID().toString(), "Maths"),
                new ProgramDto(UUID.randomUUID().toString(), "Informatics")
        ));
    }

    @GetMapping
    public List<ProgramDto> getPrograms() {
        var response = programs.stream().toList();
        log.debug("get all: {}", response);
        return response;
    }

    @GetMapping("/{id}")
    public ProgramDto get(@PathVariable String id) {
        var response = programs.stream()
                .filter(program -> program.getId().equals(id))
                .findAny()
                .orElseThrow(() -> new NotFoundException(ProgramDto.class, id));
        log.debug("get by id \"{}\": {}", id, response);
        return response;
    }

    @PostMapping
    public ProgramDto create(@RequestBody ProgramDto newProgram) {
        newProgram.setId(UUID.randomUUID().toString());
        log.debug("create: {}", newProgram);
        programs.add(newProgram);
        return newProgram;
    }

    @PutMapping("/{id}")
    public ProgramDto update(@PathVariable String id, @RequestBody ProgramDto newProgram) {
        log.debug("update by id \"{}\": {}", id, newProgram);
        final var program = get(id);
        program.setName(newProgram.getName());
        return program;
    }

    @DeleteMapping("/{id}")
    public ProgramDto delete(@PathVariable String id) {
        final var program = get(id);
        log.debug("delete by id \"{}\": {}", id, program);
        programs.remove(program);
        return program;
    }
}
