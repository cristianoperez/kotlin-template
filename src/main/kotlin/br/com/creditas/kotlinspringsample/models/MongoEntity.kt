package br.com.creditas.kotlinspringsample.models

import java.time.LocalDateTime
import java.util.UUID
import javax.persistence.Id

data class MongoEntity(
    @Id
    val id: UUID = UUID.randomUUID(),
    val title: String,
    val description: String,
    val fullName: String,
    val birthDate: LocalDateTime,
    val entityType: String,
    val sampleNestedEntity: SampleNestedEntity = SampleNestedEntity()
)

data class SampleNestedEntity (
    val name: String? = null,
    val type: String? = null,
    val value: String? = null
)
