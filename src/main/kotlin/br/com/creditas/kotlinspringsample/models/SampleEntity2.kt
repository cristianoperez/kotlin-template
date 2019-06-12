package br.com.creditas.kotlinspringsample.models

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "sample_entity2")
data class SampleEntity2(
    @Column(name = "title")
    val title: String,
    @Column(name = "description")
    val description: String
){
    @Id
    @GeneratedValue
    val id: UUID? = null
}