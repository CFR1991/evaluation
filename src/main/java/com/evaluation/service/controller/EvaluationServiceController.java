package com.evaluation.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.evaluation.service.controller.model.InputObject;
import com.evaluation.service.db.DBService;
import com.evaluation.service.validation.Validator;

@RestController
public class EvaluationServiceController {

	@Autowired
	private Validator validator;

	@Autowired
	private DBService dbService;

	@RequestMapping(path = "/test", method = RequestMethod.GET)
	public String helloWorld(@RequestParam("input") String input) {
		return "Input parameter: " + input;
	}

	@PutMapping("/validate")
	public ResponseEntity<String> helloWorld(@RequestBody InputObject input) {
		if (input.getCustomerID() == null) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		if (validator.isValid(input)) {
			 dbService.addInput2DB(input,true);
			return new ResponseEntity<String>(HttpStatus.OK);
		}
		 dbService.addInput2DB(input, false);
		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}
}
