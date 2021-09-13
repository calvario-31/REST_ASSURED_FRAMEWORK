package endpoints.users;

import endpoints.EndPoint;
import models.Model;
import models.users.CredentialsSuperModel;
import org.testng.Assert;
import utilities.Log;

public class UsersEndPoint extends EndPoint {
    private final String registerPath = "/users";
    private final String loginPath = "/users/login";
    private final String userPath = "/user";

    public UsersEndPoint() {
    }

    public UsersEndPoint(String token) {
        super(token);
    }

    public void createUser(Model newUser) {
        assignBodyParameter(newUser);
        Log.info("Calling register endpoint");
        apiCallManager(registerPath, POST);
    }

    public void loginUser(Model credentials) {
        assignBodyParameter(credentials);
        Log.info("Calling login endpoint");
        apiCallManager(loginPath, POST);
    }

    public void getCurrentUserInfo() {
        Log.info("Calling get user info endpoint");
        apiCallManager(userPath, GET);
    }

    public String generateToken() {
        assignBodyParameter(new CredentialsSuperModel());
        apiCallManager(registerPath, POST);
        Log.info("Getting token from the response");
        Assert.assertTrue(verifyStatusCode(200));
        return getFieldFromResponse("user.token");
    }

    @Override
    protected void apiCallManager(String url, String method) {
        switch (method) {
            case GET:
                response = request.get(url);
                break;
            case POST:
                response = request.post(url);
                break;
            default:
                response = null;
                Log.error("Bad method name");
        }
    }
}
