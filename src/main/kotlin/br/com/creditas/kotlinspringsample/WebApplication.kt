package br.com.creditas.kotlinspringsample

import br.com.creditas.kotlinspringsample.configuration.beans
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import springfox.documentation.swagger2.annotations.EnableSwagger2

@SpringBootApplication
@EnableSwagger2
class WebApplication

fun main(args: Array<String>) {
    runApplication<WebApplication>() {
        this.addInitializers(beans)
        if (args.isNotEmpty() && args[0] == "migrate") {
            this.setAdditionalProfiles("dbmigration")
        }
    }
}
