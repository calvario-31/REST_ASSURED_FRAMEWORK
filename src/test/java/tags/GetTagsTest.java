package tags;

import endpoints.tags.TagsEndPoint;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.Base;

import static utilities.SchemaProvider.getTagsSchemaPath;

public class GetTagsTest extends Base {
    private TagsEndPoint tagsEndPoint;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        initEndPoints();
    }

    @Test(dataProvider = "get tags data", groups = {"smoke", "regression"})
    public void getTagsTest(String schemaJsonPath) {
        tagsEndPoint.getTags();

        Assert.assertTrue(tagsEndPoint.verifyStatusCode(200));
        softAssert = new SoftAssert();
        softAssert.assertTrue(tagsEndPoint.getResponseTime() < 12000L);
        softAssert.assertTrue(tagsEndPoint.verifySchema(schemaJsonPath));
        softAssert.assertAll();
    }

    @Override
    public void initEndPoints() {
        tagsEndPoint = new TagsEndPoint();
    }

    @DataProvider(name = "get tags data")
    public Object[][] getTagsDataProvider() {
        return new Object[][]{
                {getTagsSchemaPath()}
        };
    }
}
