package users;

import endpoints.users.UsersEndPoint;
import models.Model;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.Base;
import utilities.endpointhelpers.JsonPayloadProvider;

import static utilities.endpointhelpers.SchemaProvider.getUserSchemaPath;

public class UpdateUserInfoTest extends Base {
    private UsersEndPoint usersEndPoint;
    private SoftAssert softAssert;

    @Test(dataProvider = "update data", groups = {"smoke"})
    public void testName(Model payload, String schemaJsonPath) {
        usersEndPoint = new UsersEndPoint(token);
        usersEndPoint.updateUserInfo(payload);

        Assert.assertTrue(usersEndPoint.verifyStatusCode(200));

        softAssert = new SoftAssert();
        softAssert.assertTrue(usersEndPoint.getResponseTime() < 8000L);
        softAssert.assertTrue(usersEndPoint.verifySchema(schemaJsonPath));
        softAssert.assertAll();
    }

    @DataProvider(name = "update data")
    public Object[][] updateUserDataProvider() {
        return new Object[][]{
                {new JsonPayloadProvider().getCredentialsUserJson(), getUserSchemaPath()}
        };
    }
}
