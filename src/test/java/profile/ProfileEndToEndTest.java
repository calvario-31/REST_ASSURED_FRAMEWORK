package profile;

import endpoints.profile.FollowUserEndPoint;
import endpoints.profile.ProfileEndPoint;
import endpoints.users.UsersEndPoint;
import models.profiles.ProfileModel;
import models.users.UserResponseModel;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Base;

public class ProfileEndToEndTest extends Base {
    private UsersEndPoint usersEndPoint;
    private ProfileEndPoint profileEndPoint;
    private FollowUserEndPoint followUserEndPoint;
    private UserResponseModel userToUnfollow;
    private ProfileModel profileResponse;

    @Test(groups = {"smoke"})
    public void profileEndToEndTest() {
        usersEndPoint = new UsersEndPoint();
        userToUnfollow = usersEndPoint.createNewUser();

        profileEndPoint = new ProfileEndPoint(token);
        profileEndPoint.getUserInfo(userToUnfollow.getUsername());

        Assert.assertTrue(profileEndPoint.verifyStatusCode(200));

        followUserEndPoint = new FollowUserEndPoint(token);
        profileResponse = followUserEndPoint.followUser(userToUnfollow.getUsername());

        Assert.assertTrue(followUserEndPoint.verifyStatusCode(200));
        Assert.assertTrue(profileResponse.isFollowing());

        profileResponse = followUserEndPoint.unfollowUser(userToUnfollow.getUsername());

        Assert.assertTrue(followUserEndPoint.verifyStatusCode(200));
        Assert.assertFalse(profileResponse.isFollowing());

    }
}
