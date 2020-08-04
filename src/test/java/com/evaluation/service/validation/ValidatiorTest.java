package com.evaluation.service.validation;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.evaluation.service.controller.model.InputObject;
import com.evaluation.service.db.service.IDBService;

@RunWith(MockitoJUnitRunner.class)
public class ValidatiorTest {

	@Mock
	private IDBService dbService;

	@Mock
	private IIPAddressChecker ipAddressChecker;

	private Validator validator;

	@Before
	public void initValidator() {
		this.validator = new Validator(dbService, ipAddressChecker);
		Mockito.when(ipAddressChecker.check("0.0.0.1")).thenReturn(true);
		Mockito.when(dbService.customerIdIsInDataBaseAndNotDisabled(1)).thenReturn(true);
		Mockito.when(dbService.remoteIpIsNotInIpBlackList("0.0.0.1")).thenReturn(true);
		Mockito.when(dbService.userIDisNotInUaBlackList("ua1")).thenReturn(true);

	}

	// Valid
	@Test
	public void test_valid() {
		// arrange
		InputObject inputObject = createValidInputObject();

		// act
		boolean valid = validator.isValid(inputObject);
		String message = validator.getMessage();

		// assert
		Assert.assertTrue(valid);
		Assert.assertTrue(message.isEmpty());
	}

	// Invalid
	// InvalidInForm
	@Test
	public void test_invalidInForm_RemoteIPNull() {
		// arrange
		InputObject inputObject = createValidInputObject();
		inputObject.setRemoteIP(null);
		// act
		boolean valid = validator.isValid(inputObject);
		String message = validator.getMessage();
		// assert
		Assert.assertFalse(valid);
		Assert.assertFalse(message.isEmpty());
		Assert.assertTrue(message.contains("isInvalidInForm"));
	}

	@Test
	public void test_invalidInForm_TagIDNull() {
		InputObject inputObject = createValidInputObject();
		inputObject.setTagID(null);
		Assert.assertFalse(validator.isValid(inputObject));
		Assert.assertFalse(validator.getMessage().isEmpty());
		Assert.assertTrue(validator.getMessage().contains("isInvalidInForm"));
	}

	@Test
	public void test_invalidInForm_TimestampNull() {
		// arrange
		InputObject inputObject = createValidInputObject();
		inputObject.setTimestamp(null);
		// act
		boolean valid = validator.isValid(inputObject);
		String message = validator.getMessage();
		// assert
		Assert.assertFalse(valid);
		Assert.assertFalse(message.isEmpty());
		Assert.assertTrue(message.contains("isInvalidInForm"));

	}

	@Test
	public void test_invalidInForm_invalidRemoteIP() {
		// arrange
		InputObject inputObject = createValidInputObject();
		inputObject.setRemoteIP("invalid");
		Mockito.when(ipAddressChecker.check("invalid")).thenReturn(false);
		// act
		boolean valid = validator.isValid(inputObject);
		String message = validator.getMessage();
		// assert
		Assert.assertFalse(valid);
		Assert.assertFalse(message.isEmpty());
		Assert.assertTrue(message.contains("isInvalidInForm"));
	}

	@Test
	public void test_invalidInForm_invalidTimestamp() {
		// arrange
		Long invalidLong = Long.getLong("blubb");
		InputObject inputObject = createValidInputObject();
		inputObject.setTimestamp(invalidLong);
		// act
		boolean valid = validator.isValid(inputObject);
		String message = validator.getMessage();
		// assert
		Assert.assertFalse(valid);
		Assert.assertFalse(message.isEmpty());
		Assert.assertTrue(message.contains("isInvalidInForm"));
	}

	// InvalidWithDB

	// @Test
	public void test_invalidInForm_CustomerNotInDBOrInactive() {
		// arrange
		Integer customerID_withCustomerNotInDBOrInactive = 60;
		Mockito.when(dbService.customerIdIsInDataBaseAndNotDisabled(customerID_withCustomerNotInDBOrInactive))
				.thenReturn(false);
		InputObject inputObject = createValidInputObject();
		inputObject.setCustomerID(customerID_withCustomerNotInDBOrInactive);
		// act
		boolean valid = validator.isValid(inputObject);
		String message = validator.getMessage();
		// assert
		Assert.assertFalse(valid);
		Assert.assertFalse(message.isEmpty());
		Assert.assertTrue(message.contains("isInValidWithDB"));
	}

	// @Test
	public void test_invalidInForm_IpInIpBlackList() {
		// arrange
		String remoteIP_inIpInBlackList = "0.0.0.0";
		Mockito.when(dbService.remoteIpIsNotInIpBlackList(remoteIP_inIpInBlackList)).thenReturn(false);
		InputObject inputObject = createValidInputObject();
		inputObject.setRemoteIP(remoteIP_inIpInBlackList);
		// act
		boolean valid = validator.isValid(inputObject);
		String message = validator.getMessage();
		// assert
		Assert.assertFalse(valid);
		Assert.assertFalse(message.isEmpty());
		Assert.assertTrue(message.contains("isInValidWithDB"));
	}

	// @Test
	public void test_invalidInForm_UaInUaBlackList() {
		// arrange
		String userID_inUaBlackList = "Googlebot";
		Mockito.when(dbService.userIDisNotInUaBlackList(userID_inUaBlackList)).thenReturn(false);
		InputObject inputObject = createValidInputObject();
		inputObject.setUserID(userID_inUaBlackList);
		// act
		boolean valid = validator.isValid(inputObject);
		String message = validator.getMessage();
		// assert
		Assert.assertFalse(valid);
		Assert.assertFalse(message.isEmpty());
		Assert.assertTrue(message.contains("isInValidWithDB"));
	}

	//
	//
	private InputObject createValidInputObject() {
		InputObject input = new InputObject();
		input.setCustomerID(1);
		input.setRemoteIP("0.0.0.1");
		input.setTagID(3);
		input.setTimestamp(15000000L);
		input.setUserID("ua1");
		return input;
	}
}
