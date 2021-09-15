package articles;

import endpoints.articles.ArticlesEndPoint;
import models.articles.ArticleResponseModel;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.Base;

import static utilities.endpointhelpers.SchemaProvider.getArticleSchemaPath;

public class GetArticleTest extends Base {
    private ArticlesEndPoint articlesEndPoint;
    private ArticleResponseModel newArticleResponse;

    @Test(dataProvider = "article data", groups = {"regression"})
    public void getArticleTest(String schemaJsonPath) {
        articlesEndPoint = new ArticlesEndPoint(token);
        newArticleResponse = articlesEndPoint.generateNewArticle().getArticle();

        articlesEndPoint.getArticle(newArticleResponse.getSlug());

        Assert.assertTrue(articlesEndPoint.verifyStatusCode(200));
        softAssert = new SoftAssert();
        softAssert.assertTrue(articlesEndPoint.getResponseTime() < 8000L);
        softAssert.assertTrue(articlesEndPoint.verifySchema(schemaJsonPath));
        softAssert.assertAll();
    }

    @DataProvider(name = "article data")
    public Object[][] getArticleDataProvider() {
        return new Object[][]{
                {getArticleSchemaPath()}
        };
    }
}
