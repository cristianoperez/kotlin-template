package br.com.creditas.kotlinspringsample.configuration

import com.google.common.base.Predicates
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.support.beans
import org.springframework.core.Ordered
import springfox.documentation.builders.PathSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import javax.servlet.Filter

val beans = beans {
    bean {
        Docket(DocumentationType.SWAGGER_2)
            .select()
            .paths(Predicates.not(PathSelectors.regex("/error|/info|/health.*")))
            .build()
    }
    bean { registrationRequestIdFilter() }
}

private fun registrationRequestIdFilter(): FilterRegistrationBean<Filter> {
    val registration = FilterRegistrationBean<Filter>()
    registration.filter = RequestIdFilter()
    registration.order = Ordered.HIGHEST_PRECEDENCE

    return registration
}
