package com.evaluation.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.evaluation.service.controller.model.InputObject;
import com.evaluation.service.db.service.IDBService;
import com.evaluation.service.validation.Validator;

@RestController
public class EvaluationServiceController {

	@Autowired
	private Validator validator;

	@Autowired
	private IDBService dbService;

	@PutMapping("/evaluation")
	public ResponseEntity<String> helloWorld(@RequestBody InputObject input) {
		if (input.getCustomerID() == null) {
			return new ResponseEntity<String>("noCustomerId: not added2DB", HttpStatus.BAD_REQUEST);
		}
		final boolean isValid = validator.isValid(input);
		this.dbService.addInput2DB(input, isValid);
		return new ResponseEntity<String>(creatResponseMessage(isValid), getHttpStatus(isValid));
	}

	private String creatResponseMessage(final boolean isValid) {
		return getMessageFromValidatorAndFormat() + " added2DB as " + getStringValidOrInvalid(isValid) + " request";
	}

	private String getMessageFromValidatorAndFormat() {
		return validator.getMessage().isEmpty() ? validator.getMessage() : validator.getMessage() + "\n";
	}

	private String getStringValidOrInvalid(boolean isValid) {
		return isValid ? "valid" : "invalid";
	}

	private HttpStatus getHttpStatus(final boolean isValid) {
		return isValid ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
	}
}
