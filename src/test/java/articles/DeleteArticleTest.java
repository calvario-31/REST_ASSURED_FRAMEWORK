package articles;

import endpoints.articles.ArticlesEndPoint;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Base;
import utilities.Commons;

public class DeleteArticleTest extends Base {
    private ArticlesEndPoint articlesEndPoint;
    private String articleId;

    @Test(groups = {"regression"})
    public void deleteArticleTest() {
        commons = new Commons();
        token = commons.generateNewUser().getToken();
        articleId = commons.generateNewArticle(token).getSlug();

        articlesEndPoint = new ArticlesEndPoint(token);
        articlesEndPoint.deleteArticle(articleId);

        Assert.assertTrue(articlesEndPoint.verifyStatusCode(200));
        Assert.assertTrue(articlesEndPoint.getResponseTime() < 8000L);
    }
}
