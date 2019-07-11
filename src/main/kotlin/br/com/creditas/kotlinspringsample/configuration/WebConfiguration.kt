package br.com.creditas.kotlinspringsample.configuration

import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.google.common.base.Predicates
import java.text.SimpleDateFormat
import javax.servlet.Filter
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans
import org.springframework.core.Ordered
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
internal class WebConfiguration : ApplicationContextInitializer<GenericApplicationContext> {
    private fun beans() = beans {
        bean {
            Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(Predicates.not(PathSelectors.regex("/error|/info|/health.*")))
                .build()
        }
        bean { registrationRequestIdFilter() }

        bean { jackson2ObjectMapperBuilder() }
    }

    override fun initialize(context: GenericApplicationContext) = beans().initialize(context)

    private fun registrationRequestIdFilter(): FilterRegistrationBean<Filter> {
        val registration = FilterRegistrationBean<Filter>()
        registration.filter = RequestIdFilter()
        registration.order = Ordered.HIGHEST_PRECEDENCE

        return registration
    }

    private fun jackson2ObjectMapperBuilder(): Jackson2ObjectMapperBuilder {
        return Jackson2ObjectMapperBuilder()
            .propertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
            .featuresToEnable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS)
            .dateFormat(SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ"))
    }
}
