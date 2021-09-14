package users;

import endpoints.users.UsersEndPoint;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.Base;

import static utilities.SchemaProvider.getUserSchemaPath;

public class GetUserInfoTest extends Base {
    private UsersEndPoint usersEndPoint;
    private SoftAssert softAssert;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        initEndPoints();
    }

    @Test(dataProvider = "credentials data", groups = {"smoke"})
    public void getUserInfoTest(String schemaJsonPath) {
        usersEndPoint.getCurrentUserInfo();

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

    @DataProvider(name = "credentials data")
    public Object[][] getUserInfoDataProvider() {
        return new Object[][]{
                {getUserSchemaPath()}
        };
    }
}
