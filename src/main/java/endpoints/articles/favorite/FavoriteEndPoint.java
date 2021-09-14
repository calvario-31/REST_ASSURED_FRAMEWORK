package endpoints.articles.favorite;

import endpoints.EndPoint;
import utilities.Log;

public class FavoriteEndPoint extends EndPoint {
    private final String favoritePath = "articles/{articleId}/favorite";
    private final String articleId;

    public FavoriteEndPoint(String token, String articleId) {
        super(token);
        this.articleId = articleId;
    }

    public void favoriteArticle() {
        createNewRequest();
        assignArticleId();
        Log.info("Calling favorite article endpoint");
        apiCallManager(favoritePath, POST);
    }

    public void unfavoriteArticle() {
        createNewRequest();
        assignArticleId();
        Log.info("Calling unfavorite article endpoint");
        apiCallManager(favoritePath, DELETE);
    }

    private void assignArticleId() {
        assignPathParameter("articleId", articleId);
    }

    public int getFavoriteCount() {
        return Integer.parseInt(getFieldFromResponse("article.favoritesCount"));
    }
}
