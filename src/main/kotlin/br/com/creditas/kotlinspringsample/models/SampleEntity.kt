package br.com.creditas.kotlinspringsample.models

import java.time.LocalDateTime
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "sample_entity")
data class SampleEntity(
    val title: String,
    val description: String,
    val fullName: String,
    val birthDate: LocalDateTime,

    @Enumerated(EnumType.STRING)
    val entityType: TypeEnum
) {
    @Id
    val id: UUID = UUID.randomUUID()
}

enum class TypeEnum {
    SIMPLE, COMPLETE
}
