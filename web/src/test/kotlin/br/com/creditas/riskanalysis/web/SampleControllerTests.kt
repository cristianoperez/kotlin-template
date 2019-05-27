package br.com.creditas.riskanalysis.web

import br.com.creditas.riskanalysis.web.models.SampleEntity
import br.com.creditas.riskanalysis.web.repositories.SampleRepository
import com.fasterxml.jackson.databind.ObjectMapper
import com.nhaarman.mockitokotlin2.*
import org.amshove.kluent.`should be`
import org.amshove.kluent.shouldContainSame
import org.junit.Test
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
import java.util.*

@Profile("test")
@Configuration
class SampleControllerTestsConfiguration {
    @Bean
    @Primary
    fun mockedRepository(): SampleRepository {
        return mock()
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
        val id = UUID.randomUUID()
        val entity = SampleEntity(id, "Title", "Description")
        val params = mapper.writeValueAsString(entity)
        whenever(sampleRepository.save(entity)).thenReturn(entity)

        mockMvc.perform(
            MockMvcRequestBuilders.post("/sample")
                .contentType(MediaType.APPLICATION_JSON)
                .content(params))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id.toString()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Title"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Description"))

        verify(sampleRepository).save(entity)
    }

    @Test
    fun `Fluent assertions example with "Kluent" lib`() {
        val computedList = listOf(1, 2, 3)
        val desiredList = listOf(1, 2, 3)

        computedList shouldContainSame desiredList
        computedList.any { it == 4 } `should be` false
    }
}
