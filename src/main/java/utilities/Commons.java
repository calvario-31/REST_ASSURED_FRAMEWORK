package utilities;

import endpoints.articles.ArticlesEndPoint;
import endpoints.articles.CommentsEndPoint;
import endpoints.users.UsersEndPoint;
import models.articles.ArticleResponseModel;
import models.articles.CommentResponseModel;
import models.users.UserResponseModel;
import utilities.endpointhelpers.JsonPayloadProvider;

public class Commons {

    public UserResponseModel generateNewUser() {
        String payload = new JsonPayloadProvider().getCredentialsUserJson();
        return new UsersEndPoint().createUser(payload);
    }

    public ArticleResponseModel generateNewArticle(String token) {
        String payload = new JsonPayloadProvider().getArticleJson();
        return new ArticlesEndPoint(token).createArticle(payload);
    }

    public CommentResponseModel generateComment(String token, String articleId) {
        String payload = new JsonPayloadProvider().getCommentJson();
        return new CommentsEndPoint(token).createComment(articleId, payload);
    }
}
