package users;

import endpoints.users.UsersEndPoint;
import models.users.CredentialsSuperModel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static utilities.SchemaReader.getUserSchema;

public class RegisterTest {
    private UsersEndPoint usersEndPoint;

    @BeforeMethod
    public void setUp() {
        initEndPoints();
    }

    @Test(dataProvider = "register data")
    public void registerTest(CredentialsSuperModel credentialsPayload, String schemaJsonPath) {
        usersEndPoint.createUser(credentialsPayload);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(usersEndPoint.verifyStatusCode(200));
        softAssert.assertTrue(usersEndPoint.verifySchema(schemaJsonPath));
        softAssert.assertAll();
    }

    public void initEndPoints() {
        usersEndPoint = new UsersEndPoint();
    }

    @DataProvider(name = "register data")
    public Object[][] registerDataProvider() {
        return new Object[][]{
                {new CredentialsSuperModel(), getUserSchema()}
        };
    }
}
