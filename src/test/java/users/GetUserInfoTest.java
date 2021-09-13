package users;

import endpoints.users.UsersEndPoint;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static utilities.SchemaReader.getUserSchema;

public class GetUserInfoTest {
    private UsersEndPoint usersEndPoint;
    private SoftAssert softAssert;

    @BeforeMethod
    public void setUp() {
        initEndPoints();
    }

    @Test(dataProvider = "credentials data")
    public void getUserInfoTest(String schemaJsonPath) {
        usersEndPoint.getCurrentUserInfo();

        softAssert = new SoftAssert();
        softAssert.assertTrue(usersEndPoint.verifyStatusCode(200));
        softAssert.assertTrue(usersEndPoint.getResponseTime() < 8000L);
        softAssert.assertTrue(usersEndPoint.verifySchema(schemaJsonPath));
        softAssert.assertAll();
    }

    public void initEndPoints() {
        usersEndPoint = new UsersEndPoint();
        String token = usersEndPoint.generateToken();
        usersEndPoint.setToken(token);
    }

    @DataProvider(name = "credentials data")
    public Object[][] getUserInfoDataProvider() {
        return new Object[][]{
                {getUserSchema()}
        };
    }
}
