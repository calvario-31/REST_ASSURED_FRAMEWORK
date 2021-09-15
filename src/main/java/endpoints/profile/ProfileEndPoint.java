package endpoints.profile;

import endpoints.EndPoint;
import models.profiles.ProfileModel;
import utilities.Log;

public class ProfileEndPoint extends EndPoint {
    private final String profilePath = "profiles/{userName}";

    public ProfileEndPoint(String token) {
        super(token);
    }

    public ProfileModel getUserInfo(String username) {
        createNewRequest();
        assignUsername(username);
        Log.info("Calling get user info endpoint");
        apiCallManager(profilePath, GET);
        return getProfileResponseBodyAsModel();
    }

    public void assignUsername(String username) {
        assignPathParameter("userName", username);
    }

    private ProfileModel getProfileResponseBodyAsModel() {
        return getResponseBody().as(ProfileModel.class);
    }
}
