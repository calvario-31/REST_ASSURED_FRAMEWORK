package profile;

import endpoints.profile.ProfileEndPoint;
import endpoints.users.UsersEndPoint;
import models.users.UserResponseModel;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.Base;

import static utilities.endpointhelpers.SchemaProvider.getProfileSchemaPath;

public class GetUserInfoTest extends Base {
    private UsersEndPoint usersEndPoint;
    private ProfileEndPoint profileEndPoint;
    private UserResponseModel userResponse;

    @Test(dataProvider = "get user info data", groups = {"smoke"})
    public void getUserInfoTest(String schemaJsonPath) {
        usersEndPoint = new UsersEndPoint();
        userResponse = usersEndPoint.createNewUser();

        profileEndPoint = new ProfileEndPoint(userResponse.getToken());
        profileEndPoint.getUserInfo(userResponse.getUsername());

        Assert.assertTrue(profileEndPoint.verifyStatusCode(200));
        softAssert = new SoftAssert();
        softAssert.assertTrue(profileEndPoint.getResponseTime() < 12000L);
        softAssert.assertTrue(profileEndPoint.verifySchema(schemaJsonPath));
        softAssert.assertAll();
    }

    @DataProvider(name = "get user info data")
    public Object[][] getUserInfoDataProvider() {
        return new Object[][]{
                {getProfileSchemaPath()}
        };
    }
}
