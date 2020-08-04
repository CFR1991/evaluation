package com.evaluation.service.validation;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IPAddressCheckerTest {

	private IIPAddressChecker checker;

	@Before
	public void initChecker() {
		this.checker = new IPAddressChecker();
	}

	@Test
	public void test_valid1() {
		Assert.assertTrue(checker.check("1.1.1.1"));
	}

	@Test
	public void test_valid2() {
		Assert.assertTrue(checker.check("0.0.0.0"));
	}

	@Test
	public void test_valid3() {
		Assert.assertTrue(checker.check("255.255.255.255"));
	}

	@Test
	public void test_invalidIP_higherThan255() {
		Assert.assertFalse(checker.check("1000.1.1.1"));
	}

	@Test
	public void test_invalidIP_lowerThan0() {

		Assert.assertFalse(checker.check("-1000.1.1.1"));
	}

	@Test
	public void test_invalidIP_not4Dots() {

		Assert.assertFalse(checker.check("1.1.1"));
	}

	@Test
	public void test_invalidIP_noDots() {

		Assert.assertFalse(checker.check("111"));
	}

	@Test
	public void test_invalidIP_noNumber1() {

		Assert.assertFalse(checker.check("fff.sdd.ee.ee"));
	}

	@Test
	public void test_invalidIP_noNumber2() {

		Assert.assertFalse(checker.check("fff"));
	}
}
