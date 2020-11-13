package br.com.josehamilton.api.service;

import br.com.josehamilton.api.entity.Person;
import org.springframework.stereotype.Service;

@Service
public interface PersonService {

    Person save(Person person);

}
