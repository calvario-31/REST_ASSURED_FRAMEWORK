package articles;

import endpoints.articles.ArticlesEndPoint;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.Base;
import utilities.Commons;

import static utilities.endpointhelpers.SchemaProvider.getArticleSchemaPath;

public class GetArticleTest extends Base {
    private ArticlesEndPoint articlesEndPoint;
    private String articleId;

    @Test(dataProvider = "article data", groups = {"regression"})
    public void getArticleTest(String schemaJsonPath) {
        commons = new Commons();
        token = commons.generateNewUser().getToken();
        articleId = commons.generateNewArticle(token).getSlug();

        articlesEndPoint = new ArticlesEndPoint(token);
        articlesEndPoint.getArticle(articleId);

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
