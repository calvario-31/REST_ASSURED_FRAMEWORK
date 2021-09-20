package articles;

import endpoints.articles.ArticlesEndPoint;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.Base;
import utilities.Commons;
import utilities.endpointhelpers.JsonPayloadProvider;

import static utilities.endpointhelpers.SchemaProvider.getArticleSchemaPath;

public class UpdateArticleTest extends Base {
    private ArticlesEndPoint articlesEndPoint;
    private String articleId;

    @Test(dataProvider = "update article data", groups = {"regression"})
    public void updateArticleTest(String payloadUpdate, String schemaJsonPath) {
        commons = new Commons();
        token = commons.generateNewUser().getToken();
        articleId = commons.generateNewArticle(token).getSlug();

        articlesEndPoint = new ArticlesEndPoint(token);
        articlesEndPoint.updateArticle(articleId, payloadUpdate);

        Assert.assertTrue(articlesEndPoint.verifyStatusCode(200), "status code failed");
        softAssert = new SoftAssert();
        softAssert.assertTrue(articlesEndPoint.getResponseTime() < 8000L, "response time too long");
        softAssert.assertTrue(articlesEndPoint.verifySchema(schemaJsonPath), "schema validation failed");
        softAssert.assertAll();
    }

    @DataProvider(name = "update article data")
    public Object[][] updateArticleDataProvider() {
        return new Object[][]{
                {new JsonPayloadProvider().getArticleJson(), getArticleSchemaPath()}
        };
    }
}
