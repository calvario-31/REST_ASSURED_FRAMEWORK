package profile;

import endpoints.profile.ProfileEndPoint;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.Base;
import utilities.Commons;

import static utilities.endpointhelpers.SchemaProvider.getProfileSchemaPath;

public class GetUserInfoTest extends Base {
    private ProfileEndPoint profileEndPoint;
    private String usernameToGetInfo;

    @Test(dataProvider = "get user info data", groups = {"smoke"})
    public void getUserInfoTest(String schemaJsonPath) {
        commons = new Commons();
        token = commons.generateNewUser().getToken();
        usernameToGetInfo = commons.generateNewUser().getUsername();

        profileEndPoint = new ProfileEndPoint(token);
        profileEndPoint.getUserInfo(usernameToGetInfo);

        Assert.assertTrue(profileEndPoint.verifyStatusCode(200), "status code failed");
        softAssert = new SoftAssert();
        softAssert.assertTrue(profileEndPoint.getResponseTime() < 12000L, "response time too long");
        softAssert.assertTrue(profileEndPoint.verifySchema(schemaJsonPath), "schema validation failed");
        softAssert.assertAll();
    }

    @DataProvider(name = "get user info data")
    public Object[][] getUserInfoDataProvider() {
        return new Object[][]{
                {getProfileSchemaPath()}
        };
    }
}
