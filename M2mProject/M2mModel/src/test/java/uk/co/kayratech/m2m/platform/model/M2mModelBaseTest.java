package uk.co.kayratech.m2m.platform.model;

import java.util.Locale;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import uk.co.kayratech.m2m.platform.common.context.InheritableThreadLocalContext;

public class M2mModelBaseTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		InheritableThreadLocalContext.instance.get().setLocale(new Locale("en"));
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
}
