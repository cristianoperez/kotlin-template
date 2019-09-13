package br.com.creditas.kotlinspringsample.models

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType

@Entity
@Table(name = "sample_entity3")
data class SampleEntity3(
    @Column(name = "title")
    val title: String,
    @Column(name = "description")
    val description: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
}
