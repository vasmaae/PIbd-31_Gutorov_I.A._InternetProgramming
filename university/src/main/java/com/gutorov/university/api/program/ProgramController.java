package com.gutorov.university.api.program;

import com.gutorov.university.config.Constants;
import com.gutorov.university.service.ProgramService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(Constants.API_URL + ProgramController.URL)
public class ProgramController {
    static final String URL = "/programs";
    private final ProgramService programService;

    ProgramController(ProgramService programService) {
        this.programService = programService;
    }

    @GetMapping
    public List<ProgramRs> getAll() {
        return programService.getAll();
    }

    @GetMapping("/{id}")
    public ProgramRs get(@PathVariable UUID id) {
        return programService.get(id);
    }

    @PostMapping
    public ProgramRs create(@RequestBody @Valid ProgramRq request) {
        return programService.create(request);
    }

    @PutMapping("/{id}")
    public ProgramRs update(@PathVariable UUID id,
                            @RequestBody @Valid ProgramRq request) {
        return programService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ProgramRs delete(@PathVariable UUID id) {
        return programService.delete(id);
    }
}