package br.com.creditas.kotlinspringsample.models

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "sample_entity")
data class SampleEntity(
    val title: String,
    val description: String
) {
    @Id
    val id: UUID = UUID.randomUUID()
}
