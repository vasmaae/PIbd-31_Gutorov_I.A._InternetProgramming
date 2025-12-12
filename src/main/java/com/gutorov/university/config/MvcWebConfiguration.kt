package com.gutorov.university.config

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.lang.NonNull
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Profile("!front")
@Configuration
open class MvcWebConfiguration : WebMvcConfigurer {
    override fun addViewControllers(@NonNull registry: ViewControllerRegistry) {
//        registry.addViewController("/page1").setViewName("page1")
//        registry.addViewController("/page2").setViewName("page2")
//        registry.addViewController("/page3").setViewName("page3")
//        registry.addViewController("/page4").setViewName("page4")

        registry.addRedirectViewController("/", "/page1")
    }
}
