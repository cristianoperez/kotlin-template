package br.com.creditas.kotlinspringsample.controllers

import br.com.creditas.kotlinspringsample.models.SampleEntity
import br.com.creditas.kotlinspringsample.repositories.SampleRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/sample")
class SampleController(private val sampleRepository: SampleRepository) {

    @PostMapping
    fun add(@RequestBody se: SampleEntity): SampleEntity {
        return sampleRepository.save(se)
    }

    @GetMapping
    fun list(): List<SampleEntity> {
        return sampleRepository.findAll().toList()
    }
}
