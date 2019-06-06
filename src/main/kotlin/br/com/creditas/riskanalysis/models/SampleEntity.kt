package br.com.creditas.riskanalysis.models

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "sample_entity")
data class SampleEntity(@Id @GeneratedValue val id: UUID = UUID.fromString("00000000-0000-0000-0000-000000000000"),
                   val title: String = "",
                   val description: String = "")
