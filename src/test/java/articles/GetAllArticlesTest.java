package articles;

import endpoints.articles.ArticlesEndPoint;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.Base;

import static utilities.SchemaProvider.getAllArticlesSchemaPath;

public class GetAllArticlesTest extends Base {
    private ArticlesEndPoint articlesEndPoint;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        initEndPoints();
    }

    @Test(dataProvider = "all articles data", groups = {"smoke"})
    public void getAllArticlesTest(String schemaJsonPath) {
        articlesEndPoint.getAllArticles();

        Assert.assertTrue(articlesEndPoint.verifyStatusCode(200));
        softAssert = new SoftAssert();
        softAssert.assertTrue(articlesEndPoint.getResponseTime() < 8000L);
        softAssert.assertTrue(articlesEndPoint.verifySchema(schemaJsonPath));
        softAssert.assertAll();
    }

    @Override
    public void initEndPoints() {
        articlesEndPoint = new ArticlesEndPoint(token);
    }

    @DataProvider(name = "all articles data")
    public Object[][] getAllArticlesDataProvider() {
        return new Object[][]{
                {getAllArticlesSchemaPath()}
        };
    }
}
