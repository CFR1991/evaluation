package com.evaluation.service.validation;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evaluation.service.controller.model.InputObject;
import com.evaluation.service.db.service.IDBService;

@Service
public class Validator {

	private IDBService dbService;
	private IIPAddressChecker ipAddressChecker;

	private String message;

	@Autowired
	public Validator(IDBService dbService, IIPAddressChecker ipAddressChecker) {
		this.dbService = dbService;
		this.ipAddressChecker = ipAddressChecker;
	}

	public boolean isValid(InputObject inputObject) {
		this.message = "";
		if (!isValidInForm(inputObject)) {
			return false;
		}
		return isValidWithDB(inputObject);
	}

	private boolean isValidInForm(InputObject input) {
		if (input.getCustomerID() != null && input.getTagID() != null && input.getRemoteIP() != null
				&& ipAddressChecker.check(input.getRemoteIP()) && input.getTimestamp() != null
				&& isLegalTimestamp(input.getTimestamp())) {
			return true;
		}
		this.message = String.format("%s, %s", message, "isInvalidInForm: malformedJson");
		return false;
	}

	private boolean isLegalTimestamp(Long timestamp) {
		return new Timestamp(timestamp) != null;
	}

	private boolean isValidWithDB(InputObject input) {
		if (dbService.customerIdIsInDataBaseAndNotDisabled(input.getCustomerID())
				&& dbService.remoteIpIsNotInIpBlackList(input.getRemoteIP().toString())
				&& dbService.userIDisNotInUaBlackList(input.getUserID())) {
			return true;
		}
		this.message = String.format("%s, %s", message, "isInValidWithDB");
		return false;
	}

	public String getMessage() {
		return this.message.startsWith(",") ? this.message.substring(1) : this.message;
	}

}
