package utilities;

import endpoints.users.UsersEndPoint;
import models.users.UserResponseModel;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;
import utilities.listeners.SuiteListeners;
import utilities.listeners.TestListeners;

@Listeners({SuiteListeners.class, TestListeners.class})
public abstract class Base {
    protected SoftAssert softAssert;
    protected static String token;

    protected UserResponseModel generateNewUser() {
        return new UsersEndPoint().generateNewUser();
    }

    @BeforeSuite(alwaysRun = true)
    public void assignToken() {
        Log.info("Generating token");
        token = generateNewUser().getToken();
        Log.debug(token);
    }
}
