package br.com.creditas.riskanalysis.web

import br.com.creditas.riskanalysis.web.models.SampleEntity
import br.com.creditas.riskanalysis.web.repositories.SampleRepository
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.amshove.kluent.*
import org.junit.*
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.*
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import javax.sql.DataSource

@Profile("test")
@Configuration
class SampleControllerTestsConfiguration {
    @Bean
    @Primary
    fun dataSource(): DataSource {
        if(!PostgresTestContainer.isRunning) {
            PostgresTestContainer.start()
        }

        val config = HikariConfig()
        config.jdbcUrl = PostgresTestContainer.jdbcUrl
        config.username = PostgresTestContainer.username
        config.password = PostgresTestContainer.password
        config.isAllowPoolSuspension = true

        return HikariDataSource(config)
    }
}

@RunWith(SpringRunner::class)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class SampleControllerTests {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var mapper: ObjectMapper

    @Autowired
    private lateinit var sampleRepository: SampleRepository

    @Test
    fun `When receiving a Sample entity by parameter, should pass it to the repository`() {
        val entity = SampleEntity(title = "Title", description =  "Description")
        val params = mapper.writeValueAsString(entity)

        mockMvc.perform(
            MockMvcRequestBuilders.post("/sample")
                .contentType(MediaType.APPLICATION_JSON)
                .content(params))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty)
            .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Title"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Description"))
            .andDo {
            val responseEntity = mapper.readValue<SampleEntity>(it.response.contentAsString)
            val dbEntity = sampleRepository.findById(responseEntity.id)

            dbEntity.isPresent shouldBe true
            responseEntity `should equal` dbEntity.get()
        }
    }

    @Test
    fun `Fluent assertions example with "Kluent" lib`() {
        val computedList = listOf(1, 2, 3)
        val desiredList = listOf(1, 2, 3)

        computedList shouldContainSame desiredList
        computedList.any { it == 4 } `should be` false
    }
}
