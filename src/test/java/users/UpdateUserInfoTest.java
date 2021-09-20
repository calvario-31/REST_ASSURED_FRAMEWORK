package users;

import endpoints.users.UsersEndPoint;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.Base;
import utilities.Commons;
import utilities.endpointhelpers.JsonPayloadProvider;

import static utilities.endpointhelpers.SchemaProvider.getUpdateUserSchemaPath;

public class UpdateUserInfoTest extends Base {
    private UsersEndPoint usersEndPoint;
    private SoftAssert softAssert;

    @Test(dataProvider = "update data", groups = {"regression"})
    public void testName(String payload, String schemaJsonPath) {
        commons = new Commons();
        token = commons.generateNewUser().getToken();

        usersEndPoint = new UsersEndPoint(token);
        usersEndPoint.updateUserInfo(payload);

        Assert.assertTrue(usersEndPoint.verifyStatusCode(200), "status code failed");

        softAssert = new SoftAssert();
        softAssert.assertTrue(usersEndPoint.getResponseTime() < 8000L, "response time too long");
        softAssert.assertTrue(usersEndPoint.verifySchema(schemaJsonPath), "schema validation failed");
        softAssert.assertAll();
    }

    @DataProvider(name = "update data")
    public Object[][] updateUserDataProvider() {
        return new Object[][]{
                {new JsonPayloadProvider().getCredentialsUpdateUserJson(), getUpdateUserSchemaPath()}
        };
    }
}
