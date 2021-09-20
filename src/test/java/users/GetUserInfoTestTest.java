package users;

import endpoints.users.UsersEndPoint;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.Base;
import utilities.Commons;

import static utilities.endpointhelpers.SchemaProvider.getUserSchemaPath;

public class GetUserInfoTestTest extends Base {
    private UsersEndPoint usersEndPoint;

    @Test(dataProvider = "credentials data", groups = {"regression"})
    public void getUserInfoTest(String schemaJsonPath) {
        commons = new Commons();
        token = commons.generateNewUser().getToken();

        usersEndPoint = new UsersEndPoint(token);
        usersEndPoint.getCurrentUserInfo();

        Assert.assertTrue(usersEndPoint.verifyStatusCode(200), "status code failed");

        softAssert = new SoftAssert();
        softAssert.assertTrue(usersEndPoint.getResponseTime() < 8000L, "response time too long");
        softAssert.assertTrue(usersEndPoint.verifySchema(schemaJsonPath), "schema validation failed");
        softAssert.assertAll();
    }

    @DataProvider(name = "credentials data")
    public Object[][] getUserInfoDataProvider() {
        return new Object[][]{
                {getUserSchemaPath()}
        };
    }
}
