package br.com.creditas.kotlinspringsample.repositories

import br.com.creditas.kotlinspringsample.models.SampleEntity
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface SampleRepository : CrudRepository<SampleEntity, UUID>
