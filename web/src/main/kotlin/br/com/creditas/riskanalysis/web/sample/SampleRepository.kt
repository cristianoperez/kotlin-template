package br.com.creditas.riskanalysis.web.sample

import org.springframework.data.repository.CrudRepository
import java.util.*

interface SampleRepository : CrudRepository<SampleEntity, UUID>