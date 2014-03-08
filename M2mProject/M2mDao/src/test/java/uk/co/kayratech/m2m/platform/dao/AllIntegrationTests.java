package uk.co.kayratech.m2m.platform.dao;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import uk.co.kayratech.m2m.platform.dao.integration.LovTypeDaoIntegrationTest;
import uk.co.kayratech.m2m.platform.dao.integration.LovValueDaoIntegrationTest;
import uk.co.kayratech.m2m.platform.dao.integration.UserDaoIntegrationTest;

@RunWith(Suite.class)
@SuiteClasses({ LovValueDaoIntegrationTest.class, LovTypeDaoIntegrationTest.class,
		UserDaoIntegrationTest.class })
public class AllIntegrationTests {

}
