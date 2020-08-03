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

	@PutMapping("/evaluation")
	public ResponseEntity<String> helloWorld(@RequestBody InputObject input) {
		if (input.getCustomerID() == null) {
			return new ResponseEntity<String>("noCustomerId: not added2DB", HttpStatus.BAD_REQUEST);
		}
		final boolean isValid = validator.isValid(input);
		final HttpStatus status = isValid ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
		this.dbService.addInput2DB(input, isValid);
		return new ResponseEntity<String>(creatResponseMessage(isValid), status);
	}

	private String creatResponseMessage(final boolean isValid) {
		return validator.getMessage() + " added2DB as " + getStringFromValid(isValid) + " request";
	}

	private String getStringFromValid(boolean isValid) {
		return isValid ? "valid" : "invalid";
	}

}
