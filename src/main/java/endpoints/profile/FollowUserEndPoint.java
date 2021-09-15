package endpoints.profile;

import endpoints.EndPoint;
import models.profiles.ProfileModel;
import utilities.Log;

public class FollowUserEndPoint extends EndPoint {
    private final String followUserPath = "profiles/{userName}/follow";

    public FollowUserEndPoint(String token) {
        super(token);
    }

    public ProfileModel followUser(String username) {
        createNewRequest();
        assignUsername(username);
        Log.info("Calling follow user endpoint");
        apiCallManager(followUserPath, POST);
        return getProfileResponseBodyAsModel();
    }

    public ProfileModel unfollowUser(String username) {
        createNewRequest();
        assignUsername(username);
        Log.info("Calling follow user endpoint");
        apiCallManager(followUserPath, DELETE);
        return getProfileResponseBodyAsModel();
    }

    private void assignUsername(String username) {
        assignPathParameter("userName", username);
    }

    private ProfileModel getProfileResponseBodyAsModel() {
        return getResponseBody().as(ProfileModel.class);
    }
}
