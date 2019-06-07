package br.com.creditas.template

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TemplateController {

    @GetMapping("/heello")
    fun hello(): HelloWorldDTO{
        return HelloWorldDTO("Hello world")
    }
}

data class HelloWorldDTO(val message: String)