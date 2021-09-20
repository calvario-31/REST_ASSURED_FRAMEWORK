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

public class UnfollowUserTest extends Base {
    private FollowUserEndPoint followUserEndPoint;
    private ProfileModel profileResponse;
    private String usernameToUnfollow;

    @Test(dataProvider = "get user info data", groups = {"regression"})
    public void unfollowUserTest(String schemaJsonPath) {
        commons = new Commons();
        token = commons.generateNewUser().getToken();
        usernameToUnfollow = commons.generateNewUser().getUsername();

        followUserEndPoint = new FollowUserEndPoint(token);
        followUserEndPoint.followUser(usernameToUnfollow);
        profileResponse = followUserEndPoint.unfollowUser(usernameToUnfollow);

        Assert.assertTrue(followUserEndPoint.verifyStatusCode(200));
        softAssert = new SoftAssert();
        softAssert.assertTrue(followUserEndPoint.getResponseTime() < 12000L);
        softAssert.assertTrue(followUserEndPoint.verifySchema(schemaJsonPath));
        softAssert.assertFalse(profileResponse.isFollowing());
        softAssert.assertAll();
    }

    @DataProvider(name = "get user info data")
    public Object[][] getUserInfoDataProvider() {
        return new Object[][]{
                {getProfileSchemaPath()}
        };
    }
}
