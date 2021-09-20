package articles;

import endpoints.articles.ArticlesEndPoint;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.Base;
import utilities.Commons;

public class GetAllArticlesTest extends Base {
    private ArticlesEndPoint articlesEndPoint;

    @Test(groups = {"regression"})
    public void getAllArticlesTest() {
        commons = new Commons();
        token = commons.generateNewUser().getToken();

        articlesEndPoint = new ArticlesEndPoint(token);
        articlesEndPoint.getAllArticles();

        Assert.assertTrue(articlesEndPoint.verifyStatusCode(200), "status code failed");
        softAssert = new SoftAssert();
        softAssert.assertTrue(articlesEndPoint.getResponseTime() < 8000L, "response time too long");
        softAssert.assertAll();
    }
}
