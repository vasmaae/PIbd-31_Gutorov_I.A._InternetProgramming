package com.gutorov.university.config

import org.springframework.context.annotation.Configuration
import org.springframework.lang.NonNull
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
open class MvcWebConfiguration : WebMvcConfigurer {
    override fun addViewControllers(@NonNull registry: ViewControllerRegistry) {
    }
}
