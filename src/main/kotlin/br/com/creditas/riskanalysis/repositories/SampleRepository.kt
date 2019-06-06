package br.com.creditas.riskanalysis.repositories

import br.com.creditas.riskanalysis.models.SampleEntity
import org.springframework.data.repository.CrudRepository
import java.util.*

interface SampleRepository : CrudRepository<SampleEntity, UUID>
