package br.com.creditas.riskanalysis.web.controllers

import br.com.creditas.riskanalysis.web.models.SampleEntity
import br.com.creditas.riskanalysis.web.repositories.SampleRepository
import org.springframework.web.bind.annotation.*

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
