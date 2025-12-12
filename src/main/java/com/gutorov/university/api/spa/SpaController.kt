package com.gutorov.university.api.spa

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class SpaController {
    @GetMapping(
        "/{path:^(?!api|assets|images|swagger-ui|webjars|.*\\.[a-zA-Z0-9]{2,10}).*$}/**",
        "/{path:^(?!api|assets|images|swagger-ui|webjars).*$}"
    )
    fun forwardToIndex(@PathVariable(required = false) path: String? = null): String = "forward:/index.html"
}