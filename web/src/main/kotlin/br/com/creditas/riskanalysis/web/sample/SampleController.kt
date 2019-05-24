package br.com.creditas.riskanalysis.web.sample

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("sample")
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