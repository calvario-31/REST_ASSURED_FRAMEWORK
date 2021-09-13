package users;

import endpoints.users.UsersEndPoint;
import models.users.CredentialsSuperModel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static utilities.SchemaReader.getUserSchema;

public class LoginTest {
    private UsersEndPoint usersEndPoint;
    private SoftAssert softAssert;

    @BeforeMethod
    public void setUp() {
        initEndPoints();
    }

    @Test(dataProvider = "credentials data")
    public void registerTest(CredentialsSuperModel credentialsPayload, String schemaJsonPath) {
        usersEndPoint.createUser(credentialsPayload);
        usersEndPoint.loginUser(credentialsPayload);

        softAssert = new SoftAssert();
        softAssert.assertTrue(usersEndPoint.verifyStatusCode(200));
        softAssert.assertTrue(usersEndPoint.verifySchema(schemaJsonPath));
        softAssert.assertAll();
    }

    public void initEndPoints() {
        usersEndPoint = new UsersEndPoint();
    }

    @DataProvider(name = "credentials data")
    public Object[][] loginDataProvider() {
        return new Object[][]{
                {new CredentialsSuperModel(), getUserSchema()}
        };
    }
}
