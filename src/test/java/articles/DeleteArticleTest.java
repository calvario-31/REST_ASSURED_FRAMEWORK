package articles;

import endpoints.articles.ArticlesEndPoint;
import models.articles.ArticleResponseModel;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Base;

public class DeleteArticleTest extends Base {
    private ArticlesEndPoint articlesEndPoint;
    private ArticleResponseModel articleResponse;

    @Test(groups = {"smoke"})
    public void deleteArticleTest() {
        articlesEndPoint = new ArticlesEndPoint(token);
        articleResponse =  articlesEndPoint.generateNewArticle().getArticle();

        articlesEndPoint.deleteArticle(articleResponse.getSlug());

        Assert.assertTrue(articlesEndPoint.verifyStatusCode(200));
        Assert.assertTrue(articlesEndPoint.getResponseTime() < 8000L);
    }
}
