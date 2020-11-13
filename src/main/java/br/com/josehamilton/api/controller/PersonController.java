package br.com.josehamilton.api.controller;

import br.com.josehamilton.api.dto.PersonDTO;
import br.com.josehamilton.api.entity.Person;
import br.com.josehamilton.api.exception.WaitingValuesException;
import br.com.josehamilton.api.response.Response;
import br.com.josehamilton.api.service.PersonService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/person/v1")
@RequiredArgsConstructor
public class PersonController {

	private final PersonService personService;
	private final ModelMapper modelMapper;

	@PostMapping
	public ResponseEntity<Response<PersonDTO>> create(@Valid @RequestBody PersonDTO dto, BindingResult result) {
		if ( result.hasErrors() ) throw new WaitingValuesException("Informe todos os campos obrigatórios.");

		Response<PersonDTO> response = new Response<PersonDTO>();

		Person person = this.modelMapper.map(dto, Person.class);
		Person savedPerson = this.personService.save(person);
		dto = this.modelMapper.map(savedPerson, PersonDTO.class);

		response.setData(dto);
		return ResponseEntity.ok(response);
	}

}
