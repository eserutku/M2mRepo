package uk.co.kayratech.m2m.platform.model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import uk.co.kayratech.m2m.platform.model.lov.LovValueTest;

@RunWith(Suite.class)
@SuiteClasses({ LovValueTest.class, UserTest.class })
public class AllUnitTests {

}
