package endpoints.users;

import endpoints.EndPoint;
import models.Model;
import models.users.UserResponseModel;
import org.testng.Assert;
import utilities.Log;
import utilities.endpointhelpers.JsonPayloadProvider;

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

    public UserResponseModel createUser(Model payload) {
        createNewRequest();
        assignBodyParameter(payload);
        Log.info("Calling register endpoint");
        apiCallManager(registerPath, POST);
        return getUserResponseBodyAsModel();
    }

    public UserResponseModel loginUser(Model payload) {
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

    public UserResponseModel updateUserInfo(Model payload) {
        createNewRequest();
        Log.info("Calling update user info endpoint");
        assignBodyParameter(payload);
        apiCallManager(userPath, PUT);
        return getUserResponseBodyAsModel();
    }

    public UserResponseModel createNewUser() {
        createNewRequest();
        assignBodyParameter(new JsonPayloadProvider().getCredentialsUserJson());
        apiCallManager(registerPath, POST);
        Log.info("Getting token from the response");
        Assert.assertTrue(verifyStatusCode(200));
        return getUserResponseBodyAsModel();
    }

    public UserResponseModel getUserResponseBodyAsModel() {
        return getResponseBody().as(UserResponseModel.class);
    }
}
