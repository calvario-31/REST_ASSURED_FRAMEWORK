package users;

import endpoints.users.UsersEndPoint;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.Base;
import utilities.endpointhelpers.JsonPayloadProvider;

import static utilities.endpointhelpers.SchemaProvider.getUserSchemaPath;

public class RegisterTest extends Base {
    private UsersEndPoint usersEndPoint;
    private SoftAssert softAssert;

    @Test(dataProvider = "register data", groups = {"smoke"})
    public void registerTest(String payload, String schemaJsonPath) {
        usersEndPoint = new UsersEndPoint();
        usersEndPoint.createUser(payload);

        Assert.assertTrue(usersEndPoint.verifyStatusCode(200));

        softAssert = new SoftAssert();
        softAssert.assertTrue(usersEndPoint.getResponseTime() < 8000L);
        softAssert.assertTrue(usersEndPoint.verifySchema(schemaJsonPath));
        softAssert.assertAll();
    }

    @DataProvider(name = "register data")
    public Object[][] registerDataProvider() {
        return new Object[][]{
                {new JsonPayloadProvider().getCredentialsUserJson(), getUserSchemaPath()}
        };
    }
}
