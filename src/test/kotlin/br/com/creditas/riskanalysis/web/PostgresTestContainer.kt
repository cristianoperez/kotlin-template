package br.com.creditas.riskanalysis.web

import org.testcontainers.containers.PostgreSQLContainer

object PostgresTestContainer: PostgreSQLContainer<PostgresTestContainer>()

