package br.com.josehamilton.api.controller;

import br.com.josehamilton.api.dto.PersonDTO;
import br.com.josehamilton.api.entity.Person;
import br.com.josehamilton.api.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
@WebMvcTest(controllers = PersonController.class)
public class PersonControllerTest {

	private static final String PERSON_API = "/api/person/v1";
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private PersonService service;
	
	@Test
	@DisplayName("Teste para criação de uma pessoa.")
	public void creatingPersonTest() throws Exception {
		// Cenário
		PersonDTO dto = PersonDTO.builder()
				.firstName("Jose Hamilton")
				.lastName("Martins Leite")
				.email("josehamiltonmartinsleite@gmail.com")
				.yearBirth(1999L)
				.build();

		Person person = Person.builder()
				.id(1L)
				.firstName("Jose Hamilton")
				.lastName("Martins Leite")
				.email("josehamiltonmartinsleite@gmail.com")
				.yearBirth(1999L)
				.build();

		BDDMockito.given( service.save(Mockito.any(Person.class)) ).willReturn( person );

		String json = new ObjectMapper().writeValueAsString(dto);

		// Execução
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
			.post(PERSON_API)
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON)
			.content(json);

		// Verificações
		mvc
			.perform(request)
			.andExpect(status().isOk())
			.andExpect(jsonPath("data.id").isNotEmpty())
			.andExpect(jsonPath("data.firstName").value(dto.getFirstName()))
			.andExpect(jsonPath("data.lastName").value(dto.getLastName()))
			.andExpect(jsonPath("data.email").value(dto.getEmail()))
			.andExpect(jsonPath("data.yearBirth").value(dto.getYearBirth()))
		;
	}

	@Test
	@DisplayName("Teste para ocasionar erro na criação de uma pessoa.")
	public void creatingInvalidPersonTest() throws Exception {
		// Cenário
		String json = new ObjectMapper().writeValueAsString(new PersonDTO());
		// Execução
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.post(PERSON_API)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(json);
		// Verificações
		mvc
				.perform(request)
				.andExpect(status().isBadRequest())
		;
	}
	
}