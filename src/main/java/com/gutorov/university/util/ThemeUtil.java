package com.gutorov.university.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import static com.gutorov.university.config.Constants.*;

@Profile("!front")
@Controller
class ThemeUtil {
    private final WebHelper webHelper;

    public ThemeUtil(WebHelper webHelper) {
        this.webHelper = webHelper;
    }

    @PostMapping("/toggle-theme")
    public String toggleTheme(HttpServletRequest request, HttpServletResponse response) {
        final String currentTheme = webHelper.getCookie(THEME_COOKIE, THEME_DEFAULT, request);
        final String newTheme = currentTheme.equals(THEME_DARK) ? THEME_DEFAULT : THEME_DARK;
        webHelper.setCookie(THEME_COOKIE, newTheme, response);
        return "redirect:" + webHelper.getSafeReferer();
    }

    public String getTheme() {
        return webHelper.getCookie(THEME_COOKIE, THEME_DEFAULT);
    }
}
