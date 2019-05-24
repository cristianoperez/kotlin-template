package br.com.creditas.riskanalysis.web.controllers

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1")
class HelloWorldController {

    @GetMapping("/helloworld")
    @ResponseStatus(HttpStatus.OK)
    fun helloWorld() {}
}
