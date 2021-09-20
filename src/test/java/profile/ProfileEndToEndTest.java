package profile;

import endpoints.profile.FollowUserEndPoint;
import endpoints.profile.ProfileEndPoint;
import models.profiles.ProfileModel;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Base;
import utilities.Commons;

public class ProfileEndToEndTest extends Base {
    private ProfileEndPoint profileEndPoint;
    private FollowUserEndPoint followUserEndPoint;
    private ProfileModel profileResponse;
    private String usernameToFollow;

    @Test(groups = {"smoke"})
    public void profileEndToEndTest() {
        commons = new Commons();
        token = commons.generateNewUser().getToken();
        usernameToFollow = commons.generateNewUser().getUsername();

        profileEndPoint = new ProfileEndPoint(token);
        profileEndPoint.getUserInfo(usernameToFollow);

        Assert.assertTrue(profileEndPoint.verifyStatusCode(200));

        followUserEndPoint = new FollowUserEndPoint(token);
        profileResponse = followUserEndPoint.followUser(usernameToFollow);

        Assert.assertTrue(followUserEndPoint.verifyStatusCode(200));
        Assert.assertTrue(profileResponse.isFollowing());

        profileResponse = followUserEndPoint.unfollowUser(usernameToFollow);

        Assert.assertTrue(followUserEndPoint.verifyStatusCode(200));
        Assert.assertFalse(profileResponse.isFollowing());

    }
}
