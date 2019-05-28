package br.com.creditas.riskanalysis.web.configuration

import com.google.common.base.Predicates
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans
import org.springframework.core.Ordered
import rag.web.configuration.RequestIdFilter
import springfox.documentation.builders.PathSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import javax.servlet.Filter

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
    }

    override fun initialize(context: GenericApplicationContext) = beans().initialize(context)

    private fun registrationRequestIdFilter(): FilterRegistrationBean<Filter> {
        val registration = FilterRegistrationBean<Filter>()
        registration.filter = RequestIdFilter()
        registration.order = Ordered.HIGHEST_PRECEDENCE

        return registration
    }
}
