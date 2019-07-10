package br.com.creditas.kotlinspringsample.repositories

import br.com.creditas.kotlinspringsample.models.SampleEntity
import java.util.UUID
import org.springframework.data.repository.CrudRepository

interface SampleRepository : CrudRepository<SampleEntity, UUID>
