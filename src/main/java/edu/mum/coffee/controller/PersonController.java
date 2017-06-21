package edu.mum.coffee.controller;

import java.net.URLDecoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.mum.coffee.domain.Person;
import edu.mum.coffee.service.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	private PersonService personService;

	@PostMapping(path = "/saveperson")
	public ResponseEntity<Person> savePerson(@RequestBody Person person) {
		personService.savePerson(person);
		return ResponseEntity.ok(person);
	}

	@GetMapping(path = "/list")
	@ResponseBody
	public List<Person> getByEmail(@RequestParam("email") String email) {
		String decodedEmail = URLDecoder.decode(email);
		List<Person> person = personService.findByEmail(decodedEmail);
		return person;
	}

	@PutMapping(path = "/saveperson/{id}")
	public ResponseEntity<Person> update(@RequestBody Person person, @PathVariable int personID) {

		if (person.getId() == personID) {
			personService.savePerson(person);
			return ResponseEntity.ok(person);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
