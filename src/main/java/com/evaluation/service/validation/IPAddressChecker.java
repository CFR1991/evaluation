package com.evaluation.service.validation;

import org.springframework.stereotype.Service;

@Service
class IPAddressChecker {

	boolean check(String ipString) {
		if (!ipString.contains(".")) {
			return false;
		}
		return checkSplitted(ipString.split("\\."));
	}

	private boolean checkSplitted(String[] strings) {
		if (strings.length != 4) {
			return false;
		}
		for (String splitted : strings) {
			Integer intOfString = getInt(splitted);
			if (intOfString == null) {
				return false;
			}
			if (intOfString < 0 || intOfString > 255) {
				return false;
			}
		}
		return true;
	}

	private Integer getInt(String splitted) {
		try {
			return Integer.parseInt(splitted);
		} catch (Exception e) {
			return null;
		}
	}

}
