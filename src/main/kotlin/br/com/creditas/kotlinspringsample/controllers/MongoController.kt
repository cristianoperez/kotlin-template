package br.com.creditas.kotlinspringsample.controllers

import br.com.creditas.kotlinspringsample.models.MongoEntity
import br.com.creditas.kotlinspringsample.repositories.MongodbRepository
import java.util.UUID
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/mongo/sample")
class MongoController(private val sampleRepository: MongodbRepository) {

    @PostMapping
    fun add(@RequestBody se: MongoEntity) = sampleRepository.save(se)

    @GetMapping
    fun list() = sampleRepository.findAll().toList()

    @DeleteMapping("/{id}")
    fun remove(@PathVariable id: UUID) = sampleRepository.deleteById(id)
}
