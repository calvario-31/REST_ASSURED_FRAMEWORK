package articles;

import endpoints.articles.ArticlesEndPoint;
import models.Model;
import models.articles.ArticleResponseModel;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.Base;
import utilities.endpointhelpers.JsonPayloadProvider;

import static utilities.endpointhelpers.SchemaProvider.getArticleSchemaPath;

public class UpdateArticleTest extends Base {
    private ArticlesEndPoint articlesEndPoint;
    private ArticleResponseModel newArticleResponse;

    @Test(dataProvider = "update article data", groups = {"smoke"})
    public void updateArticleTest(Model payloadUpdate, String schemaJsonPath) {
        articlesEndPoint = new ArticlesEndPoint(token);
        newArticleResponse = articlesEndPoint.generateNewArticle().getArticle();
        articlesEndPoint.updateArticle(newArticleResponse.getSlug(), payloadUpdate);

        Assert.assertTrue(articlesEndPoint.verifyStatusCode(200));
        softAssert = new SoftAssert();
        softAssert.assertTrue(articlesEndPoint.getResponseTime() < 8000L);
        softAssert.assertTrue(articlesEndPoint.verifySchema(schemaJsonPath));
        softAssert.assertAll();
    }

    @DataProvider(name = "update article data")
    public Object[][] updateArticleDataProvider() {
        return new Object[][]{
                {new JsonPayloadProvider().getArticleJson(), getArticleSchemaPath()}
        };
    }
}
