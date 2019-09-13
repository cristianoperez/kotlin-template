package br.com.creditas.kotlinspringsample

import br.com.creditas.kotlinspringsample.models.SampleEntity
import br.com.creditas.kotlinspringsample.models.TypeEnum
import br.com.creditas.kotlinspringsample.repositories.SampleRepository
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.amshove.kluent.`should be`
import org.amshove.kluent.`should equal`
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldContainSame
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.time.LocalDateTime

@RunWith(SpringRunner::class)
@SpringBootTest
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
        val entity = SampleEntity(
            title = "Title",
            description = "Description",
            fullName = "Full Name",
            birthDate = LocalDateTime.now(),
            entityType = TypeEnum.SIMPLE
        )

        val params = mapper.writeValueAsString(entity)

        mockMvc.perform(
            MockMvcRequestBuilders.post("/sample")
                .contentType(MediaType.APPLICATION_JSON)
                .content(params))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty)
            .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Title"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Description"))
            .andDo { mvcResult ->
            val responseEntity = mapper.readValue<SampleEntity>(mvcResult.response.contentAsString)
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
