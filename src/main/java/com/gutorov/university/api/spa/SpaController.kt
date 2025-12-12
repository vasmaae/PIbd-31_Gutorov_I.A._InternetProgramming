package com.gutorov.university.api.spa

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class SpaController {
    @GetMapping(value = ["/{path:^(?!api|assets|images|swagger-ui|.*\\.[a-zA-Z0-9]{2,10}).*}/**"])
    fun forwardToIndex(@PathVariable(required = false) path: String?): String {
        return "forward:/index.html"
    }
}
