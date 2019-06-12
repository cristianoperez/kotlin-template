package br.com.creditas.kotlinspringsample.models

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "sample_entity")
data class SampleEntity(
    @Column(name = "title")
    val title: String,
    @Column(name = "description")
    val description: String
) {
    @Id
    val id: UUID = UUID.randomUUID()
}