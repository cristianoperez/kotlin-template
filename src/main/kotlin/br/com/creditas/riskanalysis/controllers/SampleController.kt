package br.com.creditas.riskanalysis.controllers

import br.com.creditas.riskanalysis.models.SampleEntity
import br.com.creditas.riskanalysis.repositories.SampleRepository
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.GetMapping

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
