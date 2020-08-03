package com.evaluation.service.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evaluation.service.controller.model.InputObject;
import com.evaluation.service.db.DBService;

@Service
public class Validator {

	private DBService dbService;
	private String message;
	private IPAddressChecker ipAdressChecker;

	@Autowired
	public Validator(DBService sqlServerHelper, IPAddressChecker ipAddressChecker) {
		this.dbService = sqlServerHelper;
		this.ipAdressChecker = ipAddressChecker;
	}

	public boolean isValid(InputObject input) {
		this.message="";
		if (!isValidInForm(input)) {
			return false;
		}
		return isValidWithDB(input);
	}

	private boolean isValidInForm(InputObject input) {
		if (input.getCustomerID() != null && input.getTagID() != null && input.getRemoteIP() != null
				&& ipAdressChecker.check(input.getRemoteIP()) && input.getTimestamp() != null) {
			return true;
		}
		this.message = String.format("%s, %s", message, "isInvalidInForm: malformedJson");
		return false;
	}

	private boolean isValidWithDB(InputObject input) {
		if (dbService.customerIdisInDataBaseAndNotDisabled(input.getCustomerID())
				&& dbService.remoteIpisNotInIpBlackList(input.getRemoteIP().toString())
				&& dbService.userIDisNotInUaBlackList(input.getUserID())) {
			return true;
		}
		this.message = String.format("%s, %s", message, "isInValidWithDB");
		return false;
	}

	public String getMessage() {
		if (this.message.startsWith(",")) {
			this.message = message.substring(1) + "\n";
		}
		return this.message;
	}

}
