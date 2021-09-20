package profile.follow;

import endpoints.profile.FollowUserEndPoint;
import models.profiles.ProfileModel;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.Base;
import utilities.Commons;

import static utilities.endpointhelpers.SchemaProvider.getProfileSchemaPath;

public class FollowUserTest extends Base {
    private FollowUserEndPoint followUserEndPoint;
    private String usernameToFollow;
    private ProfileModel profileResponse;

    @Test(dataProvider = "get user info data", groups = {"regression"})
    public void followUserTest(String schemaJsonPath) {
        commons = new Commons();
        token = commons.generateNewUser().getToken();
        usernameToFollow = commons.generateNewUser().getUsername();

        followUserEndPoint = new FollowUserEndPoint(token);
        profileResponse = followUserEndPoint.followUser(usernameToFollow);

        Assert.assertTrue(followUserEndPoint.verifyStatusCode(200), "status code failed");
        softAssert = new SoftAssert();
        softAssert.assertTrue(profileResponse.isFollowing(), "following boolean failed");
        softAssert.assertTrue(followUserEndPoint.getResponseTime() < 12000L, "response time too long");
        softAssert.assertTrue(followUserEndPoint.verifySchema(schemaJsonPath), "schema validation failed");
        softAssert.assertAll();
    }

    @DataProvider(name = "get user info data")
    public Object[][] getUserInfoDataProvider() {
        return new Object[][]{
                {getProfileSchemaPath()}
        };
    }
}
