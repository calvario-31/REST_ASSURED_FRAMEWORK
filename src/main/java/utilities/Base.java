package utilities;

import endpoints.users.UsersEndPoint;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;
import utilities.Listeners.SuiteListeners;
import utilities.Listeners.TestListeners;

@Listeners({SuiteListeners.class, TestListeners.class})
public abstract class Base {
    protected SoftAssert softAssert;
    protected static String token;

    protected String generateToken() {
        return new UsersEndPoint().generateToken();
    }

    @BeforeSuite(alwaysRun = true)
    public void assignToken() {
        Log.info("Generating token");
        token = generateToken();
        Log.debug(token);
    }

    public abstract void initEndPoints();
}
