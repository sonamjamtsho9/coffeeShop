package edu.mum.coffee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

	@PostMapping(path = "/create")
	public ResponseEntity<Person> createPerson(@RequestBody Person person) {
		personService.savePerson(person);
		return ResponseEntity.ok(person);
	}

	@GetMapping(path = "/byemail", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ResponseBody
	public List<Person> getByEmail(@RequestParam("email") String email) {
		// String decodedEmail = URLDecoder.decode(email);
		List<Person> person = personService.findByEmail(email);
		return person;
	}

	@GetMapping(path = "/byID/{id}")
	public ResponseEntity<Person> findPerson(@PathVariable long id) {
		Person person = personService.findById(id);
		return ResponseEntity.ok(person);
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

	@GetMapping(path = "/list")
	public ResponseEntity<List<Person>> listAll() {
		List<Person> list = personService.findAll();
		return ResponseEntity.ok(list);
	}
}
