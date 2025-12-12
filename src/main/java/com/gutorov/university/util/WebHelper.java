package com.gutorov.university.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.net.URI;

@Profile("!front")
@Component
class WebHelper {
    private HttpServletRequest getCurrentRequest() {
        final RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes servletRequestAttributes) {
            return servletRequestAttributes.getRequest();
        }
        throw new IllegalStateException("No current HTTP request");
    }

    private HttpServletResponse getCurrentResponse() {
        final RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes servletRequestAttributes) {
            HttpServletResponse response = servletRequestAttributes.getResponse();
            if (response != null) {
                return response;
            }
        }
        throw new IllegalStateException("No current HTTP response");
    }

    public void setCookie(String name, String value) {
        setCookie(name, value, getCurrentResponse());
    }

    public void setCookie(String name, String value, HttpServletResponse response) {
        final Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(30 * 24 * 60 * 60);
        cookie.setPath("/");
        cookie.setHttpOnly(false); // Доступно из JavaScript если нужно
        response.addCookie(cookie);
    }

    public String getCookie(String name) {
        return getCookie(name, null);
    }

    public String getCookie(String name, String defaultValue) {
        return getCookie(name, defaultValue, getCurrentRequest());
    }

    public String getCookie(String name, String defaultValue, HttpServletRequest request) {
        final Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return defaultValue;
    }

    // Получение параметра запроса
    public String getParam(String name) {
        return getParam(name, getCurrentRequest());
    }

    public String getParam(String name, HttpServletRequest request) {
        return request.getParameter(name);
    }

    // Получение параметра заголовка запроса
    public String getHeader(String name) {
        return getHeader(name, getCurrentRequest());
    }

    public String getHeader(String name, HttpServletRequest request) {
        return request.getHeader(name);
    }

    private boolean isSameOrigin(String url, HttpServletRequest request) {
        try {
            final URI refererUri = new URI(url);
            final String refererHost = refererUri.getHost();
            final String serverName = request.getServerName();
            return refererHost != null && refererHost.equals(serverName);
        } catch (Exception e) {
            return false;
        }
    }

    public String getSafeReferer() {
        return getSafeReferer(getCurrentRequest());
    }

    public String getSafeReferer(HttpServletRequest request) {
        String referer = getHeader("Referer", request);

        // Проверяем, что referer существует и принадлежит нашему домену
        if (referer != null && isSameOrigin(referer, request)) {
            return referer;
        }

        // На главную страницу
        return "/";
    }

    public String getServletPath() {
        final HttpServletRequest request = getCurrentRequest();
        return request.getServletPath();
    }
}
