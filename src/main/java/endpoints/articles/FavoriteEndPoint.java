package endpoints.articles;

import endpoints.EndPoint;
import models.articles.ArticleResponseModel;
import models.articles.GetArticleModel;
import utilities.Log;

public class FavoriteEndPoint extends EndPoint {
    private final String favoritePath = "articles/{articleId}/favorite";

    public FavoriteEndPoint(String token) {
        super(token);
    }

    public ArticleResponseModel favoriteArticle(String articleId) {
        createNewRequest();
        assignArticleId(articleId);
        Log.info("Calling favorite article endpoint");
        apiCallManager(favoritePath, POST);
        return getArticleResponseBodyAsModel();
    }

    public ArticleResponseModel unfavoriteArticle(String articleId) {
        createNewRequest();
        assignArticleId(articleId);
        Log.info("Calling unfavorite article endpoint");
        apiCallManager(favoritePath, DELETE);
        return getArticleResponseBodyAsModel();
    }

    private void assignArticleId(String articleId) {
        assignPathParameter("articleId", articleId);
    }

    public ArticleResponseModel getArticleResponseBodyAsModel() {
        return getResponseBody().as(GetArticleModel.class).getArticle();
    }
}
