package articles;

import endpoints.articles.ArticlesEndPoint;
import models.articles.ArticleResponseModel;
import models.articles.GetAllArticlesModel;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.Base;
import utilities.Log;

import static utilities.endpointhelpers.SchemaProvider.getAllArticlesSchemaPath;

public class GetAllArticlesTest extends Base {
    private ArticlesEndPoint articlesEndPoint;
    private GetAllArticlesModel articleResponse;

    @Test(dataProvider = "all articles data", groups = {"smoke"})
    public void getAllArticlesTest(String schemaJsonPath) {
        articlesEndPoint = new ArticlesEndPoint(token);
        articleResponse = articlesEndPoint.getAllArticles();

        for(ArticleResponseModel articleResponseModel: articleResponse.getArticles()){
            Log.debug(articleResponseModel.getSlug());
        }

        Assert.assertTrue(articlesEndPoint.verifyStatusCode(200));
        softAssert = new SoftAssert();
        softAssert.assertTrue(articlesEndPoint.getResponseTime() < 8000L);
        softAssert.assertTrue(articlesEndPoint.verifySchema(schemaJsonPath));
        softAssert.assertAll();
    }

    @DataProvider(name = "all articles data")
    public Object[][] getAllArticlesDataProvider() {
        return new Object[][]{
                {getAllArticlesSchemaPath()}
        };
    }
}
