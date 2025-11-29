package com.gutorov.university;

import com.gutorov.university.api.application.ApplicationRq;
import com.gutorov.university.api.author.AuthorRq;
import com.gutorov.university.api.category.CategoryRq;
import com.gutorov.university.api.news.NewsRq;
import com.gutorov.university.api.program.ProgramRq;
import com.gutorov.university.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Objects;

@SpringBootApplication
public class UniversityApplication implements CommandLineRunner {
    private final Logger log = LoggerFactory.getLogger(UniversityApplication.class);
    private final ApplicationService applicationService;
    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final NewsService newsService;
    private final ProgramService programService;

    public UniversityApplication(ApplicationService applicationService, AuthorService authorService, CategoryService categoryService, NewsService newsService, ProgramService programService) {
        this.applicationService = applicationService;
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.newsService = newsService;
        this.programService = programService;
    }

    public static void main(String[] args) {
        SpringApplication.run(UniversityApplication.class, args);
    }

    private void populateData() {
        log.info("Populating data");

        log.info("Populating authors");
        final var author1 = authorService.create(new AuthorRq("Anton Antonov"));
        final var author2 = authorService.create(new AuthorRq("Semen Semenov"));
        final var author3 = authorService.create(new AuthorRq("Maxim Maximov"));

        log.info("Population categories");
        final var category1 = categoryService.create(new CategoryRq("Campus"));
        final var category2 = categoryService.create(new CategoryRq("Science"));
        final var category3 = categoryService.create(new CategoryRq("Art"));

        log.info("Population news");
        newsService.create(new NewsRq("Title 1", "Content 1", category1.getId(), author1.getId(), LocalDateTime.of(2025, Month.SEPTEMBER, 5, 17, 30, 0)));
        newsService.create(new NewsRq("Title 2", "Content 2", category2.getId(), author2.getId(), LocalDateTime.of(2025, Month.SEPTEMBER, 5, 17, 40, 10)));
        newsService.create(new NewsRq("Title 3", "Content 3", category3.getId(), author3.getId(), LocalDateTime.of(2025, Month.SEPTEMBER, 5, 17, 50, 20)));

        log.info("Populating programs");
        final var program1 = programService.create(new ProgramRq("Physics"));
        final var program2 = programService.create(new ProgramRq("Maths"));
        final var program3 = programService.create(new ProgramRq("Informatics"));

        log.info("Populating applications");
        applicationService.create(new ApplicationRq("Ivan Ivanov", "ivanov@mail.com", program1.getId(), LocalDateTime.of(2025, Month.SEPTEMBER, 5, 14, 30, 20), false));
        applicationService.create(new ApplicationRq("Petr Petrov", "petrov@mail.com", program2.getId(), LocalDateTime.of(2025, Month.SEPTEMBER, 5, 14, 20, 30), true));
        applicationService.create(new ApplicationRq("Vasiliy Vasin", "vasin@mail.com", program3.getId(), LocalDateTime.of(2025, Month.SEPTEMBER, 5, 14, 10, 40), false));
    }

    @Override
    public void run(String... args) throws Exception {
        if (args.length == 0) return;

        if (Objects.equals(args[0], "--populate")) populateData();
    }
}
