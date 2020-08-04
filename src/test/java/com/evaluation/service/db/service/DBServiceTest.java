package com.evaluation.service.db.service;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.evaluation.service.controller.model.InputObject;
import com.evaluation.service.db.model.Customer;
import com.evaluation.service.db.model.IpBlacklist;
import com.evaluation.service.db.model.UaBlacklist;
import com.evaluation.service.db.repository.CustomerRepository;
import com.evaluation.service.db.repository.HourlyStatsRepository;
import com.evaluation.service.db.repository.IpBlacklistRepository;
import com.evaluation.service.db.repository.UaBlacklistRepository;
import com.fasterxml.jackson.datatype.jdk8.OptionalDoubleSerializer;

@RunWith(MockitoJUnitRunner.class)
public class DBServiceTest {

	@Mock
	private CustomerRepository customerRepository;

	@Mock
	private IpBlacklistRepository ipBlacklistRepository;

	@Mock
	private UaBlacklistRepository uaBlacklistRepository;

	@Mock
	private HourlyStatsRepository hourlyStatsRepository;

	@Mock
	private DBServiceHelper helper;

	//
	private IDBService dBService;

	@Before
	public void initChecker() {
		this.dBService = new DBService(customerRepository, ipBlacklistRepository, uaBlacklistRepository,
				hourlyStatsRepository, helper);
		Customer customer = new Customer();
		customer.setId(1);
		customer.setActive(true);
		IpBlacklist ipBlacklist = null;
		UaBlacklist uaBlacklist = null;
		Optional<IpBlacklist> optIP = Optional.ofNullable(null);
		Optional<UaBlacklist> optUa = Optional.ofNullable(null);

		Mockito.when(customerRepository.findById(1)).thenReturn(Optional.of(customer));
		Mockito.when(customerRepository.findById(1)).thenReturn(Optional.of(customer));
		Mockito.when(ipBlacklistRepository.findById("1.1.1.1")).thenReturn(optIP);
		Mockito.when(uaBlacklistRepository.findById("ua1")).thenReturn(optUa);
	}

	@Test
	public void test_validWithDB() {
		// arrange
		InputObject inputObject = creatValidInputObject();
		// act
		boolean bool_customerIdisInDataBaseAndNotDisabled = dBService
				.customerIdIsInDataBaseAndNotDisabled(inputObject.getCustomerID());
		boolean bool_remoteIpIsNotInIpBlackList = dBService.remoteIpIsNotInIpBlackList(inputObject.getRemoteIP());
		boolean bool_userIDisNotInUaBlackList = dBService.userIDisNotInUaBlackList(inputObject.getUserID());
		// assert
		assertTrue(bool_customerIdisInDataBaseAndNotDisabled);
		assertTrue(bool_remoteIpIsNotInIpBlackList);
		assertTrue(bool_userIDisNotInUaBlackList);
	}

	@Test
	public void test_invalidWithDB_customerIsDisabled() {
		// arrange
		Integer customerIDNotInDB = 10;
		Optional<Customer> optcustomer = Optional.ofNullable(null);

		Mockito.when(customerRepository.findById(customerIDNotInDB)).thenReturn(optcustomer);
		InputObject inputObject = creatValidInputObject();
		inputObject.setCustomerID(customerIDNotInDB);

		// act
		boolean bool_customerIdisInDataBaseAndNotDisabled = dBService
				.customerIdIsInDataBaseAndNotDisabled(inputObject.getCustomerID());
		boolean bool_remoteIpIsNotInIpBlackList = dBService.remoteIpIsNotInIpBlackList(inputObject.getRemoteIP());
		boolean bool_userIDisNotInUaBlackList = dBService.userIDisNotInUaBlackList(inputObject.getUserID());
		// assert
		assertFalse(bool_customerIdisInDataBaseAndNotDisabled);
		assertTrue(bool_remoteIpIsNotInIpBlackList);
		assertTrue(bool_userIDisNotInUaBlackList);
	}

	@Test
	public void test_invalidWithDB_customerIdIsNoInDataBase() {
		// arrange
		Integer customerDisabled = 10;

		Customer customer = new Customer();
		customer.setId(customerDisabled);
		customer.setActive(false);
		Mockito.when(customerRepository.findById(customerDisabled)).thenReturn(Optional.of(customer));

		InputObject inputObject = creatValidInputObject();
		inputObject.setCustomerID(customerDisabled);
		// act
		boolean bool_customerIdisInDataBaseAndNotDisabled = dBService
				.customerIdIsInDataBaseAndNotDisabled(inputObject.getCustomerID());
		boolean bool_remoteIpIsNotInIpBlackList = dBService.remoteIpIsNotInIpBlackList(inputObject.getRemoteIP());
		boolean bool_userIDisNotInUaBlackList = dBService.userIDisNotInUaBlackList(inputObject.getUserID());
		// assert
		assertFalse(bool_customerIdisInDataBaseAndNotDisabled);
		assertTrue(bool_remoteIpIsNotInIpBlackList);
		assertTrue(bool_userIDisNotInUaBlackList);
	}

	@Test
	public void test_invalidWithDB_ipInBlackList() {
		// arrange
		String ipInBlackList = "0.0.0.0";
		IpBlacklist ipBlacklist = new IpBlacklist();
		ipBlacklist.setIp(ipInBlackList);
		Mockito.when(ipBlacklistRepository.findById(ipInBlackList)).thenReturn(Optional.of(ipBlacklist));

		InputObject inputObject = creatValidInputObject();
		inputObject.setRemoteIP(ipInBlackList);
		// act
		boolean bool_customerIdisInDataBaseAndNotDisabled = dBService
				.customerIdIsInDataBaseAndNotDisabled(inputObject.getCustomerID());
		boolean bool_remoteIpIsNotInIpBlackList = dBService.remoteIpIsNotInIpBlackList(inputObject.getRemoteIP());
		boolean bool_userIDisNotInUaBlackList = dBService.userIDisNotInUaBlackList(inputObject.getUserID());
		// assert
		assertTrue(bool_customerIdisInDataBaseAndNotDisabled);
		assertFalse(bool_remoteIpIsNotInIpBlackList);
		assertTrue(bool_userIDisNotInUaBlackList);
	}

	@Test
	public void test_invalidWithDB_uaInBlackList() {
		// arrange
		String uaInBlackList = "badUa";
		UaBlacklist uaBlackList = new UaBlacklist();
		uaBlackList.setUa(uaInBlackList);
		Mockito.when(uaBlacklistRepository.findById(uaInBlackList)).thenReturn(Optional.of(uaBlackList));

		InputObject inputObject = creatValidInputObject();
		inputObject.setUserID(uaInBlackList);
		// act
		boolean bool_customerIdisInDataBaseAndNotDisabled = dBService
				.customerIdIsInDataBaseAndNotDisabled(inputObject.getCustomerID());
		boolean bool_remoteIpIsNotInIpBlackList = dBService.remoteIpIsNotInIpBlackList(inputObject.getRemoteIP());
		boolean bool_userIDisNotInUaBlackList = dBService.userIDisNotInUaBlackList(inputObject.getUserID());
		// assert
		assertTrue(bool_customerIdisInDataBaseAndNotDisabled);
		assertTrue(bool_remoteIpIsNotInIpBlackList);
		assertFalse(bool_userIDisNotInUaBlackList);
	}

	@Test
	public void test_valid1() {
		// arrange
		InputObject inputObject = creatValidInputObject();
		assertTrue(dBService.customerIdIsInDataBaseAndNotDisabled(inputObject.getCustomerID()));
		assertTrue(dBService.remoteIpIsNotInIpBlackList(inputObject.getRemoteIP()));
		assertTrue(dBService.userIDisNotInUaBlackList(inputObject.getUserID()));
	}

	private InputObject creatValidInputObject() {
		InputObject inputObject = new InputObject();
		inputObject.setCustomerID(1);
		inputObject.setRemoteIP("1.1.1.1");
		inputObject.setTagID(1);
		inputObject.setTimestamp(1L);
		inputObject.setUserID("ua1");
		return inputObject;
	}
}
