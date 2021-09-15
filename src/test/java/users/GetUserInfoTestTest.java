package users;

import endpoints.users.UsersEndPoint;
import models.users.UserResponseModel;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.Base;

import static utilities.endpointhelpers.SchemaProvider.getUserSchemaPath;

public class GetUserInfoTestTest extends Base {
    private UsersEndPoint usersEndPoint;
    private UserResponseModel userResponse;

    @Test(dataProvider = "credentials data", groups = {"smoke"})
    public void getUserInfoTest(String schemaJsonPath) {
        usersEndPoint = new UsersEndPoint(token);
        userResponse = usersEndPoint.getCurrentUserInfo();

        Assert.assertTrue(usersEndPoint.verifyStatusCode(200));

        softAssert = new SoftAssert();
        softAssert.assertTrue(usersEndPoint.getResponseTime() < 8000L);
        softAssert.assertTrue(usersEndPoint.verifySchema(schemaJsonPath));
        softAssert.assertAll();
    }

    @DataProvider(name = "credentials data")
    public Object[][] getUserInfoDataProvider() {
        return new Object[][]{
                {getUserSchemaPath()}
        };
    }
}
