package articles;

import endpoints.articles.ArticlesEndPoint;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilities.Base;
import utilities.JsonPayloadProvider;

public class DeleteArticleTest extends Base {
    private ArticlesEndPoint articlesEndPoint;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        initEndPoints();
    }

    @Test(dataProvider = "article data", groups = {"smoke"})
    public void testName(String payload) {
        articlesEndPoint.createArticle(payload);
        String articleId = articlesEndPoint.getArticleId();
        articlesEndPoint.deleteArticle(articleId);

        Assert.assertTrue(articlesEndPoint.verifyStatusCode(200));
        Assert.assertTrue(articlesEndPoint.getResponseTime() < 8000L);
    }

    @Override
    public void initEndPoints() {
        articlesEndPoint = new ArticlesEndPoint(token);
    }

    @DataProvider(name = "article data")
    public Object[][] DeleteArticleDataProvider() {
        return new Object[][]{
                {new JsonPayloadProvider().getArticleJson()}
        };
    }
}
