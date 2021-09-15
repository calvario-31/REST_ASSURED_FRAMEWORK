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

public class FollowUserTest extends Base {
    private FollowUserEndPoint followUserEndPoint;
    private UserResponseModel userToFollow;
    private ProfileModel profileResponse;

    @Test(dataProvider = "get user info data", groups = {"smoke"})
    public void followUserTest(String schemaJsonPath) {
        userToFollow = generateNewUser();
        followUserEndPoint = new FollowUserEndPoint(token);
        profileResponse = followUserEndPoint.followUser(userToFollow.getUsername());

        Assert.assertTrue(followUserEndPoint.verifyStatusCode(200));
        softAssert = new SoftAssert();
        softAssert.assertTrue(profileResponse.isFollowing());
        softAssert.assertTrue(followUserEndPoint.getResponseTime() < 12000L);
        softAssert.assertTrue(followUserEndPoint.verifySchema(schemaJsonPath));
        softAssert.assertAll();
    }

    @DataProvider(name = "get user info data")
    public Object[][] getUserInfoDataProvider() {
        return new Object[][]{
                {getProfileSchemaPath()}
        };
    }
}
