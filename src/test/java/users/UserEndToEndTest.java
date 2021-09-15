package users;

import endpoints.users.UsersEndPoint;
import models.users.UserResponseModel;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilities.Base;
import utilities.endpointhelpers.JsonPayloadProvider;

public class UserEndToEndTest extends Base {
    private UsersEndPoint usersEndPoint;
    private UserResponseModel userResponse;

    @Test(dataProvider = "user data", groups = {"smoke"})
    public void userEndToEndTest(String payloadCreation, String payloadUpdate) {
        usersEndPoint = new UsersEndPoint();
        usersEndPoint.createUser(payloadCreation);

        Assert.assertTrue(usersEndPoint.verifyStatusCode(200));

        userResponse = usersEndPoint.loginUser(payloadCreation);

        Assert.assertTrue(usersEndPoint.verifyStatusCode(200));

        usersEndPoint = new UsersEndPoint(userResponse.getToken());

        usersEndPoint.updateUserInfo(payloadUpdate);

        Assert.assertTrue(usersEndPoint.verifyStatusCode(200));

        usersEndPoint.getCurrentUserInfo();

        Assert.assertTrue(usersEndPoint.verifyStatusCode(200));

    }

    @DataProvider(name = "user data")
    public Object[][] userDataprovider() {
        JsonPayloadProvider jsonPayloadProvider = new JsonPayloadProvider();
        return new Object[][]{
                {jsonPayloadProvider.getCredentialsUserJson(), jsonPayloadProvider.getCredentialsUpdateUserJson()}
        };
    }
}
