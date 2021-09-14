package endpoints.users;

import endpoints.EndPoint;
import org.testng.Assert;
import utilities.JsonPayloadProvider;
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

    public void createUser(String newUserJson) {
        createNewRequest();
        assignBodyParameter(newUserJson);
        Log.info("Calling register endpoint");
        apiCallManager(registerPath, POST);
    }

    public void loginUser(String credentialsJson) {
        createNewRequest();
        assignBodyParameter(credentialsJson);
        Log.info("Calling login endpoint");
        apiCallManager(loginPath, POST);
    }

    public void getCurrentUserInfo() {
        createNewRequest();
        Log.info("Calling get user info endpoint");
        apiCallManager(userPath, GET);
    }

    public void updateUserInfo(String updateUserJson) {
        createNewRequest();
        Log.info("Calling update user info endpoint");
        assignBodyParameter(updateUserJson);
        apiCallManager(userPath, PUT);
    }

    public String generateToken() {
        createNewRequest();
        assignBodyParameter(new JsonPayloadProvider().getUserJson());
        apiCallManager(registerPath, POST);
        Log.info("Getting token from the response");
        Assert.assertTrue(verifyStatusCode(200));
        return getFieldFromResponse("user.token");
    }
}
