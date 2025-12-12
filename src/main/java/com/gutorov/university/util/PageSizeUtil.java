package com.gutorov.university.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import static com.gutorov.university.config.Constants.*;

@Profile("!front")
@Controller
class PageSizeUtil {
    private final WebHelper webHelper;

    public PageSizeUtil(WebHelper webHelper) {
        this.webHelper = webHelper;
    }

    @PostMapping("/toggle-page-size")
    public String toggleTheme(HttpServletRequest request, HttpServletResponse response) {
        final String currentTheme = webHelper.getCookie(PAGE_SIZE_COOKIE, PAGE_SIZE_DEFAULT, request);
        final String newTheme = currentTheme.equals(PAGE_SIZE_SECONDARY) ? PAGE_SIZE_DEFAULT : PAGE_SIZE_SECONDARY;
        webHelper.setCookie(PAGE_SIZE_COOKIE, newTheme, response);
        return "redirect:" + webHelper.getSafeReferer();
    }

    public String getTheme() {
        return webHelper.getCookie(PAGE_SIZE_COOKIE, PAGE_SIZE_DEFAULT);
    }
}
