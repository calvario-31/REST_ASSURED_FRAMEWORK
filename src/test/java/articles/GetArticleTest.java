package articles;

import endpoints.articles.ArticlesEndPoint;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.Base;
import utilities.JsonPayloadProvider;

import static utilities.SchemaProvider.getArticleSchemaPath;

public class GetArticleTest extends Base {
    private ArticlesEndPoint articlesEndPoint;
    private String articleId;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        initEndPoints();
    }

    @Test(dataProvider = "article data", groups = {"smoke"})
    public void getArticleTest(String payload, String schemaJsonPath) {
        articlesEndPoint.createArticle(payload);
        articleId = articlesEndPoint.getArticleId();
        articlesEndPoint.getArticle(articleId);

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

    @DataProvider(name = "article data")
    public Object[][] getArticleDataProvider() {
        return new Object[][]{
                {new JsonPayloadProvider().getArticleJson(), getArticleSchemaPath()}
        };
    }
}
