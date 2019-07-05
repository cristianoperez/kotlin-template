package br.com.creditas.kotlinspringsample.controllers

import br.com.creditas.kotlinspringsample.models.SampleEntity
import br.com.creditas.kotlinspringsample.repositories.SampleRepository
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.GetMapping

@RestController
@RequestMapping("/sample")
class SampleController(private val sampleRepository: SampleRepository) {

    @PostMapping
    fun add(@RequestBody se: SampleEntity) = sampleRepository.save(se)

    @GetMapping
    fun list() = sampleRepository.findAll().toList()
}
