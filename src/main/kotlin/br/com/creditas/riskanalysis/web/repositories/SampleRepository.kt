package br.com.creditas.riskanalysis.web.repositories

import br.com.creditas.riskanalysis.web.models.SampleEntity
import org.springframework.data.repository.CrudRepository
import java.util.*

interface SampleRepository : CrudRepository<SampleEntity, UUID>
