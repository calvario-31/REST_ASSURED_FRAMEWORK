package articles.favorites;

import endpoints.articles.FavoriteEndPoint;
import models.articles.ArticleResponseModel;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.Base;
import utilities.Commons;

import static utilities.endpointhelpers.SchemaProvider.getArticleSchemaPath;

public class UnfavoriteArticleTest extends Base {
    private FavoriteEndPoint favoriteEndPoint;
    private ArticleResponseModel articleResponse;
    private String articleId;

    @Test(dataProvider = "article data", groups = {"regression"})
    public void unfavoriteArticleTest(String schemaJsonPath) {
        commons = new Commons();
        token = commons.generateNewUser().getToken();
        articleId = commons.generateNewArticle(token).getSlug();

        favoriteEndPoint = new FavoriteEndPoint(token);

        favoriteEndPoint.favoriteArticle(articleId);
        articleResponse = favoriteEndPoint.unfavoriteArticle(articleId);

        Assert.assertTrue(favoriteEndPoint.verifyStatusCode(200));
        softAssert = new SoftAssert();
        softAssert.assertTrue(favoriteEndPoint.getResponseTime() < 8000L);
        softAssert.assertTrue(favoriteEndPoint.verifySchema(schemaJsonPath));
        softAssert.assertEquals(articleResponse.getFavoritesCount(), 0);
        softAssert.assertAll();
    }

    @DataProvider(name = "article data")
    public Object[][] unfavoriteArticleDataProvider() {
        return new Object[][]{
                {getArticleSchemaPath()}
        };
    }
}
