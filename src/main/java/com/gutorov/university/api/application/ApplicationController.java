package com.gutorov.university.api.application;

import com.gutorov.university.api.page.PageHelper;
import com.gutorov.university.api.page.PageRs;
import com.gutorov.university.config.Constants;
import com.gutorov.university.entity.ApplicationEntity;
import com.gutorov.university.entity.projection.MonthlyApplications;
import com.gutorov.university.entity.projection.OverallStatsProjection;
import com.gutorov.university.entity.projection.ProgramApplicationsStats;
import com.gutorov.university.entity.projection.ProgramPopularity;
import com.gutorov.university.service.ApplicationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
    public PageRs<ApplicationRs> getApplications(
            @RequestParam(defaultValue = "1") @Min(1) int page,
            @RequestParam(defaultValue = "10") @Min(1) int size) {
        return applicationService.getAll(PageHelper.toPageable(page, size));
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

    @GetMapping("/stats/by-program")
    public List<ProgramApplicationsStats> getApplicationsStatsByProgram() {
        return applicationService.getApplicationsStatsByProgram();
    }

    @GetMapping("/stats/popular-programs")
    public List<ProgramPopularity> getTopPopularPrograms(@RequestParam(required = false) Integer limit) {
        if (limit == null) return applicationService.getTopPopularPrograms();
        else return applicationService.getTopPopularPrograms(limit);
    }

    @GetMapping("/stats/monthly")
    public List<MonthlyApplications> getMonthlyApplications(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) {
        return applicationService.getApplicationsByMonth(from, to);
    }

    @GetMapping("/stats/overall")
    public OverallStatsProjection getOverallStats(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) {
        return applicationService.getOverallStats(from, to);
    }

    @GetMapping("/stats/by-program/{programId}")
    public List<ApplicationRs> getApplicationsByProgramAndPeriod(
            @PathVariable UUID programId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to,
            @RequestParam(required = true) Boolean isAdmitted) {

        List<ApplicationEntity> entities;
        entities = applicationService.getApplicationsByProgramAndPeriod(programId, from, to, isAdmitted);

        return ApplicationRs.fromEntityList(entities);
    }

}
