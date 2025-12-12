package com.gutorov.university.config

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.lang.NonNull
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Profile("front")
@Configuration
open class RestWebConfiguration : WebMvcConfigurer {
    override fun addCorsMappings(@NonNull registry: CorsRegistry) {
        registry
            .addMapping(Constants.API_URL + "/**")
            .allowedMethods("GET", "POST", "PUT", "DELETE")
            .allowedOrigins(Constants.DEV_ORIGIN)
    }
}
