package profile.follow;

import endpoints.profile.FollowUserEndPoint;
import models.profiles.ProfileModel;
import models.users.UserResponseModel;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.Base;

import static utilities.endpointhelpers.SchemaProvider.getProfileSchemaPath;

public class UnfollowUserTest extends Base {
    private FollowUserEndPoint followUserEndPoint;
    private ProfileModel profileResponse;
    private UserResponseModel userToUnfollow;

    @Test(dataProvider = "get user info data", groups = {"regression"})
    public void unfollowUserTest(String schemaJsonPath) {
        userToUnfollow = generateNewUser();
        followUserEndPoint = new FollowUserEndPoint(userToUnfollow.getToken());
        followUserEndPoint.followUser(userToUnfollow.getUsername());

        profileResponse = followUserEndPoint.unfollowUser(userToUnfollow.getUsername());

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
