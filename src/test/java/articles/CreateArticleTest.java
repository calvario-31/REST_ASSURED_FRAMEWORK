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

public class CreateArticleTest extends Base {
    private ArticlesEndPoint articlesEndPoint;

    @Test(dataProvider = "new article data", groups = {"regression"})
    public void createArticleTest(String payload, String schemaJsonPath) {
        token = new Commons().generateNewUser().getToken();

        articlesEndPoint = new ArticlesEndPoint(token);
        articlesEndPoint.createArticle(payload);

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
