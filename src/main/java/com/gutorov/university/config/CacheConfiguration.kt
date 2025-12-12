package com.gutorov.university.config

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.lang.NonNull
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Profile("!front")
@Configuration
open class CacheConfiguration : WebMvcConfigurer {
    override fun addResourceHandlers(@NonNull registry: ResourceHandlerRegistry) {
        registry
            .addResourceHandler("/webjars/**", "/images/*")
            .addResourceLocations(
                "classpath:/META-INF/resources/webjars/",
                "classpath:/public/images/"
            )
            .setCachePeriod(3600 * 24)
    }
}

