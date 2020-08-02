package com.evaluation.service.validation;

import org.springframework.stereotype.Service;

import com.evaluation.service.controller.model.InputObject;
import com.evaluation.service.db.DBService;

@Service
public class Validator {

	private DBService dbService;

	public Validator(DBService sqlServerHelper) {
		this.dbService = sqlServerHelper;
	}

	public boolean isValid(InputObject input) {
		return isValidInForm(input) && isValidInContent(input);
	}

	private boolean isValidInForm(InputObject input) {
		return input.getCustomerId() != null && input.getTagId() != null && input.getRemoteIP() != null
				&& input.getRemoteIP() > 0 && input.getTime() != null;
	}

	private boolean isValidInContent(InputObject input) {
		return dbService.customerIDisInDataBaseAndNotDisabled(input.getCustomerId())
				&& dbService.remoteIPisNotInBlackList(input.getRemoteIP())
				&& dbService.userIDisNotInBlackList(input.getUserId());
	}

}
