package br.com.creditas.riskanalysis.web.controllers

import br.com.creditas.riskanalysis.web.models.SampleEntity
import br.com.creditas.riskanalysis.web.repositories.SampleRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1")
class SampleController(private val sampleRepository: SampleRepository) {

    @PostMapping("/sample")
    fun add(@RequestBody se: SampleEntity): SampleEntity {
        return sampleRepository.save(se)
    }

    @GetMapping("/sample")
    fun list(): List<SampleEntity> {
        return sampleRepository.findAll().toList()
    }
}
