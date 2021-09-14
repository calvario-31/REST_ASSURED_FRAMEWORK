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

public class UpdateArticleTest extends Base {
    private ArticlesEndPoint articlesEndPoint;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        initEndPoints();
    }

    @Test(dataProvider = "update article data", groups = {"smoke"})
    public void updateArticleTest(String payloadInit, String payloadUpdate, String schemaJsonPath) {
        articlesEndPoint.createArticle(payloadInit);
        String articleId = articlesEndPoint.getArticleId();
        articlesEndPoint.updateArticle(payloadUpdate, articleId);

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

    @DataProvider(name = "update article data")
    public Object[][] updateArticleDataProvider() {
        return new Object[][]{
                {new JsonPayloadProvider().getArticleJson(), new JsonPayloadProvider().getArticleJson(), getArticleSchemaPath()}
        };
    }
}