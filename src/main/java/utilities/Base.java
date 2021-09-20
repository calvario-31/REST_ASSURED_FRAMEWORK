package utilities;

import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;
import utilities.listeners.SuiteListeners;
import utilities.listeners.TestListeners;

@Listeners({SuiteListeners.class, TestListeners.class})
public abstract class Base {
    protected SoftAssert softAssert;
    protected String token;
    protected Commons commons;
}
