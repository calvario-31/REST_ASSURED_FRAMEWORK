package endpoints.users;

import endpoints.EndPoint;
import models.users.UserResponseModel;
import utilities.Log;

public class UsersEndPoint extends EndPoint {
    private final String registerPath = "users";
    private final String loginPath = "users/login";
    private final String userPath = "user";

    public UsersEndPoint(String token) {
        super(token);
    }

    public UsersEndPoint() {
        super();
    }

    public UserResponseModel createUser(String payload) {
        createNewRequest();
        assignBodyParameter(payload);
        Log.info("Calling register endpoint");
        apiCallManager(registerPath, POST);
        return getUserResponseBodyAsModel();
    }

    public UserResponseModel loginUser(String payload) {
        createNewRequest();
        assignBodyParameter(payload);
        Log.info("Calling login endpoint");
        apiCallManager(loginPath, POST);
        return getUserResponseBodyAsModel();
    }

    public UserResponseModel getCurrentUserInfo() {
        createNewRequest();
        Log.info("Calling get user info endpoint");
        apiCallManager(userPath, GET);
        return getUserResponseBodyAsModel();
    }

    public UserResponseModel updateUserInfo(String payload) {
        createNewRequest();
        Log.info("Calling update user info endpoint");
        assignBodyParameter(payload);
        apiCallManager(userPath, PUT);
        return getUserResponseBodyAsModel();
    }


    private UserResponseModel getUserResponseBodyAsModel() {
        return getResponseBody().as(UserResponseModel.class);
    }
}
