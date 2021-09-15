package articles;

import endpoints.articles.ArticlesEndPoint;
import models.articles.ArticleResponseModel;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.Base;
import utilities.endpointhelpers.JsonPayloadProvider;

import static utilities.endpointhelpers.SchemaProvider.getArticleSchemaPath;

public class CreateArticleTest extends Base {
    private ArticlesEndPoint articlesEndPoint;
    private ArticleResponseModel articleResponse;

    @Test(dataProvider = "new article data", groups = {"regression"})
    public void createArticleTest(String payload, String schemaJsonPath) {
        articlesEndPoint = new ArticlesEndPoint(token);

        articleResponse = articlesEndPoint.createArticle(payload);

        Assert.assertTrue(articlesEndPoint.verifyStatusCode(200));
        softAssert = new SoftAssert();
        softAssert.assertTrue(articlesEndPoint.getResponseTime() < 8000L);
        softAssert.assertTrue(articlesEndPoint.verifySchema(schemaJsonPath));
        softAssert.assertAll();
    }

    @DataProvider(name = "new article data")
    public Object[][] createArticleDataProvider() {
        return new Object[][]{
                {new JsonPayloadProvider().getArticleJson(), getArticleSchemaPath()}
        };
    }
}
