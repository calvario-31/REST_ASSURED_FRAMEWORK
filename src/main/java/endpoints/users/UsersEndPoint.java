package endpoints.users;

import endpoints.EndPoint;
import models.Model;
import utilities.Log;

public class UsersEndPoint extends EndPoint {
    private final String registerPath = "users";
    private final String loginPath = "users/login";

    public UsersEndPoint() {
        createNewRequest();
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

    @Override
    protected void apiCallManager(String url, String method) {
        switch (method) {
            case GET:
                response = null;
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
