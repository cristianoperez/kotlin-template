package br.com.creditas.riskanalysis.web

import br.com.creditas.riskanalysis.web.models.SampleEntity
import br.com.creditas.riskanalysis.web.repositories.SampleRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.util.*

@RunWith(SpringRunner::class)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class SampleControllerTests {
	@Autowired
	private lateinit var mockMvc: MockMvc

	@Autowired
	private lateinit var mapper: ObjectMapper

	@Test
	fun `Fidc Empirica Auto - Must return Grey Zone with all rules Approved and the rule cars_empirica as Grey Zone`() {
		val id = UUID.randomUUID()
		val sampleEntity = SampleEntity(id, "Title", "Description")
		val params = mapper.writeValueAsString(sampleEntity)

		mockMvc.perform(
			MockMvcRequestBuilders.post("/v1/sample")
				.contentType(MediaType.APPLICATION_JSON)
				.content(params))
			.andExpect(MockMvcResultMatchers.status().isOk)
			.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
			.andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Title"))
			.andExpect(MockMvcResultMatchers.jsonPath("$.description").value(arrayListOf("Description", "APPROVED", "APPROVED", "APPROVED", "APPROVED", "APPROVED", "APPROVED", "GREY_ZONE")))

		//carsApiMock.verify(1, request)
	}
}
