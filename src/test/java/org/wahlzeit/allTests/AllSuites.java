package org.wahlzeit.allTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.wahlzeit.handlers.AllHandlersTests;
import org.wahlzeit.model.AllModelTests;
import org.wahlzeit.services.AllServiceTests;
import org.wahlzeit.utils.AllUtilTests;

@RunWith(Suite.class)
@SuiteClasses({AllHandlersTests.class, AllHandlersTests.class, AllModelTests.class, AllServiceTests.class, AllUtilTests.class})
public class AllSuites {

}
