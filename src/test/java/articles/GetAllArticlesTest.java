package articles;

import endpoints.articles.ArticlesEndPoint;
import models.articles.ArticleResponseModel;
import models.articles.GetAllArticlesModel;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.Base;
import utilities.Log;

public class GetAllArticlesTest extends Base {
    private ArticlesEndPoint articlesEndPoint;
    private GetAllArticlesModel articleResponse;

    @Test(groups = {"regression"})
    public void getAllArticlesTest() {
        articlesEndPoint = new ArticlesEndPoint(token);
        articleResponse = articlesEndPoint.getAllArticles();

        for(ArticleResponseModel articleResponseModel: articleResponse.getArticles()){
            Log.debug(articleResponseModel.getSlug());
        }

        Assert.assertTrue(articlesEndPoint.verifyStatusCode(200));
        softAssert = new SoftAssert();
        softAssert.assertTrue(articlesEndPoint.getResponseTime() < 8000L);
        softAssert.assertAll();
    }
}
