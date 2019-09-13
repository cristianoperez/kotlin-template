package br.com.creditas.kotlinspringsample.models

import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.GeneratedValue

@Entity
@Table(name = "sample_entity2")
data class SampleEntity2(
    @Column(name = "title")
    val title: String,
    @Column(name = "description")
    val description: String
) {
    @Id
    @GeneratedValue
    val id: UUID? = null
}
