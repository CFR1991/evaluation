package com.evaluation.service.db.service;

import com.evaluation.service.controller.model.InputObject;

public interface IDBService {

	boolean customerIdIsInDataBaseAndNotDisabled(Integer coustumerID);

	boolean remoteIpIsNotInIpBlackList(String ip);

	boolean userIDisNotInUaBlackList(String ua);

	void addInput2DB(InputObject input, boolean b);

}