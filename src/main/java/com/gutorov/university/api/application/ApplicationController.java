package com.gutorov.university.api.application;

import com.gutorov.university.config.Constants;
import com.gutorov.university.service.ApplicationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(Constants.API_URL + ApplicationController.URL)
public class ApplicationController {
    static final String URL = "/applications";
    private final ApplicationService applicationService;

    ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping
    public List<ApplicationRs> getApplications() {
        return applicationService.getAll();
    }

    @GetMapping("/{id}")
    public ApplicationRs get(@PathVariable UUID id) {
        return applicationService.get(id);
    }

    @PostMapping
    public ApplicationRs create(@RequestBody @Valid ApplicationRq newApplication) {
        return applicationService.create(newApplication);
    }

    @PutMapping("/{id}")
    public ApplicationRs update(@PathVariable UUID id, @RequestBody @Valid ApplicationRq newApplication) {
        return applicationService.update(id, newApplication);
    }

    @DeleteMapping("/{id}")
    public ApplicationRs delete(@PathVariable UUID id) {
        return applicationService.delete(id);
    }
}
