package edu.mum.coffee.controller;

import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.mum.coffee.domain.Person;
import edu.mum.coffee.service.PersonService;

@Controller
@RequestMapping("/person")
public class PersonController {

	@Autowired
	private PersonService personService;

	@PostMapping(path = "/saveperson")
	public ResponseEntity<Person> savePerson(@RequestBody Person person) throws URISyntaxException {
		try {
			personService.savePerson(person);
			return ResponseEntity.ok(person);
		} catch (Exception e) {
			// return Conflict (409)
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
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
		try {
			if (person.getId() == personID) {
				personService.savePerson(person);
				return ResponseEntity.ok(person);
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

}
