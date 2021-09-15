package articles.favorites;

import endpoints.articles.ArticlesEndPoint;
import endpoints.articles.FavoriteEndPoint;
import models.articles.ArticleResponseModel;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.Base;

import static utilities.endpointhelpers.SchemaProvider.getArticleSchemaPath;

public class FavoriteArticleTest extends Base {
    private FavoriteEndPoint favoriteEndPoint;
    private ArticleResponseModel articleResponse;
    private ArticleResponseModel newArticleResponse;

    @Test(dataProvider = "article data", groups = {"regression"})
    public void favoriteArticleTest(String schemaJsonPath) {
        newArticleResponse = new ArticlesEndPoint(token).generateNewArticle();
        favoriteEndPoint = new FavoriteEndPoint(token);

        articleResponse = favoriteEndPoint.favoriteArticle(newArticleResponse.getSlug());

        Assert.assertTrue(favoriteEndPoint.verifyStatusCode(200));
        softAssert = new SoftAssert();
        softAssert.assertTrue(favoriteEndPoint.getResponseTime() < 8000L);
        softAssert.assertTrue(favoriteEndPoint.verifySchema(schemaJsonPath));
        softAssert.assertEquals(articleResponse.getFavoritesCount(), 1);
        softAssert.assertAll();
    }

    @DataProvider(name = "article data")
    public Object[][] favoriteArticleDataProvider() {
        return new Object[][]{
                {getArticleSchemaPath()}
        };
    }
}
