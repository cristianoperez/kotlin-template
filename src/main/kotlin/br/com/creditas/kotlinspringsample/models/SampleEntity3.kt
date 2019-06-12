package br.com.creditas.kotlinspringsample.models

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "sample_entity3")
class SampleEntity3(
    @Column(name = "title")
    val title: String,
    @Column(name = "description")
    val description: String
){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
}