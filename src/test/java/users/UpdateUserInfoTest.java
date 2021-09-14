package users;

import endpoints.users.UsersEndPoint;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.Base;
import utilities.JsonPayloadProvider;

import static utilities.SchemaProvider.getUserSchemaPath;

public class UpdateUserInfoTest extends Base {
    private UsersEndPoint usersEndPoint;
    private SoftAssert softAssert;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        initEndPoints();
    }

    @Test(dataProvider = "update data", groups = {"smoke"})
    public void testName(String updateUserPayload, String schemaJsonPath) {
        usersEndPoint.updateUserInfo(updateUserPayload);

        Assert.assertTrue(usersEndPoint.verifyStatusCode(200));

        softAssert = new SoftAssert();
        softAssert.assertTrue(usersEndPoint.getResponseTime() < 8000L);
        softAssert.assertTrue(usersEndPoint.verifySchema(schemaJsonPath));
        softAssert.assertAll();
    }

    @Override
    public void initEndPoints() {
        usersEndPoint = new UsersEndPoint(token);
    }

    @DataProvider(name = "update data")
    public Object[][] updateUserDataProvider() {
        return new Object[][]{
                {new JsonPayloadProvider().getUserJson(), getUserSchemaPath()}
        };
    }
}
