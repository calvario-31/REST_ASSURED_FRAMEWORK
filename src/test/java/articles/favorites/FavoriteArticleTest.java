package articles.favorites;

import endpoints.articles.ArticlesEndPoint;
import endpoints.articles.favorite.FavoriteEndPoint;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.Base;

import static utilities.SchemaProvider.getArticleSchemaPath;

public class FavoriteArticleTest extends Base {
    private FavoriteEndPoint favoriteEndPoint;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        initEndPoints();
    }

    @Test(dataProvider = "article data", groups = {"smoke"})
    public void favoriteArticleTest(String schemaJsonPath) {
        favoriteEndPoint.favoriteArticle();

        Assert.assertTrue(favoriteEndPoint.verifyStatusCode(200));
        softAssert = new SoftAssert();
        softAssert.assertTrue(favoriteEndPoint.getResponseTime() < 8000L);
        softAssert.assertTrue(favoriteEndPoint.verifySchema(schemaJsonPath));
        softAssert.assertEquals(favoriteEndPoint.getFavoriteCount(), 1);
        softAssert.assertAll();
    }

    @Override
    public void initEndPoints() {
        String articleId = new ArticlesEndPoint(token).generateArticleId();
        favoriteEndPoint = new FavoriteEndPoint(token, articleId);
    }

    @DataProvider(name = "article data")
    public Object[][] favoriteArticleDataProvider() {
        return new Object[][]{
                {getArticleSchemaPath()}
        };
    }
}
